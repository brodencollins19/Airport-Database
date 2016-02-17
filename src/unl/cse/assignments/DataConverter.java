package unl.cse.assignments;

import com.airamerica.*;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
/* Phase-I */

// Include imports for XML/JSON libraries if needed
import com.thoughtworks.xstream.XStream;

public class DataConverter {

	static ArrayList<Person> people;
	static ArrayList<Customer> customers;
	static ArrayList<Airport> airports;
	static ArrayList<Product> products;

	public static ArrayList<Person> PeopleReader() {
		// Data Reader for Person Object
		int i = 0;
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner pScanner = null;
		String personString = "";
		try {
			pScanner = new Scanner(new File("data/Persons.dat"));
			int numPeople = pScanner.nextInt();
			pScanner.nextLine(); // Used simply to advance the scanner to the
									// first line
			for (i = 0; i < numPeople; i++) {
				personString = pScanner.nextLine();
				String[] personLine = personString.split(";"); // Parses
																// different
																// attributes of
																// a given line
																// into
																// individual
																// elements
																// separated by
																// semicolons

				String[] name = personLine[1].split(",");
				String[] address = personLine[2].split(","); // Further
																// separations
																// that will
																// allow us to
																// easier read
																// and interpret
																// the data

				String personCode = personLine[0];
				String lastName = name[0];
				String firstName = name[1];
				String street = address[0];
				String city = address[1]; // Variables created from parsing the
											// data through the split() method
											// and Scanner object
				String state = address[2];
				String ZIP = address[3];
				String country = address[4];
				String phoneNo;

				people.add(new Person(personCode, lastName, firstName,
						new Address(street, city, state, ZIP, country)));

				if (personLine.length >= 3) {
					phoneNo = personLine[3]; //
					people.get(i).setPhoneNo(phoneNo);
				}

				String[] emailList = null;

				if (personLine.length > 4) {
					emailList = personLine[4].split(","); // If a given person
															// has, any email
															// addresses, this
															// code will add
															// these into a list
															// to store and
															// manage them
					for (int j = 0; j < emailList.length; j++) {
						people.get(i).addEmail(emailList[j]);

					}
				}
			}

		} catch (IOException personExcpt) {
			System.out.println("Invalid File");
			personExcpt.printStackTrace();
		} finally {
			pScanner.close();
		}
		return people;
	}

	// Reads and parses Customer data, creating a list

	public static ArrayList<Customer> CustomerReader() {
		int i = 0;
		ArrayList<Person> people = PeopleReader();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Scanner cScanner = null;
		String customerString = "";

		try {
			cScanner = new Scanner(new File("data/Customers.dat"));
			int numCustomers = cScanner.nextInt();
			cScanner.nextLine();

			for (i = 0; i < numCustomers; i++) {
				customerString = cScanner.nextLine();
				String[] customerLine = customerString.split(";");
				String customerCode = customerLine[0];
				String customerType = customerLine[1]; // Data is parsed and
														// stored into variables
														// (See above)
				String name = customerLine[3];
				String contactCode = customerLine[2];

				Customer customer = null;

				if (customerType.equals("V")) {
					customer = new GovernmentCustomer(customerCode,
							customerType, name);
				} else if (customerType.equals("C")) {
					customer = new CorporateCustomer(customerCode,
							customerType, name);
				} else {
					customer = new GeneralCustomer(customerCode, customerType,
							name);
				}

				customers.add(customer);

				// customers.add(new Customer(customerCode, customerType,
				// name));

				for (int k = 0; k < people.size(); k++) {
					if (contactCode.equalsIgnoreCase(people.get(k)
							.getPersonCode())) {
						customer.setPrimaryContact(people.get(k));
					}
				}

				if (customerLine.length > 4) {
					String airMiles = customerLine[4];
					customers.get(i).setAirlineMiles(airMiles);
				}

			}

		} catch (IOException customerExcept) {
			System.out.println(":Invalid File");
			customerExcept.printStackTrace();
		} finally {
			cScanner.close();
		}

		return customers;
	}

	// Reads data for aiprorts and creates a corresponding list

