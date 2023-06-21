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
import javax.servlet.http.HttpSession;

@WebServlet("/choixentree")
public class ChoixEntree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChoixEntree() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    String sortieValue = request.getParameter("sortie");
		    String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    Connection connection = DriverManager.getConnection(url, "root", "");
		    String selectQuery = "SELECT * FROM transformation WHERE sortie = ?";
		    String selectQuery3 = "SELECT * FROM transformation";
		    PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
		    selectStatement.setString(1, sortieValue);
		    ResultSet resultSet2 = selectStatement.executeQuery();

		    
		    // 
		    
		    PreparedStatement selectStatement3 = connection.prepareStatement(selectQuery3);
		    ResultSet resultSet3 = selectStatement3.executeQuery();
		    
		    // Create a list to hold the transformations
		    List<Transformation> transformations = new ArrayList<>();
		    List<Transformation> transforms = new ArrayList<>();

		    while (resultSet3.next()) {
		        String sortie = resultSet3.getString("sortie");
		        String entree = resultSet3.getString("entree");
		        int qte = resultSet3.getInt("qte");
		        String code = resultSet3.getString("code");

		        Transformation transformation = new Transformation(code, sortie, entree, qte);
		        transforms.add(transformation);
		    }

		    // Process the result set for transformations
		    while (resultSet2.next()) {
		        String sortie = resultSet2.getString("sortie");
		        String entree = resultSet2.getString("entree");
		        int qte = resultSet2.getInt("qte");
		        String code = resultSet2.getString("code");

		        Transformation transformation = new Transformation(code, sortie, entree, qte);
		        transformations.add(transformation);
		    }
		    
		    
            String sqlQuery4 = "SELECT * FROM article WHERE desig IN (SELECT sortie FROM transformation)";
            PreparedStatement statement4 = connection.prepareStatement(sqlQuery4);

            // Execute the SQL query and obtain the result set
            ResultSet resultSet4 = statement4.executeQuery();

            // Create a list to hold the articles
            List<Article> articles = new ArrayList<>();

            // Process the result set
            while (resultSet4.next()) {
                int code = resultSet4.getInt("code");
                String designation = resultSet4.getString("desig");

                Article article = new Article(code, designation);
                articles.add(article);
            }
            HttpSession session = request.getSession(); // Obtain the session object

            String nom = (String) session.getAttribute("nom");

            String sqlQuery5 = "SELECT * FROM usersdepot WHERE nom = ?";
            PreparedStatement statement5 = connection.prepareStatement(sqlQuery5);
            statement5.setString(1, nom);

            // Execute the SQL query and obtain the result set
            ResultSet resultSet5 = statement5.executeQuery();

            // Create a list to hold the UserDepot objects
            List<UserDepot> userDepots = new ArrayList<>();

            // Process the result set
            while (resultSet5.next()) {
                String nomp = resultSet5.getString("nom");
                String lieu = resultSet5.getString("lieu");

                UserDepot userDepot = new UserDepot(nomp, lieu);
                userDepots.add(userDepot);
            }

            request.setAttribute("lieux", userDepots);

            request.setAttribute("articles", articles);

		    request.setAttribute("transforms", transforms);

		    request.setAttribute("transformations", transformations);
		    request.setAttribute("sortieValue", sortieValue);

            request.getRequestDispatcher("UsersSorite.jsp").forward(request, response);



		    // Close resources
		    resultSet2.close();
		    selectStatement.close();
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

	}

}
