package logique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Affecter
 */
@WebServlet("/affecter")
public class Affecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affecter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "");

            // Retrieve the selected values from the request parameters
            String selectedNom = request.getParameter("nom");
            String selectedLieu = request.getParameter("lieu");
 
            // Insert the selected values into the UsersDepot table
            String insertQuery = "INSERT INTO usersdepot (nom, lieu) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, selectedNom);
            insertStatement.setString(2, selectedLieu);
            insertStatement.executeUpdate();

            // Redirect to the "/DepotUsers" page
            response.sendRedirect("DepotUsers");
            insertStatement.close();
        
        	

        } 
        
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        	response.sendRedirect("DepotUsers");
            e.printStackTrace();
        }
        
    }

}
