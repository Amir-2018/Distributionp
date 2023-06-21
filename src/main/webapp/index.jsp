<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<script>
function confirmDelete() {
    // Afficher la boîte de dialogue modale
    var result = confirm("Êtes-vous sûr de vouloir supprimer cet élément ?");

    // Vérifier la réponse de l'utilisateur
    if (result) {
        // L'utilisateur a cliqué sur "Oui", le lien sera activé
        return true;
    } else {
        // L'utilisateur a cliqué sur "Non", le lien ne sera pas activé
        return false;
    }
}

</script>

<script>

function filterOptions() {
    var input, filter, select, options, option, i;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    select = document.getElementById("mySelect0");
    options = select.getElementsByTagName("option");

    for (i = 0; i < options.length; i++) {
        option = options[i];
        if (option.textContent.toUpperCase().indexOf(filter) > -1) {
            option.style.display = "";
        } else {
            option.style.display = "none";
        }
    }
}

function filterOptions2() {
    var input, filter, select, options, option, i;
    input = document.getElementById("searchInput1");
    filter = input.value.toUpperCase();
    select = document.getElementById("mySelect1");
    options = select.querySelectorAll(".option1");

    for (i = 0; i < options.length; i++) {
        option = options[i];
        if (option.textContent.toUpperCase().indexOf(filter) > -1) {
            option.style.display = "";
        } else {
            option.style.display = "none";
        }
    }
}


</script>
</head>
<body>
<%@ page import="java.util.List" %>
<%@ page import="logique.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="connects">Saber</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
   
         
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="login">Déconnexion</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

    <tbody>
   	<form method="POST" action="transfert">
    <h3 class="text-center" >Sortie</h3>
    
                <input type="text" class="form-control" id="searchInput" placeholder="Recherche" onkeyup="filterOptions()" />
   			 <br/>
        <select class="form-select" name="sortie" id="mySelect0">
		    <c:forEach items="${articles}" var="article">
		        <option value="${article.desig}">${article.desig}</option>
		    </c:forEach>
		</select>
	<h3 class="text-center" >Equations</h3>
	                <input type="text" class="form-control" id="searchInput1" placeholder="Recherche" onkeyup="filterOptions2()" />
	<br>
	<table>
    <tr>
        
        <td>
           <select class="form-select" name="entree" id="mySelect1">
    <c:forEach items="${articles}" var="article">
        <option class="option1" value="${article.desig}">${article.desig}</option>
    </c:forEach>
</select>
        </td>
        <td>
            <input required type="number" class="form-control" name="qte" name="entree" placeholder="Quantité" />
        </td>
    </tr>
</table>
	<br>
	<a class="btn btn-info" href="DepotUsers">Affecter utilisateur vers Depots</a>
	<input class="btn btn-success" type="submit" value = "Ajouter Equation"/>
<a class="btn btn-danger" href="delete" onclick="return confirmDelete()">reset</a>
<br>
	<%-- Add this code snippet where you want to display the message --%>
<%-- Add this code snippet where you want to display the message --%>
<%-- Add this code snippet where you want to display the message --%>

<%-- Add this code snippet where you want to display the message --%>
<%
String message = (String) session.getAttribute("message");
session.removeAttribute("message"); // Remove the session attribute after retrieving it
String alertClass = "";
if (message != null && !message.isEmpty()) {
    if (message.equals("Existe Déjà")) {
        alertClass = "alert-warning";
    } else if (message.equals("Ajouter avec succès")) {
        alertClass = "alert-success";
    } else if (message.equals("Problème avec transformation")) {
        alertClass = "alert-danger";
    }
%>
    <div id="message" class="alert <%= alertClass %>">
        <strong>Message:</strong> <%= message %>
    </div>
    <script>
        setTimeout(function() {
            var messageDiv = document.getElementById("message");
            messageDiv.classList.add("fade");
            messageDiv.classList.add("show");
            setTimeout(function() {
                messageDiv.remove();
            }, 1000);
        }, 1000);
    </script>
<% } %>



	
	</form>
	<br>		
	<table class="table table-striped">
    <tr>
        <th>Sortie</th>
        <th>Entrée</th>
        <th>Quantité</th>
        <th>Supprimer</th>
    </tr>
    <c:forEach items="${transformations}" var="transformation">
        <tr>
            <td>${transformation.sortie}</td>
            <td>${transformation.entree}</td>
            <td>${transformation.qte}</td>
<td>
    <a href="effacerById?code=${transformation.code}" class="btn btn-danger" onclick="return confirmDelete()">Effacer</a>
</td>        </tr>
    </c:forEach>
</table>

<!-- Afficher les boutons de pagination -->
<div>
    <!-- Bouton précédent -->
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}" class="btn btn-primary">Précédent</a>
    </c:if>
    
    <!-- Bouton suivant -->
    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}" class="btn btn-primary">Suivant</a>
    </c:if>
</div>

	<br>
	<!-- Button to trigger the popup -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
  Depot
</button>

<!-- Popup HTML -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" >
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
          <table class="table table-striped">
  <thead>
    <tr>
      <th>Personnel</th>
      <th>Depot</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="depotPersonne" items="${depotPersonnes}">
      <tr>
        <td>${depotPersonne.nom}</td>
        <td>${depotPersonne.lieu}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
          </div>
        </form>
      </div>
    
    </div>
  </div>
</div>

<!-- Bootstrap 5 CSS and JavaScript -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" integrity="sha512-..." crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha512-..." crossorigin="anonymous"></script>
	
		
		
		
    </tbody>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
</body>
</html>