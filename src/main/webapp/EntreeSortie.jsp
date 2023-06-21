<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('txt2');
    const rows = document.querySelectorAll('.table-striped2 tbody tr');

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
  
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('txt1');
    const rows = document.querySelectorAll('.table-striped1 tbody tr');

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
</head>
<body>



<container-fluid>
	<div class='row'>
		<div class="col">
		<br>
		<h5>Sortie</h5>
		<br>
		<input type="text" id="txt1" class="form-control" placeholder="Rechercher">
		<br>
	<table class="table table-striped table-striped1">
    <thead>
        <tr>
            <th>Année</th>
            <th>Numéro</th>
            <th>Art N</th>
            <th>Article</th>
            <th>Désignation</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${sortieStockList}" var="entreeStock" varStatus="loop">
            <c:if test="${loop.index < 20}">
                <tr>
                    <td>${entreeStock.annee}</td>
                    <td>${entreeStock.num}</td>
                    <td>${entreeStock.artn}</td>
                    <td>${entreeStock.article}</td>
                    <td>${entreeStock.desig}</td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>

		</div>
		<div class="col">
		<br>
		<h5>Entrée</h5>
		<br>
		<input type="text" id="txt2"/ class="form-control" placeholder="Rechercher">
		<br>
			<table class="table table-striped table-striped2">
    <thead>
        <tr>
            <th>Année</th>
            <th>Numéro</th>
            <th>Art N</th>
            <th>Article</th>
            <th>Désignation</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${entreeStockList}" var="entreeStock">
            <tr>
                <td>${entreeStock.annee}</td>
                <td>${entreeStock.num}</td>
                <td>${entreeStock.artn}</td>
                <td>${entreeStock.article}</td>	
                <td>${entreeStock.desig}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
			
		</div>
	</div>
</container-fluid>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>