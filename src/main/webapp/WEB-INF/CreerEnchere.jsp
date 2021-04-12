<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 11/04/2021
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création d'Enchère</title>
    <%@include file="/head.html" %>

</head>
<body>
<%@include file="navbar.jsp" %>
<div class="wrapper">
    <h1> Créer un Enchère :</h1>

    <form action="<%=request.getContextPath() %>/Article" method="POST">

        <label for="nom">Nom de l'article :</label>
        <input type="text" id="nom" name="nom" ><br>

        <label for="description">Description :</label>
        <input type="text" id="description" name="description"  ><br>

        <label for="categorie">Catégorie :</label>
        <select id="categorie" name="categorie">
            <option></option>
        </select>

        <label for="dateDebut">Date de début de l'enchère :</label>
        <input type="date" id="dateDebut" name="dateDebut"  ><br>

        <label for="dateFin">Date de fin de l'enchère :</label>
        <input type="date" id="dateFin" name="dateFin" ><br>

        <label for="prix">Prix de base :</label>
        <input type="text" id="prix" name="prix" ><br>

        <div class="wrapper">
            <h2>Retrait</h2>
            <label for="rue">Rue :</label>
            <input type="text" id="rue" name="rue"><br>

            <label for="ville">Ville :</label>
            <input type="text" id="ville" name="ville" ><br>

            <label for="cp">Code Postal :</label>
            <input type="text" id="cp" name="cp" ><br>

        </div>

        <input type="submit" value="Valider Création" class="btn btn-primary" >
        <a  class="btn btn-danger" href="<%=request.getContextPath() %>/connexion" > Annuler</a>
    </form >

</div>

</body>
</html>
