package com.airamerica.interfaces;

import java.sql.*;

import org.joda.time.DateTime;

import com.airamerica.*;

import unl.cse.assignments.DatabaseInfo;

/* Assignment 5 - (Phase IV) */
/* NOTE: Donot change the package name or any of the method signatures.
 *  
 * There are 23 methods in total, all of which need to be completed as a 
 * bare minimum as part of the assignment.You can add additional methods 
 * for testing if you feel.
 * 
 * It is also recommended that you write a separate program to read
 * from the .dat files and test these methods to insert data into your 
 * database.
 * 
 * Donot forget to change your reports generation classes to read from 
 * your database instead of the .dat files.
 */

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */

public class InvoiceData {

	/**
	 * Method that removes every person record from the database
	 */

	public static void removeAllPersons() { // Good

		Connection conn = DatabaseInfo.getConnection();

		String query = "";

		Statement stmt = null;

		query = "DELETE from Person";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

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

	}

	/**
	 * Method to add a person record to the database with the provided data.
	 */
	public static void addPerson(String personCode, String firstName,
			String lastName, String phoneNo, String street, String city, // Good
			String state, String zip, String country) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int AddressID = 0;

		String addressCheck = "Select * from Address where Street like ? AND City like ? AND State like ? AND ZipCode like ? AND Country like ?";
		try {
			ps = conn.prepareStatement(addressCheck);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);

			rs = ps.executeQuery();
			if (rs.next()) {
				AddressID = rs.getInt("AddressID");
			} else {
				String insertAddress = "insert into Address (Street, City, State, ZipCode, Country) values (?,?,?,?,?)";
				PreparedStatement insAddress = conn
						.prepareStatement(insertAddress);
				insAddress.setString(1, street);
				insAddress.setString(2, city);
				insAddress.setString(3, state);
				insAddress.setString(4, zip);
				insAddress.setString(5, country);

				insAddress.executeUpdate();

				String getNewAddress = "Select * from Address where Street like ? AND City like ? AND State like ? AND ZipCode like ? AND Country like ?";
				PreparedStatement getAddress = conn
						.prepareStatement(getNewAddress);
				getAddress.setString(1, street);
				getAddress.setString(2, city);
				getAddress.setString(3, state);
				getAddress.setString(4, zip);
				getAddress.setString(5, country);

				rs = getAddress.executeQuery();

				if (rs.next()) {
					AddressID = rs.getInt("AddressID");
				}
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String insertPerson = "insert into Person (`PersonCode`,`PersonLastName`,`PersonFirstName`,`AddressID_FK`,`PhoneNumber`) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(insertPerson);

			ps.setString(1, personCode);
			ps.setString(2, lastName);
			ps.setString(3, firstName);
			ps.setInt(4, AddressID);
			ps.setString(5, phoneNo);

			ps.executeUpdate();

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
	}

	/**
	 * Method that removes every airport record from the database
	 */
	public static void removeAllAirports() { // Good
		Connection conn = DatabaseInfo.getConnection();

		String query = "";

		Statement stmt = null;

		query = "DELETE from Airport";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

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
	}

	/**
	 * Method to add a airport record to the database with the provided data.
	 */
	public static void addAirport(String airportCode, String name,
			String street, String city, String state,
			String zip, // Good
			String country, int latdegs, int latmins, int londegs, int lonmins,
			double passengerFacilityFee) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String coordinates = "";
		int AddressID = 0;

		String addressCheck = "Select * from Address where Street like ? AND City like ? AND State like ? AND ZipCode like ? AND Country like ?";
		try {
			ps = conn.prepareStatement(addressCheck);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);

