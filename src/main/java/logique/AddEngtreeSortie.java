package logique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddEngtreeSortie
 */
@WebServlet("/AddEngtreeSortie")
public class AddEngtreeSortie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEngtreeSortie() {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depot = request.getParameter("pv");
        String sortie = request.getParameter("sortie");
        String entree = request.getParameter("entree");
        int qt = Integer.parseInt(request.getParameter("qt"));

        Entree en = new Entree(depot, sortie, entree);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;
        PreparedStatement statementA = null;
        PreparedStatement statementE= null;
        PreparedStatement statementDE= null;



        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/gsoftpv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, "root", "");

            // Prepare the SQL query
            String sql = "SELECT code FROM lieustockage WHERE desig = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, depot);

            // Execute the query
            resultSet = statement.executeQuery();

            // Retrieve the ID from the result set
            int id = 0;
            String code = "";
            // Default value if no matching record is found
            if (resultSet.next()) {
                id = resultSet.getInt("code");
                code += String.valueOf(id);
                System.out.print(code);
            }

            // Prepare the SQL query for article
            String sqlA = "select code from article where desig = ?";
            statement2 = connection.prepareStatement(sqlA);
            statement2.setString(1, sortie);

            // Execute the query for article
            resultSet2 = statement2.executeQuery();

            // Retrieve the code from the result set
            String codeA = null; // Default value if no matching record is found
            if (resultSet2.next()) {
                codeA = resultSet2.getString("code");
            } else {
                System.out.print("check");
            }
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            
            // local storage in the server 
            
            HttpSession session = request.getSession();

            // Assuming you have retrieved the values of codeA and currentYear

            // Prepare the SQL query
            String insertSql = "INSERT INTO sortiestock (annee, num, unom,lieu,datep,desig,motifs,totalttc,valide) VALUES (?,?, ?, ?,?,?,?,?,?)";
            statement3 = connection.prepareStatement(insertSql);
            Random random = new Random();
            
       
            String selectQueryS = "SELECT COUNT(*) AS total FROM dsortiestock";
            PreparedStatement statementS = connection.prepareStatement(selectQueryS);

            // Execute the SQL query and obtain the result set
            ResultSet resultSetS = statementS.executeQuery();

            int totalCount = 0;
            if (resultSetS.next()) {
                totalCount = resultSetS.getInt("total");
            }
            // Use the code variable for further processing


            // statement3.setString(2, code);
            statement3.setInt(1, currentYear);
            String nom = (String) session.getAttribute("nom");
            statement3.setString(3, nom);
            LocalDate currentDate = LocalDate.now();

            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

            statement3.setString(6, depot);

            // Retrieve the value from the session
            statement3.setDate(5, sqlDate);
            statement3.setString(4, code);

            statement3.setString(7,"xxxxxxx");
            statement3.setString(9,"o");
            statement3.setInt(2,totalCount);

            int randomNumber = random.nextInt(10);

            statement3.setInt(8, 1);


            // Execute the insert query
            statement3.executeUpdate();
            
            
            
            // Add data to dentreestock table
            
            String insertSqlD	 = "INSERT INTO dsortiestock (annee, num, artn,article,desig,unite,qte,prix) VALUES (?,?, ?, ?,?,?,?,?)";
            statement4 = connection.prepareStatement(insertSqlD);
            
            String selectQueryQ = "SELECT qte FROM transformation WHERE sortie = ? AND entree = ?";
            PreparedStatement statementQ = connection.prepareStatement(selectQueryQ);

            statementQ.setString(1, sortie); // Set the 'sortie' parameter value dynamically
            statementQ.setString(2, entree); // Set the 'entree' parameter value dynamically

            // Execute the SQL query and obtain the result set
            ResultSet resultSetQ = statementQ.executeQuery();
            
         // Assuming you have executed the query and obtained the result set 'resultSet'

            int qte = 0; // Initialize the variable to hold the 'qte' value

            if (resultSetQ.next()) {
                qte = resultSetQ.getInt("qte"); // Retrieve the value of 'qte' from the result set
            }

            // Now the 'qte' variable holds the retrieved value

            
            String selectQueryA = "SELECT code FROM article WHERE desig = ?";
           statementA = connection.prepareStatement(selectQueryA);
            statementA.setString(1, sortie); // Set the parameter value dynamically

            // Execute the SQL query and obtain the result set
            ResultSet resultSetA = statementA.executeQuery();

            String codeArticle = null;
            if (resultSetA.next()) {
                codeArticle = resultSetA.getString("code");
            }
            
            
        
            // statement3.setString(2, code);
            statement4.setInt(1, currentYear);
            statement4.setString(2,code);
            statement4.setInt(3, totalCount);
            statement4.setString(4, codeArticle);
            statement4.setString(5, sortie);
            statement4.setString(6, "G");
            statement4.setInt(7,qt);
            statement4.setInt(8, 10);




            // Retrieve the value from the session




            // Execute the insert query
            statement4.executeUpdate();
            
            
            
            // maintenet les entrées
            String selectQueryAE = "SELECT code FROM article WHERE desig = ?";
            PreparedStatement statementAE = connection.prepareStatement(selectQueryA);
             statementAE.setString(1, entree); // Set the parameter value dynamically

             // Execute the SQL query and obtain the result set
             ResultSet resultSetAE = statementAE.executeQuery();

             String codeArticleAE = null;
             if (resultSetAE.next()) {
                 codeArticleAE = resultSetAE.getString("code");
             }
            
            String insertSqlE = "INSERT INTO entreestock (annee, num, unom, lieu, datep, desig, motifs, totalttc, valide) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statementE = connection.prepareStatement(insertSqlE);

            statementE.setInt(1, currentYear);
            statementE.setString(3, nom);


            statementE.setString(6, depot);

            // Retrieve the value from the session
            statementE.setDate(5, sqlDate);
            statementE.setString(4, code);

            statementE.setString(7,"xxxxxxx");
            statementE.setString(9,"o");
            statementE.setInt(2,totalCount);


            statementE.setInt(8, 1);

            // Execute the insert query
            statementE.executeUpdate();



         
            
            // now add the entree stock 
            
            
            String insertSqlDE	 = "INSERT INTO dentreestock (annee, num, artn,article,desig,unite,qte,prix) VALUES (?,?, ?, ?,?,?,?,?)";
            statementDE = connection.prepareStatement(insertSqlDE);
            

            // Execute the SQL query and obtain the result set

            // Diminuer du stocck :  
            
                                           
            // statement3.setString(2, code);
            statementDE.setInt(1, currentYear);
            statementDE.setString(2,code);
            statementDE.setInt(3, totalCount);
            statementDE.setString(4, codeArticleAE);
            statementDE.setString(5, entree);
            statementDE.setString(6, "G");
            statementDE.setInt(7,qte);
            statementDE.setInt(8, 10);     
            statementDE.executeUpdate();

            String updateQueryDS = "UPDATE Stock s " +
                    "INNER JOIN DSortieStock ds ON s.article = ds.article " +
                    "SET s.qte = s.qte - " +qt+" "+
                    "WHERE s.lieu = ds.num";

