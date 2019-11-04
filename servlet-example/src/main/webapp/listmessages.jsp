<%@page import="java.sql.*,java.sql.DriverManager"%>
<html>
<body>
<%
String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=MESSAGES_TESTDB;user=JavaUser;password=java$12345";
        try (Connection con = DriverManager.getConnection(connectionUrl);) {

            // To call a stored procedure, create the statement, set any parameters,
            // then execute the query.
            CallableStatement pr_list_msg =  con.prepareCall("{call pr_list_msg(?)}");
            pr_list_msg.setInt("item", 2);
            ResultSet rs = pr_list_msg.executeQuery();

            // Now iterate through the result set.
            while(rs.next()){
                out.println("<p>" 
                // If you need a particular type, e.g. int or String use the appropriate get method,
                // see https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html
                + rs.getObject(1) 
                + "," + rs.getObject(2) 
                + "," + rs.getObject(3)
                + "," + rs.getObject(4)
                + "," + rs.getObject(5)
                + "," + rs.getObject(6)
                + "</p>");
            }
            con.close();
        }
        //catch(Exception e){ // Catching exceptions is good, but can make it harder to find bugs.
        //                    // Try uncommenting these lines and then add an error somewhere above.
        //    out.println("I'm sorry, I can't do that.");
        //}
%>
</body>
</html>
