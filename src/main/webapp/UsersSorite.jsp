<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

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

document.addEventListener('DOMContentLoaded', function() {
    const mySelect = document.getElementById('mySelect0');
    const myForm = document.getElementById('myForm');

    mySelect.addEventListener('change', function() {
        const qtField = document.getElementById('qt');
        if (qtField.value.trim() !== '') {
            myForm.submit();
        } else {
            alert('Le champ quantité est vide');
        }
    });
});


</script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput2');
    const rows = document.querySelectorAll('.table-striped tbody tr');

    searchInput.addEventListener('keyup', function() {
      const searchTerm = searchInput.value.trim().toLowerCase();

      rows.forEach(function(row) {
        const cells = row.getElementsByTagName('td');
        let found = false;

        for (let i = 0; i < cells.length; i++) {
          const cellText = cells[i].innerText.toLowerCase();
          if (cellText.includes(searchTerm)) {
            found = true;
            break;
          }
        }

        if (found) {
          row.style.display = '';
        } else {
          row.style.display = 'none';
        }
      });
    });
  });
</script>



<style>

.container-fluid .row .col{
	border : 1px solid gray ; 
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>

<%@ page import="java.util.List" %>
<%@ page import="logique.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
    
<a class="navbar-brand" href="sortie">${connectedUser}</a>

      
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
          </li>
          <li class="nav-item">
          </li>
          <li class="nav-item">
          </li>
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
    <div class="container-fluid">
      <div class="row">
       <div class="col">
		      	<form method="POST" action="choixentree" id="myForm">
			    		<h3 class="text-center" >Sortie</h3>	        
						<input type="text" class="form-control" id="searchInput" placeholder="Recherche" onkeyup="filterOptions()" />
						<br>	
						<input type="number" name="qt" id="qt" placeholder="Quantité" class="form-control" required>
						<br>		
						<select class="form-select" name="sortie" id="mySelect0">
							 		    <option value="">Choisir Sortie</option>
									<c:forEach items="${articles}" var="article">
							  			<option value="${article.desig}">${article.desig}</option>
									</c:forEach>
	  					</select>
	  					<br>	  
	  					<input type="submit" style="display: none;">			
						<br>
						<!--  <input class="btn btn-success" type="submit" value = "Choisir Entree"/>-->
						<br>	
				</form>
				<br>		
						<input type="text" id="searchInput2" placeholder="Search..." class="form-control">
				<br>
				
				<table class="table table-striped">
				    <tr>
				        <th>Sortie</th>
				        <th>Entrée</th>
				        <th>Quantité</th>
				    </tr>
				    <c:forEach items="${transformations}" var="transformation">
				        <tr>
				            <td>${transformation.sortie}</td>
				            <td>${transformation.entree}</td>
				            <td>${transformation.qte}</td>
				        </tr>
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
   	   </div>
   	   <div class="col">
   	      	    <h3 class="text-center" >Entree</h3>
  <form method="POST" action="AddEngtreeSortie">
   	      	    
   	      <select class="form-select" name="pv" id="mySelect0">
   	      
    <c:forEach items="${lieux}" var="lieu">
        <option value="${lieu.lieu}">${lieu.lieu}</option>
    </c:forEach>
		</select>



						    		<br>
   	  			
   	   			<%
   String sortieValue = request.getParameter("sortie");
   String qt = request.getParameter("qt");
   String qte = request.getParameter("qte");


   boolean sortieExists = (sortieValue != null && !sortieValue.isEmpty());
%>

<% if (sortieExists) { %>
   <input type="text" class="form-control" value="<%= sortieValue %>"  name="sortie"/>
   <br>
   <input type="text" class="form-control" value="<%= qt %>"  name="qt"/>


   	   			<br>
   		<select class="form-select" name="entree" id="mySelect0">
   	   			
   	   		<c:forEach var="transformation" items="${transformations}">
            <c:if test="${not empty transformation}">
                <option value="${transformation.entree}">${transformation.getEntree()}</option>
            </c:if>
        </c:forEach>
        </select>
        <br>
          
        
        <input class="btn btn-success" type="submit" value = "Valider" onclick="return confirmDelete()"	/>
        
        <% } %>
   	   </div>
    </div>
  </div>
</form>
	
	<!-- Button to trigger the popup -->


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
<br>
<a class="btn btn-success" href="getES">Visualiser les derniers entrées sorties</a>
<!-- Bootstrap 5 CSS and JavaScript -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" integrity="sha512-..." crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha512-..." crossorigin="anonymous"></script>
	
		
		
		
    </tbody>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
</body>
</html>