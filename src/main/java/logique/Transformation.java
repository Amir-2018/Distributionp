package logique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Transformation {
	private String code ; 
	private String sortie ;
	private String entree ; 
	private int qte ;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Transformation(String code, String sortie, String entree, int qte) {
		super();
		this.code = code;
		this.sortie = sortie;
		this.entree = entree;
		this.qte = qte;
	}




	public String getEntree() {
		return entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getSortie() {
		return sortie;
	}

	public void setSortie(String sortie) {
		this.sortie = sortie;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}


	

	public Transformation() {}
	
	public boolean deleteTransformationById(String code) {
	    try {
	        // Load the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish the database connection
	        String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        Connection connection = DriverManager.getConnection(url, "root", "");

	        // Create a prepared statement for executing the delete query
	        String deleteQuery = "DELETE FROM transformation WHERE code = ?";
	        PreparedStatement statement = connection.prepareStatement(deleteQuery);

	        // Set the ID parameter in the prepared statement
	        statement.setString(1, code);

	        // Execute the delete statement
	        int rowsAffected = statement.executeUpdate();

	        // Close the statement and connection
	        statement.close();
	        connection.close();

	        // Return true if a row was affected (successful deletion), false otherwise
	        return rowsAffected > 0;
	    } catch (ClassNotFoundException e) {
	        System.out.println("Failed to load MySQL JDBC driver.");
	    } catch (SQLException e) {
	        System.out.println("Failed to connect to the database: " + e.getMessage());
	    }

	    // Return false in case of any exceptions or failures
	    return false;
	}
	
		  
	private static final String NUMBERS = "0123456789";
	   private static final int MAX_LENGTH = 11;
		    public static String generateRandomCode() {
		    	
		        Random random = new Random();
		        StringBuilder codeBuilder = new StringBuilder();

		        int length = random.nextInt(MAX_LENGTH) + 1;
		        for (int i = 0; i < length; i++) {
		            int randomIndex = random.nextInt(NUMBERS.length());
		            char randomChar = NUMBERS.charAt(randomIndex);
		            codeBuilder.append(randomChar);
		        }

		        return codeBuilder.toString();
		}
	


}

