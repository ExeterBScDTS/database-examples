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

            // See 
            // https://docs.microsoft.com/en-us/sql/connect/jdbc/spatial-data-types-sample?view=sql-server-2017

            String geoWKT = "POINT(-0.6 5.0)";
            Geography geogWKT = Geography.STGeomFromText(geoWKT, 4326);
            int id = 3;
            try (SQLServerPreparedStatement pstmt = 
            (SQLServerPreparedStatement) con.prepareStatement("insert into " + "REPORTS" + " values (?,?,?,?,?,?)");) {
                pstmt.setGeography(1, geogWKT);
                pstmt.setObject(2, new java.util.Date()); // DATE_REPORTED
                pstmt.setObject(3, null); // DATE_OBSERVED
                pstmt.setObject(4, "anon"); // REPORTED_BY
                pstmt.setObject(5, 40.0); // DEPTH
                pstmt.setObject(6, "600.5"); // DIAMETER - note use of string
                pstmt.execute(); 
            }
            
            try (SQLServerResultSet rs = (SQLServerResultSet) stmt.executeQuery("select * from " + "REPORTS");){
            // Iterate through the data in the result set and display it.               
                while(rs.next()){
                    // Note column numbers are not same as before as now have ID in col 1.
                    System.out.println("ID: " + rs.getObject(1));
                    System.out.println("Geography data: " + rs.getGeography(2));
                    System.out.println("Date reported: " + rs.getObject(3));
                    System.out.println("Date observed: " + rs.getObject(4));
                    System.out.println("Reported by: " + rs.getObject(5));
                    // Could use getDouble() or getFloat() here
                    System.out.println("Depth: " + rs.getObject(6)); 
                    System.out.println("Diameter: " + rs.getObject(7));
                }
            }
            System.out.println("All done.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}