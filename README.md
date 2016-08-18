# CS144
UCLA CS144 (Winter 2016)
Project Overview

Your project is to build a Web service and a Web site that allow users to search and navigate eBay bidding data. Your system manages all of its data at the back-end in the MySQL database and supports browsing and searching interfaces over the Internet. These functionalities will be provided by integrating MySQL, Apache Tomcat, Lucene and Axis2 packages. We will also give you a considerable amount of real data from the eBay Web site in XML format to populate your system.

Part 1: System setup and warm-up (10%)
In this part, you will have to set up a provided virtual machine on your computer, which will be the main development and testing platform. Once your virtual machine is setup and running, you will have to interact with MySQL to populate data and query them. You will also have to implement a simple Java program to brush up on Java programming.

Part 2: Data transformation and loading
You will be given a large volume of real data from the eBay web site in XML files. You will examine the data and design a good relational schema for it. You will then write a Java program to transform the data from its XML form into MySQL's load file format, conforming to your relational schema. You will create your schema in the MySQL database in the virtual machine, load your transformed data, and test it by running some SQL queries over it.

Part 3: Building auction search Web service (25%)
You will need to provide a SOAP-based Web service interface that allows users to search and retrieve auction items in the eBay data. Your Web service will support queries both on numeric attributes (like price) and textual attributes (like product description) and allow simple keyword-based searches on textual attributes. To support keyword searches, you will need to build inverted indexes using Apache Lucene. You will also use Apache Tomcat together with Apache Axis2 to make your search interface available as a Web service.

Part 4: Building auction search Web site (25%)
You will build a Web site that allows users to browse and search items within the eBay data. The Web site will be constructed by integrating three Web services, the eBay Web service that you built in Part 3, Google Map service to display the location of auction items and Google suggest service to help users formulate queries. You will have to learn Java servlet programming for Apache Tomcat and have a chance to learn AJAX-style programming to build dynamic Web user interfaces.

Part 5: Enabling secure access (15%)
You will update your Web site such that the site is secure to use even when you transmit sensitive information.