PreparedStatement updateStatementDS = connection.prepareStatement(updateQueryDS);
int rowsAffectedDS = updateStatementDS.executeUpdate();

if (rowsAffectedDS > 0) {
  System.out.println("Stock quantity updated successfully for DSortieStock.");
} else {
  System.out.println("No matching records found in DSortieStock.");
}


String updateQueryDE = "UPDATE Stock s " +
        "INNER JOIN DEntreeStock de ON s.article = de.article " +
        "SET s.qte = s.qte + " + qte*qt + " " +
        "WHERE s.lieu = de.num";


PreparedStatement updateStatementDE = connection.prepareStatement(updateQueryDE);
int rowsAffectedDE = updateStatementDE.executeUpdate();

if (rowsAffectedDE > 0) {
System.out.println("Stock quantity updated successfully for DEntreeStock.");
request.setAttribute("qte", qte);

response.sendRedirect("getES	");


} else {
System.out.println("No matching records found in DEntreeStock.");
}
            

            // Use the retrieved ID in your logic
        } catch (ClassNotFoundException e) {
            // Handle the ClassNotFoundException
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle the SQLException
            e.printStackTrace();
        } finally {
            // Close the database resources in a finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (statement2 != null) {
                    statement2.close();
                }
                if (statement3 != null) {
                    statement3.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Handle the SQLException during resource closing
                e.printStackTrace();
            }
        }


	}

}
