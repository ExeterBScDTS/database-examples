
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlProcedureDemo {

    public static void main(String[] args) {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;database=MESSAGES_TESTDB;user=JavaUser;password=java$12345";

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

            System.out.println("\nConnection:\n\t" + con + "\n");

            // Get the current date and time
            java.util.Date time = new java.util.Date();
            System.out.println(time);
            // Convert to an SQL compatible object
            Timestamp msg_time = new Timestamp(time.getTime());
            System.out.println(msg_time);

            CallableStatement pr_create_msg = 
                con.prepareCall("{call pr_create_msg(?, ?, ?, ?, ?)}");
            
            pr_create_msg.setInt("item", 2);
            pr_create_msg.setInt("user", 2);
            pr_create_msg.setNull("reply", 0);
            pr_create_msg.setTimestamp("date", msg_time);
            pr_create_msg.setString("txt", "Everything else is fine.");

            pr_create_msg.execute();

            System.out.println("All done.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}