	public static ArrayList<Airport> AirportReader() {
		ArrayList<Airport> airports = new ArrayList<Airport>();
		Scanner aScanner = null;
		int i = 0;

		try {
			aScanner = new Scanner(new File("data/Airports.dat"));
			int numAirports = aScanner.nextInt();
			aScanner.nextLine();
			for (i = 0; i < numAirports; i++) {
				String airportString = aScanner.nextLine();
				String[] airportLine = airportString.split(";");
				String[] addressLine = airportLine[2].split(","); // Data is
																	// parsed
																	// and
																	// stored
																	// into
																	// variables
																	// (see
																	// above)
				String[] coordinates = airportLine[3].split(",");

				airports.add(new Airport(
						airportLine[0],
						airportLine[1],
						new Address(addressLine[0], addressLine[1],
								addressLine[2], addressLine[3], addressLine[4]),
						Integer.parseInt(coordinates[0]), Integer
								.parseInt(coordinates[1]), Integer
								.parseInt(coordinates[2]), Integer
								.parseInt(coordinates[3]), Double
								.parseDouble(airportLine[4])));

			}

		} catch (IOException airportExcept) {
			System.out.println("Invalid File");
			airportExcept.printStackTrace();
		} finally {
			aScanner.close();
		}

		return airports;

	}

	// Reads from the Airports file and the parses the data into a list

	public static ArrayList<Product> ProductReader() {
		ArrayList<Airport> airports = AirportReader();
		ArrayList<Product> products = new ArrayList<Product>();

		Scanner productScanner = null;
		int i = 0;
		try {
			productScanner = new Scanner(new File("data/Products.dat"));
			int numProducts = productScanner.nextInt();
			productScanner.nextLine();

			for (i = 0; i < numProducts; i++) {
				String productString = productScanner.nextLine();
				String[] productLine = productString.split(";");

				switch (productLine[1]) {
				case "TS":
					products.add(new StandardTicket(productLine[0],
							productLine[2], productLine[3], productLine[4],
							productLine[5], productLine[6], productLine[7],
							productLine[8]));
					break;
				case "TO":
					products.add(new OffSeasonTicket(productLine[0],
							productLine[4], productLine[5], productLine[6],
							productLine[7], productLine[8], productLine[9],
							productLine[10], productLine[2], productLine[3],
							Double.parseDouble(productLine[11])));
					break; // In this data reader, a swithc statement is used to
				case "TA": // Decide which object should be instantiated as the
							// loop moves through the flat file
					products.add(new AwardTicket(
							productLine[0],
							productLine[2],
							productLine[3], // Each product type will have a
											// corresponding constructor that
											// will be called according to which
											// branch of the switch statement
											// activates
							productLine[4], productLine[5], productLine[6],
							productLine[7], productLine[8], Integer
									.parseInt(productLine[9])));
					break;

				case "SC":
					products.add(new CheckedBaggage(productLine[0],
							productLine[2]));
					break;
				case "SI":
					products.add(new Insurance(productLine[0], productLine[2],
							productLine[3], Double.parseDouble(productLine[4])));
					break;
				case "SS":
					products.add(new SpecialAssistance(productLine[0],
							productLine[2]));
					break;
				case "SR":
					products.add(new Refreshment(productLine[0],
							productLine[2], Double.parseDouble(productLine[3])));
					break;

				}

			}

		} catch (IOException productExcept) {
			System.out.println("Invalid File");
			productExcept.printStackTrace();
		} finally {
			productScanner.close();
		}
		return products;

	}

