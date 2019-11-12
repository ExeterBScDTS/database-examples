import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * A servlet that returns HTML content
*/
public class DbConnectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=MESSAGES_TESTDB;user=JavaUser;password=java$12345";
        //String connectionUrl = "jdbc:sqlserver://localhost:1433";

        try{
            Connection con = DriverManager.getConnection(connectionUrl); 
            out.print("<p>Database connection: " + con + "</p>");
        }
        catch(SQLException e) {
            out.print("<p>Database exception.</p>");
            e.printStackTrace();
        }
    }
} 