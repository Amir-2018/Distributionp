package logique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/transfert")
public class TransfromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TransfromServlet() {
        super();
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        Transformation tr = new Transformation();
        String code = tr.generateRandomCode();
        String sortie = request.getParameter("sortie");
        String entree = request.getParameter("entree");
        String qte = request.getParameter("qte");
        int qtet = Integer.parseInt(qte);
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the database connection
        String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "");

        // Create a prepared statement to execute SQL queries
        String sqlQuery = "SELECT * FROM article";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);

        // Execute the SQL query and obtain the result set
        ResultSet resultSet = statement.executeQuery();

        // Insert the Transformation into the table
     // Assuming you have a database connection object named "connection"

     // Prepare the SELECT query to check if the transformation already exists
     String selectQuery = "SELECT COUNT(*) FROM transformation WHERE sortie = ? AND entree = ?";
     PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
     selectStatement.setString(1, sortie);
     selectStatement.setString(2, entree);

     // Execute the SELECT query
     ResultSet resultSet4 = selectStatement.executeQuery();
     resultSet4.next();
     int count = resultSet4.getInt(1);

     // Check if a matching transformation already exists
     if (count > 0) {
         // Transformation already exists, handle the error or take appropriate action
         // For example, you can throw an exception, show an error message, or skip the insertion
    	 String message = "Existe Déjà";
    	 request.getSession().setAttribute("message", message);
    	 response.sendRedirect("connects");
     } else {
         // Transformation does not exist, proceed with the insertion into the database
         String insertQuery = "INSERT INTO transformation (code,sortie, entree, qte) VALUES (?, ?, ?,?)";
         PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
         insertStatement.setString(1, code);
         insertStatement.setString(2, sortie);
         insertStatement.setString(3, entree);
         insertStatement.setInt(4, qtet);

         // Execute the INSERT query to insert the new transformation
         int rowsAffected = insertStatement.executeUpdate();
         if (rowsAffected > 0) {
             // Insertion successful
        	 String message = "Ajouter avec succée";
        	 request.getSession().setAttribute("message", message);
        	 response.sendRedirect("connects");

         } else {
        	 String message = "Probléme avec transformation";
        	 request.getSession().setAttribute("message", message);
        	 response.sendRedirect("connects");
         }
     }

     // Close the database resources
     resultSet.close();
     selectStatement.close();


        // Clean up resources
        resultSet4.close();
        statement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}


