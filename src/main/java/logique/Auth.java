package logique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
        String password = request.getParameter("password");

        // Establish the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "");

            // Search for the provided credentials in the "admins" table
            String query = "SELECT * FROM Admins WHERE nom = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Entry exists in "admins" table
                response.sendRedirect("connects");
            }
            else {
            	  String query2 = "SELECT * FROM Users WHERE nom = ? AND pwd = ?";
                  PreparedStatement statement2 = connection.prepareStatement(query2);
                  statement2.setString(1, nom);
                  statement2.setString(2, password);
                  ResultSet resultSet2 = statement2.executeQuery();
                  if (resultSet2.next()) {
                      response.sendRedirect("sortie");
                   // Get the current session
                      HttpSession session = request.getSession();

                      // Store data in the session attribute
                      session.setAttribute("nom", nom);

                  }else {
                      request.getRequestDispatcher("Admin.jsp").forward(request, response);
                  }

                // Entry doesn't exist in "admins" table
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
	}

}
