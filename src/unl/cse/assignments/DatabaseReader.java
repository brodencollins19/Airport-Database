package unl.cse.assignments;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

import com.airamerica.*;
import com.airamerica.utils.CustomList;

//import edu.unl.cse.model.Member;
import unl.cse.assignments.DatabaseInfo;

public class DatabaseReader {

	public static void main(String args[]) {
		int i = 0;
		// ArrayList<Person> people = DBPeopleReader();
		// ArrayList<Customer> customers = DBCustomerReader();
		// ArrayList<Airport> airports = DBAirportReader();
		// ArrayList<Ticket> tickets = getTickets();
		// ArrayList<Service> services = getServices();
		
		
		//ArrayList<Invoice> invoices = DBInvoiceReader();
		CustomList<Invoice> invoices = DBInvoiceReader();

		// for (i = 0; i < people.size(); i++) {
		// people.get(i).printPerson();
		// }

		// for (i = 0; i < customers.size(); i++) {
		// customers.get(i).printCustomer();
		// }

		// for (i = 0; i < airports.size(); i++) {
		// airports.get(i).printAirport();
		// }

		// for (i = 0; i < tickets.size(); i++) {
		// tickets.get(i).printProduct();
		// }

		// for (i = 0; i < services.size(); i++) {
		// services.get(i).printProduct();
		// }
		System.out.print(":D");
		for (i = 0; i < invoices.size(); i++) {
			invoices.get(i).printInvoice();
		}
	}

	// Gets a person from the database
	public static Person getPerson(String personCode) {

		// int PersonID = 0;
		// String PersonLastName = "";
		// String PersonFristName = "";
		// String PhoneNumber = "";
		Address address = new Address();
		ArrayList<String> emails = new ArrayList<String>();

		Connection conn = DatabaseInfo.getConnection();
		Person p = new Person();

		String query = "select * from Person where PersonCode like ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				p.setFirstName(rs.getString("PersonFirstName"));
				p.setLastName(rs.getString("PersonLastName"));
				p.setPhoneNo(rs.getString("PhoneNumber"));
				p.setPersonCode(rs.getString("PersonCode"));
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "select * from Person join Email on Person.PersonID = Email.Person_FK where PersonCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			while (rs.next()) {

				emails.add(new String(rs.getString("Email")));
			}

