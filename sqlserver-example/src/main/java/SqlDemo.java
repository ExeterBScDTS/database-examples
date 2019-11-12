
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlDemo {

    public static void main(String[] args) {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;user=JavaUser;password=java$12345";
        
        // Use this really simple connection URL if you just need to check if
        // a suitable driver is found.
        // String connectionUrl = "jdbc:sqlserver://localhost:1433";

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

            System.out.println("\nConnection:\n\t" + con + "\n");
            
            con.close();
            System.out.println("All done.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}