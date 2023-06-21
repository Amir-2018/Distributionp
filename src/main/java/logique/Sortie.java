package logique;

import java.io.IOException;
import java.util.Collections;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Sortie
 */
@WebServlet("/sortie")
public class Sortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sortie() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "");

            // Create a prepared statement to execute SQL queries
            String sqlQuery = "SELECT * FROM article WHERE desig IN (SELECT sortie FROM transformation)";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            // Execute the SQL query and obtain the result set
            ResultSet resultSet = statement.executeQuery();

            // Create a list to hold the articles
            List<Article> articles = new ArrayList<>();

            // Process the result set
            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String designation = resultSet.getString("desig");

                Article article = new Article(code, designation);
                articles.add(article);
            }

            // Close the result set and statement
          

            // Create another prepared statement to fetch transformations from the table

         // ...

         int transformationsPerPage = 10; // Nombre de transformations par page

         // Récupérer le numéro de page à partir de la requête
         String currentPageParam = request.getParameter("page");
         int currentPage = 1; // Valeur par défaut si le paramètre n'est pas présent ou invalide

         if (currentPageParam != null && !currentPageParam.isEmpty()) {
             try {
                 currentPage = Integer.parseInt(currentPageParam);
             } catch (NumberFormatException e) {
                 e.printStackTrace();
             }
         }

         // Calculer le nombre total de transformations
         String countQuery = "SELECT COUNT(*) AS total FROM transformation";
         PreparedStatement countStatement = connection.prepareStatement(countQuery);
         ResultSet countResult = countStatement.executeQuery();
         countResult.next();
         int totalTransformations = countResult.getInt("total");

         // Calculer le nombre total de pages
         int totalPages = (int) Math.ceil((double) totalTransformations / transformationsPerPage);

         // Vérifier si la page actuelle dépasse le nombre total de pages
         currentPage = Math.min(currentPage, totalPages);
         currentPage = Math.max(currentPage, 1); // Vérifier si currentPage est inférieur à 1

         // Calculer l'offset en fonction de la page actuelle
         int offset = (currentPage - 1) * transformationsPerPage;

         // Créer la requête SQL avec pagination
         String selectQuery = "SELECT * FROM transformation LIMIT ? OFFSET ?";
         PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
         selectStatement.setInt(1, transformationsPerPage);
         selectStatement.setInt(2, offset);
         ResultSet resultSet2 = selectStatement.executeQuery();

         // Créer une liste pour contenir les transformations de la page actuelle
         List<Transformation> transformations = new ArrayList<>();

         // Traiter le résultat pour les transformations
         while (resultSet2.next()) {
             String sortie = resultSet2.getString("sortie");
             String entree = resultSet2.getString("entree");
             int qte = resultSet2.getInt("qte");
             String code = resultSet2.getString("code");

             Transformation transformation = new Transformation(code, sortie, entree, qte);
             transformations.add(transformation);
         }

         // Vérifier si la liste de transformations est vide
         if (totalTransformations == 0) {
             transformations = Collections.emptyList(); // Retourner une liste vide
         }

         // Définir les attributs pour le rendu JSP
         request.setAttribute("transformations", transformations);
         request.setAttribute("currentPage", currentPage);
         request.setAttribute("totalPages", totalPages);

            
            String selectQuery3 = "SELECT * FROM usersdepot";
            PreparedStatement selectStatement3 = connection.prepareStatement(selectQuery3 );
            ResultSet resultSet3 = selectStatement3.executeQuery();

            // Create a list to hold the DepotPersonne objects
            List<UserDepot> depotPersonnes = new ArrayList<>();

            // Process the result set for depotpersonne
            while (resultSet3.next()) {
            	
                String nom = resultSet3.getString("nom");
                String depot = resultSet3.getString("lieu");

                UserDepot depotPersonne = new UserDepot();
                depotPersonne.setNom(nom);
                depotPersonne.setLieu(depot);

                depotPersonnes.add(depotPersonne);
            }
            request.setAttribute("lieux", depotPersonnes); 

            request.setAttribute("depotPersonnes", depotPersonnes); 
            request.setAttribute("articles", articles);
            request.setAttribute("transforms", transformations);

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();	
            resultSet2.close();
            selectStatement.close();
            connection.close();

            // Set the lists of articles and transformations as attributes in the request
            HttpSession session = request.getSession(); // Obtain the session object

            // Retrieve the value of the "nom" attribute from the session
            String cnUser = (String) session.getAttribute("nom");
            request.setAttribute("connectedUser",cnUser ); 
            // Forward the request and response to the index.jsp file
            request.getRequestDispatcher("UsersSorite.jsp").forward(request, response);

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
