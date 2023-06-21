package logique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class GetEntreeSortie
 */
@WebServlet("/getES")
public class GetEntreeSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEntreeSortie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    // Load the MySQL JDBC driver
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    // Establish the database connection
		    String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    Connection connection = DriverManager.getConnection(url, "root", "");

		    // Create a statement object to execute the query
		    String sqlQuery = "SELECT * FROM dsortiestock";

		    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

		    // Execute the SQL query
		    ResultSet resultSet = preparedStatement.executeQuery();

		    // Create a list to hold the SortieStock objects
		    List<DSortieStock> sortieStockList = new ArrayList<>();

		 // Process the query results
		 // Process the query results
		    while (resultSet.next()) {
		        // Retrieve the data from the result set and create a SortieStock object
		        DSortieStock sortieStock = new DSortieStock();
		        sortieStock.setAnnee(resultSet.getInt("annee"));
		        sortieStock.setNum(resultSet.getInt("num"));
		        sortieStock.setArtn(Integer.parseInt(resultSet.getString("artn")));
		        sortieStock.setArticle(resultSet.getString("article"));
		        sortieStock.setDesig(resultSet.getString("desig"));

		        // Add the SortieStock object to the list
		        sortieStockList.add(sortieStock);
		    }
		    
		    String sqlQuery2 = "SELECT * FROM dentreestock";
		    PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2);

		    // Execute the SQL query
		    ResultSet resultSet2 = preparedStatement2.executeQuery();
		    
		    List<DEntreeStock> entreeStockList = new ArrayList<>();

		 // Process the query results
		 while (resultSet2.next()) {
		     // Retrieve the data from the result set and create a DEntreeStock object
		     DEntreeStock entreeStock = new DEntreeStock();
		     entreeStock.setAnnee(resultSet2.getInt("annee"));
		     entreeStock.setNum(resultSet2.getInt("num"));
		     entreeStock.setArtn(Integer.parseInt(resultSet2.getString("artn")));
		     entreeStock.setArticle(resultSet2.getString("article"));
		     entreeStock.setDesig(resultSet2.getString("desig"));

		     // Add the DEntreeStock object to the list
		     entreeStockList.add(entreeStock);
		 }
		   


		    request.setAttribute("sortieStockList", sortieStockList);
		    request.setAttribute("entreeStockList", entreeStockList);



		    // Close the result set, statement, and connection
		    resultSet.close();
		    preparedStatement.close();
		    resultSet2.close();
		    preparedStatement2.close();
		    connection.close();
		    request.getRequestDispatcher("EntreeSortie.jsp").forward(request, response);

		    // Set the list as an attribute of the request

		    // Forward the request to the "EntreeSortie" JSP
		} catch (ClassNotFoundException | SQLException e) {
		    // Handle any exceptions that occur
		    e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
