-To use your login details with the JDBC diver, Login details can be set in the "DatabaseInfo" class

-To run the program with local flat files in the "InvoiceReport" class, use the line: static ArrayList<Invoice> invoices = DataConverter.InvoiceReader();
 To run the application with a database, use the line: static CustomList<Invoice> invoices = DatabaseReader.DBInvoiceReader();

-Sample flat file data is provided in the data folder.

-The SQL script can be directly imported into a database and the zipped file can be imported as an eclipse project. 


-The SQL script has been pre-populated with test data so it can be imported and run immediately. A JDBC interface is used for updating the databse with custom data.
