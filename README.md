In chapter 3 of the book we're using the H2 in memory database for data persistence.
In order to access the database and verify that the inserts are working, we access the following URL:

http://localhost:8080/h2-console

At this point the JDBC URL should be 
jdbc:h2:mem:tacocloud

if it isn't this, change it to this