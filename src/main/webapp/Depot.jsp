<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Centered Select Options</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
  <style>

  </style>
</head>
<body>
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
  <br>
<form method="POST" action="affecter">
  <div class="container">
    <h2>Affecter Utilisateurs vers Depots</h2>
<select class="form-select" name="lieu">
  <c:forEach var="desig" items="${desigList}">
    <option>${desig}</option>
  </c:forEach>
</select>
<br>
<select class="form-select" name="nom">
  <c:forEach var="userName" items="${userNames}">
    <option>${userName}</option>
  </c:forEach>
</select>


    <input type="submit" class="btn btn-primary mt-3" value="Affecter">
     </form> 
<table class="table mt-3">
  <thead>
    <tr>
      <th scope="col">Utilisateur</th>
      <th scope="col">Dépot</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${usersDepotList}" var="userDepot">
      <tr>
        <td>${userDepot.nom}</td>
        <td>${userDepot.lieu}</td>
      </tr>
    </c:forEach>
    <!-- Add more rows here if needed -->
  </tbody>
</table>

  </div>

</body>
</html>
