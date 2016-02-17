DROP TABLE IF EXISTS InvoiceProduct,PessengerTicket,Invoice,Product,Customer,Airport,Email,Person,Address;

# This database has been supplied with test data for simple and easily accesible testing and troubleshooting. Save this base file.


CREATE TABLE Address(
AddressID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Street			VARCHAR(45)	NOT NULL,
City			VARCHAR(60) NOT NULL,
State			VARCHAR(60) NOT NULL,
ZipCode			VARCHAR(20) ,
Country			VARCHAR(45) NOT NULL
);


INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1060 West Addison Street','Chicago','IL','60613','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('123 N 1st Street','Omaha','NE','68116','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('8753 West 3rd Ave.','Dallas','TX','75001','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('123 Friendly Street','Ottawa','ON','K1A 0G9','Canada');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1 Wall Street','New York','NY','10005-0012','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('321 Bronx Street','New York','NY','10004','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('301 Front St W','Toronto','ON','M5V 2T6','Canada');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1 Blue Jays Way','Toronto','ON','M5V 1J1','Canada');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('Campos El290','Mexico City','FD','','Mexico');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('Avery Hall','Lincoln','NE','68503','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('126-01 Roosevelt Ave','Flushing','NY','11368','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1 MetLife Stadium Dr','East Rutherford','NJ','07073','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1 E 161st St','Bronx','NY','10451','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('700 E Grand Ave','Chicago','IL','60611','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('333 W 35th St','Chicago','IL','60616','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('800 West 7th Street','Albuquerque','NM','87105','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('123 Cabo San Lucas','Los Cabos','BCS','','Mexico');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('2400 W Adams St','Lincoln','NE','68524','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('10000 W O`Hare Ave','Chicago','IL','660666','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('5700 S Cicero Ave','Chicago','IL','60638','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('JFK Expy & S Cargo Rd','Jamaica','NY','11430','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('3400 E Sky Harbor Blvd','Phoenix','AZ','85034','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1 World Way','Los Angeles','CA','90045','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('1701 Airport Blvd','San Jose','CA','95110','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('6000 N Terminal Pkwy','Atlanta','GA','30320','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('8500 Pea Blvd','Denver','CO','80249','USA');

INSERT INTO Address (Street,City,State,ZipCode,Country)
VALUES ('4300 Glumack Drive','St. Paul','MN','55111','USA');

CREATE TABLE Person(
PersonID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
PersonCode		VARCHAR(10) 	NOT NULL,
PersonLastName	VARCHAR(45) NOT NULL,
PersonFirstName	VARCHAR(45) NOT NULL,
AddressID_FK    INT			NOT NULL,
PhoneNumber		VARCHAR(12)	,
FOREIGN KEY (`AddressID_FK`) REFERENCES Address(AddressID)
);

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('944c','Castro','Starlin',1,'142-241-6024');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('306a','Sampson','Brock',2,'402-241-6024');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('55bb','O`Brien','Miles',3,'402-251-2114');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('2342','O`Brien','Miles',4,'212-241-6024');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('aef1','Gekko','Gordon',5,'122-141-1032');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('321f','Fox','Bud',6,'');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('ma12','Sveum','Dale',1,'541-354-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('321nd','Hartnell','Wiliam',1,'323-541-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('nf32a','Troughton','Patrick',1,'');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('321na','Pertwee','Jon',7,'323-541-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('231','Baker','Tom',8,'210-124-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('6doc','Hurndall','Richard',9,'124-366-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('321dr','Baker','C.',10,'122-141-1032');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('1svndr','McCoy','Sylvester',11,'');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('123lst','McGann','Paul',12,'102-147-3522');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('nwdoc1','Eccleston','Chris',13,'210-124-3210');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('2ndbestd','Tennant','David',14,'320-141-3142');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('wrddocr','Smith','Matt',15,'141-325-1032');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('bbchar','Ehrmantraut','Kaylee',16,'587-417-1032');

INSERT INTO Person (PersonCode,PersonLastName,PersonFirstName,AddressID_FK,PhoneNumber)
VALUES ('doc05','Davison','Peter',17,'122-141-1032');





CREATE TABLE Email(
EmailID			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Email			VARCHAR(255),
Person_FK		INT NOT NULL,
FOREIGN KEY (`Person_FK`) REFERENCES Person(PersonID)
);

INSERT INTO Email (Email,Person_FK)
VALUES ('scastro@cubs.com,starlin_castro13@gmail.com',1);

INSERT INTO Email (Email,Person_FK)
VALUES ('brock_f_sampson@gmail.com,bsampson@venture.com',2);

INSERT INTO Email (Email,Person_FK)
VALUES ('obrien@ds9.com,obrien@enterprise.gov',3);

INSERT INTO Email (Email,Person_FK)
VALUES ('bfox@gmail.com,csheen@crazy.net',4);

INSERT INTO Email (Email,Person_FK)
VALUES ('sveum@cubs.com',5);

INSERT INTO Email (Email,Person_FK)
VALUES ('whartnell@doctors.com,dr@who.com',6);

INSERT INTO Email (Email,Person_FK)
VALUES ('ptroug@cse.unl.edu,ptrou32@unl.edu',7);

INSERT INTO Email (Email,Person_FK)	
VALUES ('jpet@whofan.com',8);

INSERT INTO Email (Email,Person_FK)
VALUES ('famousdoc@who.com,tbaker@cse.unl.edu,mostfamous@whovian.com,thedoctor@bbc.com',9);

INSERT INTO Email (Email,Person_FK)
VALUES ('rhurndall@cse.unl.edu,richard@unl.edu',10);

INSERT INTO Email (Email,Person_FK)
VALUES ('dr@baker.com',11);

INSERT INTO Email (Email,Person_FK)
VALUES ('slyguy@hotmail.com,mccoy@whofan.com',12);

INSERT INTO Email (Email,Person_FK)
VALUES ('pmcgann@mlb.com,foo@bar.com,pmc@unl.edu',13);

INSERT INTO Email (Email,Person_FK)
VALUES ('newguy@whovian.com',14);

INSERT INTO Email (Email,Person_FK)
VALUES ('actor@shakespeare.com,tdavid@unl.edu',15);

INSERT INTO Email (Email,Person_FK)
VALUES ('msmith@who.com,thedoc@cse.unl.eduAddress',16);







CREATE TABLE Airport(
AirportID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
AirportCode		VARCHAR(3)	NOT NULL,
AirportName		VARCHAR(60)	NOT	NULL,
AddressID_FK	INT NOT NULL,
Coordinates		VARCHAR(60)	NOT	NULL,
PassengerFacilityFee	DOUBLE	NOT NULL,
FOREIGN KEY	(`AddressID_FK`) REFERENCES	Address(AddressID)
);




INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('LNK','Lincoln Municipal',18,'40,50,96,40',0);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('ORD','Chicago O`Hare International',19,'41,50,87,37',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('MDW','Chicago Midway International',20,'41,50,87,37',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('JFK','John F. Kennedy International',21,'40,47,73,58',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('PHX','Phoenix Sky Harbor International',22,'33,29,112,4',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('LAX','Los Angeles International Airport',23,'34,3,118,15',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('SJC','San Jose International Airport',24,'37,20,121,53',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('ATL','Hartsfield Jackson Atlanta International Airport',25,'33,45,84,23',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('DEN','Denver International Airport',26,'39,45,105,0',4.50);

INSERT INTO Airport (AirportCode,AirportName,AddressID_FK,Coordinates,PassengerFacilityFee)
VALUES ('MSP','Minneapolis Saint Paul International Airport',27,'44,59,93,14',4.50);


CREATE TABLE Product(
ProductID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
ProductCode		VARCHAR(4),
ProductType		VARCHAR(2),
ArriveAirportID_FK	INT,
DepartureAirportID_FK	INT,
DepartureTime	TIME,
ArriveTime		TIME,
FlightNo		VARCHAR(15),
FlightClass		VARCHAR(2),
AircraftType	VARCHAR(45),
SeasonStartDate DATE,
SesonEndDate	DATE,
Rebate			DOUBLE,
PointsPerMile	DOUBLE,
TicketCode		INT,
InsuranceName	VARCHAR(45),
AgeClass		VARCHAR(15),
CostPerMile		DOUBLE,
TypeOfService	VARCHAR(45),
RefreshmentName	VARCHAR(45),
RefreshmentCost	DOUBLE,
FOREIGN KEY (`ArriveAirportID_FK`) REFERENCES Airport(AirportID),
FOREIGN KEY (`DepartureAirportID_FK`) REFERENCES Airport(AirportID)  

);

INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('ff23','SI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NationalInsurance','0-20',0.07,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1255','TS',6,8,'09:30:00','15:55:00','NA2222','BC','Boeing757',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('90fa','SC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1255,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1238','TS',1,8,'06:00:00','09:08:00','NA4889','EC','CRJ900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('xer2','SS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'WheelChair',NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1240','TO',1,8,'06:00:00','09:08:00','NA4889','EC','CRJ900','2015-10-07','2016-01-29',0.1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1241','TS',8,1,'21:15:00','22:42:00','NA3871','EC','CRJ900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('ff25','SI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Progressive','69-102',0.1,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1234','TO',8,1,'21:15:00','22:42:00','NA4871','EP','CRJ900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0.2',NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('90fb','SC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1241,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1256','TS',6,8,'09:30:00','15:55:00','NA2222','EP','Boeing757',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1257','TA',6,8,'10:15:00','18:13:00','NA101','EP','Boeing737-900ER',NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1258','TS',8,6,'10:50:00','12:50:00','NA1555','EC','Boeing737-900ER',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('32f4','SR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Italian Buffet',18);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1260','TA',2,5,'09:09:00','11:06:00','N725','EP','Airbus-A319',NULL,NULL,NULL,110,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('xer4','SS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BabyBassinet',NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1261','TS',2,5,'09:09:00','11:06:00','N725','BC','Airbus-A319',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('ff24','SI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Progressive','55-68',0.09,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1262','TO',5,2,'14:24:00','19:56:00','N2000','BC','Airbus-A320','2015-09-01','2016-10-12',0.15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('1263','TO',5,2,'07:10:00','12:42:00','N488','EC','Airbus-A320','2015-09-15','2016-12-29',0.25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('fg23','SI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Progressive','21-54',0.08,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('fh23','SI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NationalInsurance','21-60',0.085,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('90fc','SC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1260,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('xer1','SS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PrioritySeating',NULL,NULL);
INSERT INTO `Product` (`ProductCode`,`ProductType`,`ArriveAirportID_FK`,`DepartureAirportID_FK`,`DepartureTime`,`ArriveTime`,`FlightNo`,`FlightClass`,`AircraftType`,`SeasonStartDate`,`SesonEndDate`,`Rebate`,`PointsPerMile`,`TicketCode`,`InsuranceName`,`AgeClass`,`CostPerMile`,`TypeOfService`,`RefreshmentName`,`RefreshmentCost`) VALUES ('31f4','SR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Labbat-Beer-15oz',4.5);


CREATE TABLE Customer(
CustomerID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
CustomerCode	VARCHAR(4)	NOT NULL,
CustomerType	VARCHAR(1)	NOT NULL,
PrimaryContactID_FK		INT NOT NULL,
CustomerName	VARCHAR(45)	NOT NULL,
AirlineMiles	INT(12),
FOREIGN KEY (`PrimaryContactID_FK`) REFERENCES	Person(PersonID)
);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C001','C',11,'Cinco Consultants',0);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C002','G',1,'Stark Associates',36000);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C003','V',2,'United States Postal Service',36000);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C004','C',6,'Mr Steve Odelay',4170000);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C005','G',19,'Vandelay Industries',0);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C006','V',13,'University of Nebaska-Lincoln',6857441);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C007','C',12,'Venture Club',null);

INSERT INTO Customer (CustomerCode,CustomerType,PrimaryContactID_FK,CustomerName,AirlineMiles)
VALUES ('C008','G',7,'Mr Gregory Smith',101111);



CREATE TABLE Invoice(
InvoiceID		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
InvoiceCode		VARCHAR(7) NOT NULL,
SalesPersonID_FK		INT,
CustomerID_FK	INT NOT NULL,
InvoiceDate		DATE,
FOREIGN KEY (`SalesPersonID_FK`) REFERENCES Person(PersonID),
FOREIGN KEY (`CustomerID_FK`) REFERENCES Customer(CustomerID)

);


INSERT INTO `Invoice` (`InvoiceID`,`InvoiceCode`,`SalesPersonID_FK`,`CustomerID_FK`,`InvoiceDate`) VALUES (1,'INV001',16,1,'2015-09-13');
INSERT INTO `Invoice` (`InvoiceID`,`InvoiceCode`,`SalesPersonID_FK`,`CustomerID_FK`,`InvoiceDate`) VALUES (2,'INV002',NULL,2,'2015-09-12');
INSERT INTO `Invoice` (`InvoiceID`,`InvoiceCode`,`SalesPersonID_FK`,`CustomerID_FK`,`InvoiceDate`) VALUES (3,'INV003',17,4,'2015-12-17');
INSERT INTO `Invoice` (`InvoiceID`,`InvoiceCode`,`SalesPersonID_FK`,`CustomerID_FK`,`InvoiceDate`) VALUES (4,'INV004',12,3,'2016-01-08');



CREATE TABLE PessengerTicket(
PessengerTicketID			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
InvoiceID_FK			INT,
TicketID_FK				INT,
TravelDate				DATE,
Seat				VARCHAR(255),
PersonID_FK			INT  NOT NULL,
IndetityNumber		VARCHAR(255),
PassengerAge		INT(3),
PassengerNationality			VARCHAR(255),
TicketNote						VARCHAR(50),
FOREIGN KEY (`TicketID_FK`) REFERENCES Product(ProductID),
FOREIGN KEY (`InvoiceID_FK`)	REFERENCES Invoice(InvoiceID)

);


INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (1,1,2,'2015-09-13','12A',7,'FA1234',23,'USA','Operated by Skywest');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (2,2,6,'2015-09-12','32C',10,'FS12345',43,'Mexico','None');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (3,2,7,'2015-09-22','12C',10,'FS12345',43,'Mexico','None');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (4,3,6,'2015-12-17','17G',3,'NB3111',72,'Bolivia','CareNeeded');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (5,4,15,'2016-01-08','32F',3,'AB3111',34,'Korea','Operated by Southwest');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (6,4,15,'2016-01-08','21A',4,'AB1222',31,'Korea','Operated by Southwest');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (7,4,15,'2016-01-08','32D',11,'PP988877',12,'Korea','Operated by Southwest');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (8,4,19,'2016-03-28','10B',11,'PP988877',12,'Korea','None');
INSERT INTO `PessengerTicket` (`PessengerTicketID`,`InvoiceID_FK`,`TicketID_FK`,`TravelDate`,`Seat`,`PersonID_FK`,`IndetityNumber`,`PassengerAge`,`PassengerNationality`,`TicketNote`) VALUES (9,4,19,'2016-03-28','10C',4,'AB1222',31,'Korea','None');



CREATE TABLE InvoiceProduct(
InvoiceProductID				INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
InvoiceID_FK					INT,
ProductID_FK					INT,
QuantityCheckedBaggage			INT,
QuantityInsurance				INT,
QuantityRefreshments			INT,
SpecialAssistance				INT,
PessengerTicket_FK				INT,
FOREIGN KEY (`PessengerTicket_FK`)  REFERENCES PessengerTicket(PessengerTicketID),
FOREIGN KEY (`SpecialAssistance`) 	REFERENCES Person(PersonID),
FOREIGN KEY (`InvoiceID_FK`) REFERENCES Invoice(InvoiceID),
FOREIGN KEY (`ProductID_FK`) REFERENCES 	Product(ProductID)
);



INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (1,1,3,3,NULL,NULL,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (2,1,22,NULL,2,NULL,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (3,2,14,NULL,NULL,3,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (4,2,16,NULL,NULL,NULL,10);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (5,3,24,NULL,NULL,NULL,3);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (6,3,25,NULL,NULL,1,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (7,4,21,NULL,3,NULL,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (8,4,23,2,NULL,NULL,NULL);
INSERT INTO `InvoiceProduct` (`InvoiceProductID`,`InvoiceID_FK`,`ProductID_FK`,`QuantityCheckedBaggage`,`QuantityInsurance`,`QuantityRefreshments`,`SpecialAssistance`) VALUES (9,4,21,NULL,2,NULL,NULL);



#SELECT * FROM InvoiceProduct;






