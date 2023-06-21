package logique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class Article {
private int code ; 
public Article(int code, String desig) {
	super();
	this.code = code;
	this.desig = desig;
}
private String desig ;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getDesig() {
	return desig;
}
public void setDesig(String desig) {
	this.desig = desig;
}
public Article() {}
public boolean deleteTransformations() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the database connection
        String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "");

        // Create a prepared statement for executing the delete query
        String deleteQuery = "DELETE FROM transformation";
        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        // Execute the delete statement
        int rowsAffected = statement.executeUpdate();

        // Close the statement and connection
        statement.close();
        connection.close();

        // Return true if rows were affected (successful deletion), false otherwise
        return rowsAffected > 0;
    } catch (ClassNotFoundException e) {
        System.out.println("Failed to load MySQL JDBC driver.");
    } catch (SQLException e) {
        System.out.println("Failed to connect to the database: " + e.getMessage());
    }

    // Return false in case of any exceptions or failures
    return false;
}



}
