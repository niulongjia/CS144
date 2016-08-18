### UCLA CS144 (Winter 2016) Project Overview
=====
The project is to build a Web service and a Web site that allow users to search and navigate eBay bidding data. Your system manages all of its data at the back-end in the MySQL database and supports browsing and searching interfaces over the Internet. These functionalities will be provided by integrating MySQL, Apache Tomcat, Lucene and Axis2 packages. A considerable amount of real data from the eBay Web site in XML format will be given to populate the system.

### Part 1: System setup and warm-up (10%)
=====
### Part 2: Data transformation and loading (25%)
=====
A large volume of real data from the eBay web site in XML files will be given. My job is to write a Java program to transform the data from its XML form into MySQL's load file format, conforming to my relational schema. I will also create my schema in the MySQL database in the virtual machine, load my transformed data, and test it by running some SQL queries over it.

### Part 3: Building auction search Web service (25%)
=====
I will need to provide a SOAP-based Web service interface that allows users to search and retrieve auction items in the eBay data. My Web service will support queries both on numeric attributes (like price) and textual attributes (like product description) and allow simple keyword-based searches on textual attributes. To support keyword searches, I will need to build inverted indexes using Apache Lucene. I will also use Apache Tomcat together with Apache Axis2 to make my search interface available as a Web service.

### Part 4: Building auction search Web site (25%)
=====
I will build a Web site that allows users to browse and search items within the eBay data. The Web site will be constructed by integrating three Web services, the eBay Web service that I built in Part 3, Google Map service to display the location of auction items and Google suggest service to help users formulate queries. I will learn Java servlet programming for Apache Tomcat and have a chance to learn AJAX-style programming to build dynamic Web user interfaces.

### Part 5: Enabling secure access (15%)
=====
I will update my Web site such that the site is secure to use even sensitive information is transmitted.

### Authors
=====
LONGJIA NIU <br/>
GENA XIE