			p.setEmails(emails);

			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "select * from Address join Person on Address.AddressID = Person.AddressID_FK where personCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				address.setCountry(rs.getString("Country"));
				address.setState(rs.getString("State"));
				address.setStreet(rs.getString("Street"));
				address.setZipCode(rs.getString("ZipCode"));
				address.setCity(rs.getString("City"));

			}
			p.setAddress(address);

			ps.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return p;

	}

	// Returns all people from the database

	public static ArrayList<Person> DBPeopleReader() {
		ArrayList<Person> people = new ArrayList<Person>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Person";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			// stmt.executeUpdate(query)
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				people.add(getPerson(rs.getString("PersonCode")));
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return people;

	}

	// Gets a specific Customer from the database

	public static Customer getCustomer(String customerCode) {
		ArrayList<Person> people = DBPeopleReader();
		int i = 0;
		Person primaryContact = new Person();

		GeneralCustomer genCustomer = new GeneralCustomer();
		GovernmentCustomer govCustomer = new GovernmentCustomer();
		CorporateCustomer corCustomer = new CorporateCustomer();

		String type = "";
		String primaryContactCode = "";

		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Customer where CustomerCode like ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			if (rs.next()) {

				if (rs.getString("CustomerType").equals("G")) {
					type = "G";
					genCustomer.setCustomerCode(rs.getString("CustomerCode"));
					genCustomer.setName(rs.getString("CustomerName"));
					genCustomer.setAirlineMiles(rs.getInt("AirlineMiles"));
					genCustomer.setType(rs.getString("CustomerType"));
				}

				else if (rs.getString("CustomerType").equals("C")) {
					type = "C";
					corCustomer.setCustomerCode(rs.getString("CustomerCode"));
					corCustomer.setName(rs.getString("CustomerName"));
					corCustomer.setAirlineMiles(rs.getInt("AirlineMiles"));
					corCustomer.setType(rs.getString("CustomerType"));
				}

				else {
					type = "V";
					govCustomer.setCustomerCode(rs.getString("CustomerCode"));
					govCustomer.setName(rs.getString("CustomerName"));
					govCustomer.setAirlineMiles(rs.getInt("AirlineMiles"));
					govCustomer.setType(rs.getString("CustomerType"));
				}
				
				

			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Person join Customer on Person.PersonID = Customer.PrimaryContactID_FK where customerCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				for (i = 0; i < people.size(); i++) {
					if (rs.getString("PersonCode").equals(
							people.get(i).getPersonCode())) {
						primaryContact = people.get(i);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (type.equals("G")) {
			genCustomer.setPrimaryContact(primaryContact);
			return genCustomer;
		} else if (type.equals("C")) {
			corCustomer.setPrimaryContact(primaryContact);
			return corCustomer;
		}

		else {
			govCustomer.setPrimaryContact(primaryContact);
			return govCustomer;
		}

	}

	public static ArrayList<Customer> DBCustomerReader() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Customer";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				customers.add(getCustomer(rs.getString("CustomerCode")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return customers;
	}

	public static Airport getAirport(String airportCode) {
		Airport a = new Airport();
		Address address = new Address();

		Connection conn = DatabaseInfo.getConnection();
		Person p = new Person();
		String coordinates;

		int latDeg;
		int latMin;
		int lonDeg;
		int lonMin;

		String query = "select * from Airport where AirportCode like ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, airportCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				a.setName(rs.getString("AirportName"));
				a.setAirportCode(rs.getString("AirportCode"));
				a.setPassengerFacilityFee(rs.getDouble("PassengerFacilityFee"));

				coordinates = rs.getString("Coordinates");
				String[] splitCoordinates = coordinates.split(",");

				latDeg = Integer.parseInt(splitCoordinates[0]);
				latMin = Integer.parseInt(splitCoordinates[1]);
				lonDeg = Integer.parseInt(splitCoordinates[2]);
				lonMin = Integer.parseInt(splitCoordinates[2]);

				a.setLatDegs(latDeg);
				a.setLatMins(latMin);
				a.setLongDegs(lonDeg);
				a.setLongMins(lonMin);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "select * from Address join Airport on Address.AddressID = Airport.AddressID_FK where airportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, airportCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				address.setCountry(rs.getString("Country"));
				address.setState(rs.getString("State"));
				address.setStreet(rs.getString("Street"));
				address.setZipCode(rs.getString("ZipCode"));
				address.setCity(rs.getString("City"));

			}
			a.setAddress(address);

			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return a;
	}

	public static ArrayList<Airport> DBAirportReader() {
		ArrayList<Airport> airports = new ArrayList<Airport>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Airport";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			// stmt.executeUpdate(query)
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				airports.add(getAirport(rs.getString("AirportCode")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return airports;
	}

	public static Ticket getTicket(String productCode) {
		Ticket t = new Ticket();

		String type = "";

		// String flightClass = "";
		// String flightNO = "";
		// String aircraftType = "";
		// String depTime = "";
		// String arrTime = "";
		// String ticketCode;

		Airport arrAirport;
		Airport depAirport;

		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Product where ProductCode like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				type = rs.getString("ProductType");
				switch (type) {
				case "TS":

					StandardTicket s = new StandardTicket();

					s.setTicketCode(rs.getString("ProductCode"));
					s.setFlightClass(rs.getString("FlightClass"));
					s.setFlightNo(rs.getString("FlightNO"));
					s.setAircraftType(rs.getString("AircraftType"));
					s.setArrTime(String.valueOf(rs.getTime("ArriveTime")));
					s.setDepTime(String.valueOf(rs.getTime("DepartureTime")));

					t = s;
					break;

				case "TO":
					OffSeasonTicket o = new OffSeasonTicket();

					o.setTicketCode(rs.getString("ProductCode"));
					o.setFlightClass(rs.getString("FlightClass"));
					o.setFlightNo(rs.getString("FlightNO"));
					o.setAircraftType(rs.getString("AircraftType"));
					o.setArrTime(String.valueOf(rs.getTime("ArriveTime")));
					o.setDepTime(String.valueOf(rs.getTime("DepartureTime")));
					o.setSeasonStartDate(rs.getDate("SeasonStartDate")
							.toString());
					o.setSeasonEndDate(rs.getDate("SesonEndDate").toString());
					o.setRebate(rs.getDouble("Rebate"));

					t = o;
					break;

				case "TA":
					AwardTicket a = new AwardTicket();

					a.setTicketCode(rs.getString("ProductCode"));
					a.setFlightClass(rs.getString("FlightClass"));
					a.setFlightNo(rs.getString("FlightNO"));
					a.setAircraftType(rs.getString("AircraftType"));
					a.setArrTime(String.valueOf(rs.getTime("ArriveTime")));
					a.setDepTime(String.valueOf(rs.getTime("DepartureTime")));

					int pointsPerMile = (int) rs.getDouble("PointsPerMile");

					a.setPointsPerMile(pointsPerMile);

					t = a;
					break;

				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Airport join Product on Airport.AirportID = Product.DepartureAirportID_FK where ProductCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				depAirport = (getAirport(rs.getString("AirportCode")));
				t.setDepartureAirport(depAirport);
				t.setDepAirportCode(depAirport.getAirportCode());
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Airport join Product on Airport.AirportID = Product.ArriveAirportID_FK where ProductCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				arrAirport = (getAirport(rs.getString("AirportCode")));
				t.setArrivalAirport(arrAirport);
				t.setArrAirportCode(arrAirport.getAirportCode());
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return t;

	}

	public static Service getService(String productCode) {
		Service s = new Service();
		String type = "";

		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Product where ProductCode like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {

				type = rs.getString("ProductType");
				switch (type) {
				case "SI":
					Insurance i = new Insurance();
					i.setProductCode(rs.getString("ProductCode"));
					i.setName(rs.getString("InsuranceName"));
					i.setAgeClass(rs.getString("AgeClass"));
					i.setCostPerMile(rs.getDouble("CostPerMile"));

					s = i;
				case "SS":
					SpecialAssistance sa = new SpecialAssistance();

					sa.setType(rs.getString("TypeOfService"));
					s = sa;

				case "SC":
					CheckedBaggage b = new CheckedBaggage();

					b.setProductCode(rs.getString("ProductCode"));
					b.setTicketCode(rs.getInt("TicketCode"));

					s = b;

				case "SR":
					Refreshment r = new Refreshment();
					r.setProductCode(rs.getString("ProductCode"));

					r.setName(rs.getString("RefreshmentName"));
					r.setCost(rs.getDouble("RefreshmentCost"));
					s = r;

				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	public static ArrayList<Service> getServices() {
		ArrayList<Service> services = new ArrayList<Service>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Product";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			// stmt.executeUpdate(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString("ProductType").equals("SI")
						|| rs.getString("ProductType").equals("SC")
						|| rs.getString("ProductType").equals("SR")
						|| rs.getString("ProductType").equals("SS")) {
					services.add(getService(rs.getString("ProductCode")));
				}

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return services;
	}

	public static ArrayList<Ticket> getTickets() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Product";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			// stmt.executeUpdate(query)
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString("ProductType").equals("TS")
						|| rs.getString("ProductType").equals("TO")
						|| rs.getString("ProductType").equals("TA")) {
					tickets.add(getTicket(rs.getString("ProductCode")));
				}

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tickets;
	}

	public static Invoice getInvoice(String invoiceCode) {
		// ArrayList<Ticket> ticketList = getTickets();
		// ArrayList<Service> serviceList = getServices();

		Invoice inv = new Invoice();

		int i = 0;

		ArrayList<Ticket> invoiceTickets = new ArrayList<Ticket>();
		ArrayList<Service> invoiceServices = new ArrayList<Service>();

		String invoiceDate = "";

		int invoiceID = 0;

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
				invoiceDate = rs.getString("InvoiceDate");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Person join Invoice on Person.PersonID = Invoice.SalesPersonID_FK where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				Person salesPerson = getPerson(rs.getString("PersonCode"));
				inv.setSalesperson(salesPerson);
			} else {
				OnlineSalesperson salesperson = new OnlineSalesperson();
				inv.setSalesperson(salesperson);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// Sets a customer for an invoice
		query = "Select * from Customer join Invoice on Customer.CustomerID = Invoice.CustomerID_FK where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				Customer c = getCustomer(rs.getString("CustomerCode"));
				inv.setCustomer(c);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// int ProductID = 0;

		// Gets all services for an invoice
		String productType = "";

		query = "select * from Product join InvoiceProduct on Product.ProductID = InvoiceProduct.ProductID_FK where InvoiceID_FK like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			rs = ps.executeQuery();

			while (rs.next()) {
				productType = rs.getString("ProductType");
				switch (productType) {
				case "SI":
					Insurance ins = new Insurance();
					ins.setProductCode(rs.getString("ProductCode"));
					ins.setQuantity(rs.getInt("QuantityInsurance"));
					ins.setName(rs.getString("InsuranceName"));
					ins.setAgeClass(rs.getString("AgeClass"));
					ins.setCostPerMile(rs.getDouble("CostPerMile"));

					invoiceServices.add(ins);
					break;

				case "SC":
					CheckedBaggage baggage = new CheckedBaggage();
					baggage.setProductCode(rs.getString("ProductCode"));
					baggage.setQuantity(rs.getInt("QuantityCheckedBaggage"));
					baggage.setTicketCode(rs.getInt("TicketCode"));

					invoiceServices.add(baggage);
					break;

				case "SS":
					SpecialAssistance assistance = new SpecialAssistance();
					assistance.setProductCode(rs.getString("ProductCode"));
					assistance.setType(rs.getString("TypeOfService"));

					invoiceServices.add(assistance);
					break;

				case "SR":
					Refreshment r = new Refreshment();
					r.setCost(rs.getDouble("RefreshmentCost"));
					r.setProductCode(rs.getString("ProductCode"));
					r.setQuantity(rs.getInt("QuantityRefreshments"));
					r.setName(rs.getString("RefreshmentName"));

					invoiceServices.add(r);
					break;
				}
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// Check below here

		// String ticketCode = "";
		String insQuery = "";

		// Gets all the tickets for a particular invoice
		query = "Select  Distinct ProductCode,ProductType,ArriveAirportID_FK,DepartureAirportID_FK,DepartureTime,ArriveTime,FlightNo,FlightClass,AircraftType,SeasonStartDate,SesonEndDate,Rebate,InvoiceProductID_FK, TicketID_FK from Product join PessengerTicket on Product.ProductID = PessengerTicket.TicketID_FK join Invoice on Invoice.InvoiceID = PessengerTicket.InvoiceID_FK where InvoiceID like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			rs = ps.executeQuery();
			while (rs.next()) {     // trouble point for inv002 and inv003
				Ticket t = getTicket(rs.getString("ProductCode"));
				int ticketID = rs.getInt("TicketID_FK");
				t.setAircraftType(rs.getString("AircraftType"));;
				//invoiceTickets.add(getTicket(rs.getString("ProductCode")));

				// bagQuery = ""
				insQuery = "Select Distinct ProductID,ProductCode,ProductType,InsuranceName,AgeClass,CostPerMile,InvoiceProductID,ProductID_FK,QuantityInsurance,TicketID_FK From Product join InvoiceProduct on Product.ProductID = InvoiceProduct.ProductID_FK join PessengerTicket on InvoiceProduct.InvoiceProductID = PessengerTicket.InvoiceProductID_FK join Invoice on Invoice.InvoiceID = PessengerTicket.InvoiceID_FK where InvoiceID like ? and TicketID_FK like ?";
				PreparedStatement insPS = conn.prepareStatement(insQuery);
				insPS.setInt(1, invoiceID);
				insPS.setInt(2, ticketID);
				ResultSet insRS = insPS.executeQuery();
				if (insRS.next()) {
					Insurance ins = new Insurance();
					ins.setAgeClass(insRS.getString("AgeClass"));
					ins.setCostPerMile(insRS.getDouble("CostPerMile"));
					ins.setName(insRS.getString("InsuranceName"));
					;
					ins.setQuantity(insRS.getInt("QuantityInsurance"));
					ins.setProductCode(insRS.getString("ProductCode"));

					 t.setInsurance(ins);

				}

				// Ticket t = getTicket(rs.getString("ProductCode"));
				invoiceTickets.add(t);

			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// Assign Passengers to the tickets
		// String ticketCode = "";
		PreparedStatement assistancePS = null;
		ResultSet assistanceRS = null;
		String assistanceQuery = "";
		for (i = 0; i < invoiceTickets.size(); i++) {
			query = "Select  Distinct *  From Product join PessengerTicket on Product.ProductID = PessengerTicket.TicketID_FK join Invoice on Invoice.InvoiceID = PessengerTicket.InvoiceID_FK join Person on Person.PersonID = PessengerTicket.PersonID_FK where InvoiceID like ? and ProductCode Like ?";
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, invoiceID);
				ps.setString(2, invoiceTickets.get(i).getTicketCode());
				rs = ps.executeQuery();

				while (rs.next()) {
					assistanceQuery = "Select  Distinct *  From InvoiceProduct join Product on InvoiceProduct.ProductID_FK = Product.ProductID join Person on Person.PersonID = InvoiceProduct.SpecialAssistance where PersonCode like ?";
					assistancePS = conn.prepareStatement(assistanceQuery);
					assistancePS.setString(1, rs.getString("PersonCode"));
					assistanceRS = assistancePS.executeQuery();

					PassengerData p = new PassengerData();
					p.setAge(rs.getInt("PassengerAge"));
					p.setIDNumber(rs.getString("IndetityNumber"));
					p.setNationality(rs.getString("PassengerNationality"));
					p.setSeatNO(rs.getString("Seat"));
					Person person = getPerson(rs.getString("PersonCode"));
					if (assistanceRS.next()) {
						SpecialAssistance s = new SpecialAssistance();
						s.setType(assistanceRS.getString("TypeOfService"));
						s.setProductCode(assistanceRS.getString("ProductCode"));
						person.setAssistance(s);
					}

					p.setPassenger(person);

					if (rs.getString("ticketNote").equals("None")) {
						invoiceTickets.get(i).setTicketNote("");
					} else {
						invoiceTickets.get(i).setTicketNote(
								rs.getString("TicketNote"));
					}

					invoiceTickets.get(i).setTravelDate(
							rs.getString("TravelDate"));

					invoiceTickets.get(i).getPassengers().add(p);

				}

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		int k = 0;

		for (i = 0; i < invoiceServices.size(); i++) {

			Service currentService = invoiceServices.get(i);
			if (currentService instanceof CheckedBaggage) {
				CheckedBaggage baggage = (CheckedBaggage) currentService;

				for (k = 0; k < invoiceTickets.size(); k++) {
					if (baggage.getTicketCode().equals(
							invoiceTickets.get(k).getProductCode())) {
						invoiceTickets.get(k).setAdditionalBaggage(baggage);
					}
				}
			}
		//	if(currentService instanceof Refreshment){
			//	invoiceServices.add(currentService);
			//}
		}

		// PreparedStatement insurancePS = null;
		// ResultSet insuranceRS = null;

		inv.setInvoiceDate(invoiceDate);
		inv.setServices(invoiceServices);
		inv.setTickets(invoiceTickets);
		inv.setInvoiceDate(invoiceDate);
		inv.setInvoiceCode(invoiceCode);

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inv;

	}

	
	
	
	
	public static CustomList<Invoice> DBInvoiceReader(){
		CustomList<Invoice> invoices = new CustomList<Invoice>();
		Connection conn = DatabaseInfo.getConnection();

		String query = "select * from Invoice";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			// stmt.executeUpdate(query)
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				invoices.add(getInvoice(rs.getString("InvoiceCode")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return invoices;

	}
}


