Family Tree. This is example of a web application using JSP, Servlet & Jdbc.


Servlet + JSP +  Filter + JSP EL + JDBC.

The principle when programming Servlet + JSP
1.	Never allow users to directly access to your JSP page.
2.	JSP is only considered as the place to display interface.
3.	Servlet acts as the controller of the application flows and program logical processing.
4.	Open the JDBC connection and transaction management in Filter (Optional).

Prepare database
•	You need to run scripts to create some tables and necessary data for this example.
	o	/database/script/
Create WebApp Project in IntelliJ;
•	Java Enterprise – Web Application
	o	UML/
Configuring the runtime environment.
•	The application needs to run on a Webserver, such as Tomcat Server.