	public static ArrayList<Ticket> getAllTickets() {
		ArrayList<Product> products = ProductReader();
		ArrayList<Airport> airports = AirportReader();

		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof Ticket) {
				tickets.add((Ticket) products.get(i));
			}

		}

		for (int i = 0; i < tickets.size(); i++) {
			for (int j = 0; j < airports.size(); j++) {
				if (tickets.get(i).getDepAirportCode()
						.equals(airports.get(j).getAirportCode())) {
					tickets.get(i).setDepartureAirport(airports.get(j));
				}
			}
			// Assigns Airports to the list of tickets
		}

		for (int i = 0; i < tickets.size(); i++) {
			for (int j = 0; j < airports.size(); j++) {
				if (tickets.get(i).getArrAirportCode()
						.equals(airports.get(j).getAirportCode())) {
					tickets.get(i).setArrivalAirport(airports.get(j));
				}
			}

		}

		return tickets; // These methods will split the product list
	} // Into tickets and services separately

	public static ArrayList<Service> getAllServices() {
		ArrayList<Product> products = ProductReader();
		ArrayList<Service> services = new ArrayList<Service>();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof Service) {
				services.add((Service) products.get(i));
			}

		}
		return services;
	}

	// Main method to check values of lists

	public static void main(String[] args) {
		int i = 0;
		ArrayList<Person> people = PeopleReader();
		ArrayList<Customer> customers = CustomerReader();

		ArrayList<Airport> airports = AirportReader();

		ArrayList<Ticket> tickets = getAllTickets();
		ArrayList<Service> services = getAllServices();

		ArrayList<Invoice> invoices = InvoiceReader();

		for (i = 0; i < people.size(); i++) {
			people.get(i).printPerson();
		}

		for (i = 0; i < customers.size(); i++) {
			customers.get(i).printCustomer();
		}

		for (i = 0; i < airports.size(); i++) {
			airports.get(i).printAirport();
		}

		for (i = 0; i < tickets.size(); i++) {
			tickets.get(i).printProduct();
		}

		for (i = 0; i < services.size(); i++) {
			services.get(i).printProduct();
		}

		for (i = 0; i < invoices.size(); i++) {

			invoices.get(i).printInvoice();
		}

	}

	// Method to read the invoice file and create a list of Invoice objects

	public static ArrayList<Invoice> InvoiceReader() {
		ArrayList<Person> people = PeopleReader();
		ArrayList<Customer> customers = CustomerReader(); // Data from customer
															// file

		ArrayList<Ticket> ticketList = getAllTickets(); // Separated lists of
														// ticket and services
		ArrayList<Service> serviceList = getAllServices();

		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		int i = 0;
		int j = 0;
		int k = 0;
		Scanner iScanner = null;
		String invoiceString = "";

		try {
			iScanner = new Scanner(new File("data/Invoices.dat"));
			int numInvoices = iScanner.nextInt();
			iScanner.nextLine();
			for (i = 0; i < numInvoices; i++) {

				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				ArrayList<Service> services = new ArrayList<Service>();

				invoiceString = iScanner.nextLine();
				String invoiceInfo[] = invoiceString.split(";");
				String invoiceProducts[] = invoiceInfo[4].split(",");
				int numProducts = invoiceProducts.length;

				String invoiceCode = invoiceInfo[0];
				String customerCode = invoiceInfo[1];
				String salespersonCode = invoiceInfo[2];
				String invoiceDate = invoiceInfo[3];
				String PNR = Invoice.generatePNR();
				Invoice invoice = new Invoice(invoiceCode, invoiceDate, PNR);

				for (k = 0; k < customers.size(); k++) {
					if (customerCode.equalsIgnoreCase(customers.get(k)
							.getCustomerCode())) {
						invoice.setCustomer(customers.get(k));
					}
				}

				if (salespersonCode.equalsIgnoreCase("online")) {
					invoice.setSalesperson(new OnlineSalesperson());
				} else {
					for (k = 0; k < people.size(); k++) {
						if (salespersonCode.equalsIgnoreCase(people.get(k)
								.getPersonCode())) {
							invoice.setSalesperson(people.get(k));
						}
					}

					 //invoice.setTickets(tickets);
					 //invoice.setServices(services);
				}

				// String productInfo[] = null;

				for (j = 0; j < numProducts; j++) {
					String productInfo[] = invoiceProducts[j].split(":");

					for (k = 0; k < ticketList.size(); k++) { // This will check
																// if an element
																// of the
																// invoice
																// references a
																// ticket
						if (productInfo[0].equals(ticketList.get(k)
								.getProductCode())) {
							// Readable String[]
							// Scanner ticketScanner = new
							// Scanner(invoiceProducts[j]);
							Ticket currentTicket = ticketList.get(k);
							String travelDate = productInfo[1];
							int numPassengers = Integer
									.parseInt(productInfo[2]);
							String ticketNote = productInfo[productInfo.length-1];

							currentTicket.setTravelDate(travelDate);
							currentTicket.setTicketNote(ticketNote);

							// tickets.add(currentTicket);

							for (int l = 0; l < numPassengers; l++) {
								Person currentPassenger = new Person();
								PassengerData passenger;
								String seatNO = productInfo[3 + (5 * l)];
								String passengerCode = productInfo[4 + (5 * l)];
								String passengerID = productInfo[5 + (5 * l)];
								int age = Integer
										.parseInt(productInfo[6 + (5 * l)]);
								String nationality = productInfo[7 + (5 * l)];

								for (int m = 0; m < people.size(); m++) {
									if (passengerCode.equals(people.get(m)
											.getPersonCode())) {
										currentPassenger = people.get(m);
									}

								}

								passenger = new PassengerData(seatNO,
										passengerID, age, nationality,
										currentPassenger);
								currentTicket.getPassengers().add(passenger);

							}

							tickets.add(currentTicket);
						}
						invoice.setTickets(tickets);

					}

					for (k = 0; k < serviceList.size(); k++) {
						if (productInfo[0].equals(serviceList.get(k)
								.getProductCode())) {
							Service currentService = serviceList.get(k);

							if (currentService instanceof Insurance) {
								Insurance ticketInsurance = (Insurance) currentService;
								int quantity = Integer.parseInt(productInfo[1]);
								ticketInsurance.setQuantity(quantity);
								services.add(ticketInsurance);
								for (int l = 0; l < tickets.size(); l++) {
									if (productInfo[2].equals(tickets.get(l)
											.getProductCode())) {
										tickets.get(l).setInsurance(
												ticketInsurance);
									}

								}
							}

							if (currentService instanceof CheckedBaggage) {
								CheckedBaggage additionalBaggage = (CheckedBaggage) currentService;
								String baggageTicket = additionalBaggage
										.getTicketCode();
								int quantity = Integer.parseInt(productInfo[1]);
								additionalBaggage.setQuantity(quantity);
								services.add(additionalBaggage);
								for (int l = 0; l < tickets.size(); l++) {
									if (baggageTicket.equals(tickets.get(l)
											.getProductCode())) {
										tickets.get(l).setAdditionalBaggage(
												additionalBaggage);
									}
								}

							}

							if (currentService instanceof SpecialAssistance) {
								SpecialAssistance assistance = (SpecialAssistance) currentService;
								String assistancePerson = productInfo[1];
								// String assisstancCode = productInfo[0];
								// for(int l=0;l<serviceList.size();l++){
								// if(assistanceCode)
								// }
								services.add(assistance);
								for (int l = 0; l < tickets.size(); l++) {
									for (int m = 0; m < tickets.get(l)
											.getPassengers().size(); m++) {
										if (assistancePerson
												.equals(tickets.get(l)
														.getPassengers().get(m)
														.getPassenger()
														.getPersonCode())) {
											tickets.get(l).getPassengers()
													.get(m).getPassenger()
													.setAssistance(assistance);
										}
									}

								}

							}

							if (currentService instanceof Refreshment) {
								Refreshment refreshment = (Refreshment) currentService;
								String refreshmentCode = productInfo[0];
								int quantity = Integer.parseInt(productInfo[1]);
								refreshment.setQuantity(quantity);
								if (refreshmentCode.equals(serviceList.get(k)
										.getProductCode())) {
									services.add(refreshment);
								}

							}

						}
						invoice.setServices(services);
					}
					

				}
				invoices.add(invoice);
			}
		} catch (IOException invoiceException) {
			System.out.println("Invalid File");
			invoiceException.printStackTrace();
		} finally {
			iScanner.close();
		}

		return invoices;
	}

	public static void AirportXML(ArrayList<Airport> airports) {
		int i = 0;
		XStream xstream = new XStream();

		xstream.alias("airport", Airport.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Airports.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<airports>\n");
		for (i = 0; i < airports.size(); i++) {
			pw.print(xstream.toXML(airports.get(i)) + "\n");
		}

		pw.print("</airports>\n");
		pw.close();

		System.out.println("XML generated at 'data/Airports.xml'");
	}
		
	
			

	public static void ProductXML(ArrayList<Product> products) {
		int i = 0;
		XStream xstream = new XStream();

		xstream.processAnnotations(Products.class);
		xstream.processAnnotations(Ticket.class);
		xstream.processAnnotations(StandardTicket.class);
		xstream.processAnnotations(OffSeasonTicket.class);
		xstream.processAnnotations(AwardTicket.class);
		xstream.processAnnotations(Service.class);
		xstream.processAnnotations(Refreshment.class);
		xstream.processAnnotations(SpecialAssistance.class);
		xstream.processAnnotations(Insurance.class);
		xstream.processAnnotations(CheckedBaggage.class);

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Products.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Products wrapper = new Products();

		wrapper.setProducts(products);

		String xml = xstream.toXML(wrapper);

		pw.print(xml);

		pw.close();

		System.out.println("XML generated at 'data/Products.xml'");
	}

}