			rs = ps.executeQuery();
			if (rs.next()) {
				AddressID = rs.getInt("AddressID");
			} else {
				String insertAddress = "insert into Address (Street, City, State, ZipCode, Country) values (?,?,?,?,?)";
				PreparedStatement insAddress = conn
						.prepareStatement(insertAddress);
				insAddress.setString(1, street);
				insAddress.setString(2, city);
				insAddress.setString(3, state);
				insAddress.setString(4, zip);
				insAddress.setString(5, country);

				insAddress.executeUpdate();

				String getNewAddress = "Select * from Address where Street like ? AND City like ? AND State like ? AND ZipCode like ? AND Country like ?";
				PreparedStatement getAddress = conn
						.prepareStatement(getNewAddress);
				getAddress.setString(1, street);
				getAddress.setString(2, city);
				getAddress.setString(3, state);
				getAddress.setString(4, zip);
				getAddress.setString(5, country);

				rs = getAddress.executeQuery();

				if (rs.next()) {
					AddressID = rs.getInt("AddressID");
				}
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		coordinates = (String.valueOf(latmins) + ", " + String.valueOf(latdegs)
				+ ", " + String.valueOf(lonmins) + (", ") + String
				.valueOf(londegs));

		String insertAirport = "insert into Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee) values (?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(insertAirport);

			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setInt(3, AddressID);
			ps.setString(4, coordinates);
			ps.setDouble(5, passengerFacilityFee);

			ps.executeUpdate();

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

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		Person p = new Person();
		int personID = 0;

		String query = "select * from Person where personCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				personID = rs.getInt("PersonID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Email (Email,Person_FK) values(?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, personID);

			ps.executeUpdate();
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

	}

	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection conn = DatabaseInfo.getConnection();

		String query = "";

		Statement stmt = null;

