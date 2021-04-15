<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 15/04/2021
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nouveau mot de Passe</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>

<% Utilisateur usr = (Utilisateur) request.getAttribute("usr"); %>

<h2 class="text-center">Veuillez entrer un nouveau mot de passe : </h2>
<div class="container">
    <form method="post" action="<%=request.getContextPath() %>/MotDePasse">

        <label for="mdp">Nouveau Mot de Passe</label>
        <input type="password" id="mdp" name="mdp" required><br>

        <label for="mdp2">Confirmer : </label>
        <input type="password" id="mdp2" name="mdp2" required><br>

        <input type="hidden" name="nouser" id="nouser" value="<%= usr.getNoUtilisateur()%>">
        <input type="submit" value="Soumettre" class="btn btn-primary" >
        <a  class="btn" href="<%=request.getContextPath() %>/connexion" > Annuler</a>
    </form>
</div>

</body>
</html>
