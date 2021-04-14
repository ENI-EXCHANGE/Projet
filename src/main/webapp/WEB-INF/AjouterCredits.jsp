<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 13/04/2021
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Des Credits</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<% if(request.getParameter("credit") == null){
%>
<div class="container">
    <h1 class="text-center">€ Ajouter des Crédits €</h1>
    <h3 class="text-center">1 Crédit = 8€</h3>
    <form method="post" action="<%=request.getContextPath() %>/AjouterCredits">
        <div class="form-group">
            <label for="credit">Nombre de Crédit à ajouter :</label>
            <input type="text" id="credit" name="credit" required>
        </div>
        <div class="form-group">
            <div class="offset-md-5 col-md-2">
                <input class="btn btn-primary" type="submit" value="Valider" >
            </div>
        </div>
        <div class="form-group">
            <div class="offset-md-5 col-md-2">
                <a class="btn btn-primary" href="<%=request.getContextPath() %>/Compte" > Annuler</a>
            </div>
        </div>
    </form>

</div>
<%} if(request.getParameter("credit") != null){
    int eight = Integer.parseInt(request.getParameter("credit"));
%>
<div class="container">
    <h1 class="text-center">€ Veuillez saisir vos Informations Bancaires €</h1>
    <div class="row">
        <div class="card col-md-3 mx-auto" style="margin-left: 8%;margin-bottom: 4%;">
            <div class="card-body">
                <h5 class="card-title">Montant TTC :</h5>
                <p class="card-text text-center"><h2><%= eight*8 %></h2></p>
            </div>
        </div>
    </div>
    <form method="post" action="<%=request.getContextPath() %>/ValiderCredits">
        <div class="form-group">
            <h3>Raison Civil</h3>
            <label for="genre"></label>
            <select name="genre" id="genre" required>
                <option>MME</option>
                <option>MR</option>
                <option>INDEFINI</option>
            </select>
            <label for="Titulaire"></label>
            <input type="text" id="Titulaire" name="Titulaire" required>
            <label for="prenom"></label>
            <input type="text" id="prenom" name="prenom" required>
        </div>
        <div class="form-group">
            <h3>Coordonnées bancaires</h3>
            <input type="hidden" name="ajout" id="ajout" value="<%= eight%>">
            <label for="CB">Numéro de Carte :</label>
            <input type="text" id="CB" name="CB" required>
            <label for="expiration">Numéro de Carte :</label>
            <input type="text" id="expiration" name="expiration" required>
            <label for="CSV">CSV :</label>
            <input type="text" id="CSV" name="CSV" required>
        </div>
        <div class="form-group">
            <div class="offset-md-5 col-md-2">
                <input class="btn btn-primary" type="submit" value="Valider" >
            </div>
        </div>
        <div class="form-group">
            <div class="offset-md-5 col-md-2">
                <a class="btn btn-primary" href="<%=request.getContextPath() %>/Compte" > Annuler</a>
            </div>
        </div>
    </form>

</div>
<%}%>
</body>
</html>