		query = "DELETE from Customer";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

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
	}

	/**
	 * Method to add a customer record to the database with the provided data.
	 */
	public static void addCustomer(String customerCode, String customerType,
			String primaryContactPersonCode, String name, int airlineMiles) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int primaryContactID = 0;

		String type = "";

		if (customerType.equalsIgnoreCase("V")
				|| customerType.equalsIgnoreCase("Governmnet")
				|| customerType.equalsIgnoreCase("GovernmentCustomer")) {
			type = "V";
		} else if (customerType.equalsIgnoreCase("G")
				|| customerType.equalsIgnoreCase("General")
				|| customerType.equalsIgnoreCase("GeneralCustomer")) {
			type = "G";
		} else {
			type = "C";
		}

		String query = "Select * from Person where PersonCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, primaryContactPersonCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				primaryContactID = rs.getInt("PersonID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			ps.setString(2, type);
			ps.setInt(3, primaryContactID);
			ps.setString(4, name);
			ps.setInt(5, airlineMiles);

			ps.executeUpdate();
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

	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection(); // Good

		String query = "";

		Statement stmt = null;

		query = "DELETE from Product";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

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
	}

	/**
	 * Adds an standardTicket record to the database with the provided data.
	 */
	public static void addStandardTicket(String productCode,
			String depAirportCode, String arrAirportCode, String depTime,
			String arrTime, String flightNo, String flightClass,
			String aircraftType) {

		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		int depAirportID = 0;
		int arrAirportID = 0;

		// String depAirportCheck =
		// "Select * from Airport join Address on Airport.ad"

		String query = "Select * from Airport join Product on Airport.AirportID = Product.DepartureAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, depAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				depAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Airport join Product on Airport.AirportID = Product.ArriveAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, arrAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				arrAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Product (ProductCode,ProductType,ArriveAirportID_FK,DepartureAirportID_FK,DepartureTime,ArriveTime,FlightNo,FlightClass,AircraftType) values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "ST");
			ps.setInt(3, arrAirportID);
			ps.setInt(4, depAirportID);
			ps.setTime(5, Time.valueOf(depTime + ":00"));
			ps.setTime(6, Time.valueOf(arrTime + ":00"));
			ps.setString(7, flightNo);
			ps.setString(8, flightClass);
			ps.setString(9, aircraftType);

			ps.executeUpdate();

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
	}

	/**
	 * Adds an offSeasonTicket record to the database with the provided data.
	 */
	public static void addOffSeasonTicket(String productCode,
			String seasonStartDate, String seasonEndDate,
			String depAirportCode, String arrAirportCode, String depTime,
			String arrTime, String flightNo, String flightClass,
			String aircraftType, double rebate) {

		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		int depAirportID = 0;
		int arrAirportID = 0;

		String query = "Select * from Airport join Product on Airport.AirportID = Product.DepartureAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, depAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				depAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Airport join Product on Airport.AirportID = Product.ArriveAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, arrAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				arrAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Product (ProductCode,ProductType,ArriveAirportID_FK,DepartureAirportID_FK,DepartureTime,ArriveTime,FlightNo,FlightClass,AircraftType,SeasonStartDate,SesonEndDate,Rebate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "SO");
			ps.setInt(3, arrAirportID);
			ps.setInt(4, depAirportID);
			ps.setTime(5, Time.valueOf(depTime + ":00"));
			ps.setTime(6, Time.valueOf(arrTime + ":00"));
			ps.setString(7, flightNo);
			ps.setString(8, flightClass);
			ps.setString(9, aircraftType);
			ps.setDate(10, Date.valueOf(seasonStartDate));
			ps.setDate(11, Date.valueOf(seasonEndDate));
			ps.setDouble(12, rebate);

			ps.executeUpdate();

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
	}

	/**
	 * Adds an awardsTicket record to the database with the provided data.
	 */
	public static void addAwardsTicket(String productCode,
			String depAirportCode, String arrAirportCode, String depTime,
			String arrTime, String flightNo, String flightClass,
			String aircraftType, double pointsPerMile) {

		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		int depAirportID = 0;
		int arrAirportID = 0;

		String query = "Select * from Airport join Product on Airport.AirportID = Product.DepartureAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, depAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				depAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Airport join Product on Airport.AirportID = Product.ArriveAirportID_FK where AirportCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, arrAirportCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				arrAirportID = rs.getInt("AirportID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "set foreign_key_checks=0";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Product (ProductCode,ProductType,ArriveAirportID_FK,DepartureAirportID_FK,DepartureTime,ArriveTime,FlightNo,FlightClass,AircraftType,PointsPerMile) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "SA");
			ps.setInt(3, arrAirportID);
			ps.setInt(4, depAirportID);
			ps.setTime(5, Time.valueOf(depTime + ":00"));
			ps.setTime(6, Time.valueOf(arrTime + ":00"));
			ps.setString(7, flightNo);
			ps.setString(8, flightClass);
			ps.setString(9, aircraftType);
			ps.setDouble(10, pointsPerMile);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "set foreign_key_checks=1";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
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
	}

	/**
	 * Adds a CheckedBaggage record to the database with the provided data.
	 */
	public static void addCheckedBaggage(String productCode, String ticketCode) {
		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "insert into Product (ProductCode,TicketCode) values(?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, ticketCode);
			ps.executeUpdate();
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

	}

	/**
	 * Adds a Insurance record to the database with the provided data.
	 */
	public static void addInsurance(String productCode, String productName,
			String ageClass, double costPerMile) {

		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "insert into Product (ProductCode,InsuranceName,AgeClass,CostPerMile) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, productName);
			ps.setString(3, ageClass);
			ps.setDouble(4, costPerMile);
			ps.executeUpdate();
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

	}

	/**
	 * Adds a SpecialAssistance record to the database with the provided data.
	 */
	public static void addSpecialAssistance(String productCode,
			String assistanceType) {

		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "insert into Product (ProductCode,TypeOfService) values(?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, assistanceType);
			ps.executeUpdate();
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

	}

	/**
	 * Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name,
			double cost) {
		Connection conn = DatabaseInfo.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "insert into Product (ProductCode,RefreshmentName,RefreshmentCost) values(?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setDouble(3, cost);
			ps.executeUpdate();
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

	}

	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		Connection conn = DatabaseInfo.getConnection();

		String query = "";

		Statement stmt = null;
		ResultSet rs = null;

		query = "DELETE from Invoice";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

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
	}

	/**
	 * Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode,
			String salesPersonCode, String invoiceDate) {
		int salesPersonID = 0;
		int customerID = 0;

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "Select * from Person where PersonCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, salesPersonCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				salesPersonID = rs.getInt("PersonID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "select * from Customer where CustomerCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				customerID = rs.getInt("CustomerID");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into Invoice (InvoiceCode,SalesPersonID_FK,CustomerID_FK,InvoiceDate) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setInt(2, salesPersonID);
			ps.setInt(3, customerID);
			ps.setString(4, invoiceDate);

			ps.executeUpdate();

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
	}

	/**
	 * Adds a particular Ticket (corresponding to <code>productCode</code>) to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given additional details
	 */
	public static void addTicketToInvoice(String invoiceCode,
			String productCode, String travelDate, String ticketNote) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int productID = 0;
		int invoiceID = 0;
		int ticketID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from PessengerTicket where TicketNote like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, ticketNote);
			rs = ps.executeQuery();
			if (rs.next()) {
				ticketID = rs.getInt("TicketID_FK");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "set foreign_key_checks=0";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "insert into PessengerTicket (TicketID_FK,InvoiceID_FK,TravelDate,TicketNote,InvoiceProductID_FK) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ticketID);
			ps.setInt(2, invoiceID);
			ps.setString(3, travelDate);
			ps.setString(4, ticketNote);
			ps.setInt(5, productID);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "set foreign_key_checks=1";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
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
	}

	/**
	 * Adds a Passenger information to an invoice corresponding to the provided
	 * <code>invoiceCode</code>
	 */
	public static void addPassengerInformation(String invoiceCode,
			String productCode, String personCode, String identity, int age,
			String nationality, String seat) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceID = 0;
		int productID = 0;
		int personID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Person where PersonCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				personID = rs.getInt("PersonID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "set foreign_key_checks=0";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "insert into PessengerTicket (InvoiceID_FK,InvoiceProductID_FK,PersonID_FK,IndetityNumber,PassengerAge,PassengerNationality,Seat) values(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, personID);
			ps.setString(4, identity);

			ps.setInt(5, age);
			ps.setString(6, nationality);
			ps.setString(7, seat);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "set foreign_key_checks=1";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
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
	}

	/**
	 * Adds an Insurance Service (corresponding to <code>productCode</code>) to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of quantity and associated ticket information
	 */
	public static void addInsuranceToInvoice(String invoiceCode,
			String productCode, int quantity, String ticketCode) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int ticketID = 0;
		int productID = 0;
		int invoiceID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?"; // Ticket
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, ticketCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				ticketID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?"; // Ticket
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into InvoiceProduct join Product on InvoiceProduct.ProductID_FK = Product.ProductID (TicketID_FK,ProductID_FK,InvoiceID_FK,QuantityInsurance) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ticketID);
			ps.setInt(2, productID);
			ps.setInt(3, invoiceID);
			ps.setInt(4, quantity);
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

	}

	/**
	 * Adds a CheckedBaggage Service (corresponding to <code>productCode</code>)
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of quantity.
	 */
	public static void addCheckedBaggageToInvoice(String invoiceCode,
			String productCode, int quantity) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int invoiceID = 0;
		int productID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?"; // Ticket
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into InvoiceProduct(InvoiceID_FK,ProductID_FK,QuantityCheckedBaggage) values(?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);

			ps.executeUpdate();
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

	}

	/**
	 * Adds a SpecialAssistance Service (corresponding to
	 * <code>productCode</code>) to an invoice corresponding to the provided
	 * <code>invoiceCode</code> with the given number of quantity.
	 */
	public static void addSpecialAssistanceToInvoice(String invoiceCode,
			String productCode, String personCode) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int invoiceID = 0;
		int productID = 0;
		int personID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?"; // Ticket
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Person where PersonCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				personID = rs.getInt("PersonID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "insert into InvoiceProduct (InvoiceID_FK,ProductID_FK,SpecialAssistance";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, personID);
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

	}

	/**
	 * Adds a Refreshment service (corresponding to <code>productCode</code>) to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of quantity.
	 */
	public static void addRefreshmentToInvoice(String invoiceCode,
			String productCode, int quantity) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int invoiceID = 0;
		int productID = 0;

		String query = "Select * from Invoice where InvoiceCode like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Select * from Product where ProductCode like ?"; // Ticket
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productID = rs.getInt("ProductID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "Insert into InvoiceProduct(InvoiceID_FK,ProductID_FK,QuantityRefreshments)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setDouble(3, quantity);

			ps.executeUpdate();

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

	}

}
