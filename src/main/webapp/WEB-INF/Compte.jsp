<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 11/04/2021
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utilisateur ProfilSelectionne = (Utilisateur) request.getAttribute("ProfilSelectionne"); %>
<html>
<head>
    <title><%= ProfilSelectionne.getPseudo() %></title>
    <%@include file="/head.html" %>

</head>
<body>
<%@include file="navbar.jsp" %>
<div class="wrapper">
<h1>Fiche du Profil : <%= ProfilSelectionne.getPseudo() %></h1>
<ul class="list-group">

    <li type="hidden" class="list-group-item">Numéro d'identification du vendeur : <%= ProfilSelectionne.getNoUtilisateur() %></li>
    <li class="list-group-item">Nom : <%= ProfilSelectionne.getNom() %></li>
    <li class="list-group-item">Prenom : <%= ProfilSelectionne.getPrenom() %></li>
    <li class="list-group-item">Pseudo : <%= ProfilSelectionne.getPseudo() %></li>
    <li class="list-group-item">E-m@il : <%= ProfilSelectionne.getEmail() %></li>
    <li class="list-group-item">Rue : <%= ProfilSelectionne.getRue() %></li>
    <li class="list-group-item">Ville : <%= ProfilSelectionne.getVille() %></li>
    <li class="list-group-item">Code Postale : <%= ProfilSelectionne.getCodePostal() %></li>
    <li class="list-group-item">Telephone : <%= ProfilSelectionne.getTelephone() %></li>
    <li class="list-group-item">Crédit : <%= ProfilSelectionne.getCredit() %></li>
    <li class="list-group-item">Administrateur : <%= ProfilSelectionne.getAdministrateur() %></li>
</ul>
</div>
</body>
</html>
