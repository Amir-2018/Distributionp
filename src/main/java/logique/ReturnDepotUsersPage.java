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

/**
 * Servlet implementation class ReturnDepotUsersPage
 */
@WebServlet("/DepotUsers")
public class ReturnDepotUsersPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnDepotUsersPage() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "");

            String selectQuery = "SELECT nom FROM Users";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            // Create a list to hold the user names
            List<String> userNames = new ArrayList<>();

            // Process the result set
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                userNames.add(nom);
            }
            
            String cmd = "SELECT desig FROM LieuStockage";
            PreparedStatement slt = connection.prepareStatement(cmd);
            ResultSet res = slt.executeQuery();

            // Create a list to hold the desig values
            List<String> desigList = new ArrayList<>();

            // Process the result set
            while (res.next()) {
                String desig = res.getString("desig");
                desigList.add(desig);
            }

            String sql2 = "SELECT * FROM UsersDepot";
            PreparedStatement st2 = connection.prepareStatement(sql2);

         // Execute the query and retrieve the result set
         ResultSet res2 = st2.executeQuery();

         // Create a list to store the UserDepot objects
         List<UserDepot> usersDepotList = new ArrayList<>();

         // Iterate through the result set and create UserDepot objects
         while (res2.next()) {
             // Assuming UserDepot has constructor and getter/setter methods
             UserDepot userDepot = new UserDepot();
             userDepot.setNom(res2.getString("nom")) ; 
             userDepot.setLieu(res2.getString("lieu"));
             usersDepotList.add(userDepot);
         }

         // Close the result set, statement, and connection (Remember to handle exceptions properly)
         resultSet.close();
         st2.close();
         connection.close();

         // Set the list as an attribute in the request object
         request.setAttribute("usersDepotList", usersDepotList);
            
            request.setAttribute("desigList", desigList);


            request.setAttribute("userNames", userNames);


            // Forward the request to the "Depot.jsp" page
            request.getRequestDispatcher("Depot.jsp").forward(request, response);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
