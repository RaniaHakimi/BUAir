# BUAir (CS411 Final Project)
Welcome to our Airline Ticket Reservation web-application! Due to the COVID-19 pandemic, getting flights to travel back home have become increasingly limited as well as expensive, espeically for students at BU. Our group looks to tackle that problem by creating our own airline system known as BUAir. BUAir is a student only airline reservation system which assists students in booking flights up to 7 days in advance from Boston to their homes.

## Group Members
Rania Hakimi  
Gabriel Sechkin  
Akshey Nischal  
Yash Bengali  
Junaid Qasim  

## Built With
+ [Java](https://docs.oracle.com/en/java/)
+ [Bootstrap](https://getbootstrap.com/)
+ [JSP](https://www.wideskills.com/jsp/jsp-document)
+ [JDBC](https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/guide/jdbc/index.html)
+ [Servlet](https://tomcat.apache.org/tomcat-5.5-doc/servletapi/)
+ [MySQL](https://docs.oracle.com/cd/E17952_01/mysql-8.0-en/index.html)

## Getting Started (Dependencies)
Getting the environment set up and downloading all the needed dependencies may take some time, but we have included some brief instructions below.
1. Open Eclipse and start a new Dynamic Web Application. In the top right corner of eclipse, ensure that you are using "Java EE" and not "Java"; you may need to set up a Java EE if not found.
2. Right click your project. Under properties -> Build Path -> Libraries, we will need 5 things: 
    1. java.servlet-api-3.0.1.jar (Download from Maven)
    2. mysql-connector-java-8.0.15.jar (Download from Maven)
    3. servlet-api-2.5.jar (Download from Maven)
    4. JRE System Library (You will most likely have this already)
    5. Apache Tomcat (Download from Oracle and you must configure this as a server runtime)
3. Download MySQL and mySQL Workbench (workbench is optional but makes working with the database easier). Set up a user and password, keeping in mind that the root and pass that you use will need to be used in your code when creating the connection (leave the default url as 3306). Update any instance of root or pass with your respective details.
4. Run the attached SQL script in Workbench to initialize the tables in the database
5. Run the project by right-clicking in eclipse and select "run as -> Run on Server (Apache Tomcat)

**Troubleshooting** If you receive a database connection error, you may want to also put your mysql-connector jar file under WEB-INF -> lib which is an Eclipse auto-generated folder when creating a Java dynamic web app. There are many resources online for debugging any other bugs that might arise.

## License
[MIT](https://choosealicense.com/licenses/mit/)
