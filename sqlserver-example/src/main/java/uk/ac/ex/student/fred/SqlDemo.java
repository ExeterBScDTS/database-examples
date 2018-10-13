package uk.ac.ex.student.fred;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.Geography;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.microsoft.sqlserver.jdbc.SQLServerResultSet;


public class SqlDemo {

    public static void main(String[] args) {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=POTHOLE_REPORTS;user=PotholeAdmin;password=p0tHo13Z$1";

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM REPORTS";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println("A report");
                //System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
            }

            // See 
            // https://docs.microsoft.com/en-us/sql/connect/jdbc/spatial-data-types-sample?view=sql-server-2017

            String geoWKT = "POINT(-0.6 5.0)";
            Geography geogWKT = Geography.STGeomFromText(geoWKT, 4326);
            int id = 3;
            try (SQLServerPreparedStatement pstmt = (SQLServerPreparedStatement) con
                    .prepareStatement("insert into " + "REPORTS" + " values (?,?,null,null,null,null,null)");) {
                pstmt.setObject(1, id);
                pstmt.setGeography(2, geogWKT);
                pstmt.execute();

                SQLServerResultSet rs2 = (SQLServerResultSet) stmt.executeQuery("select * from " + "REPORTS");
                rs2.next();

                System.out.println("Geography data: " + rs2.getGeography(2));
            }

            System.out.println("All done.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}