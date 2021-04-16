<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 13/04/2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utilisateur ProfilSelectionne = (Utilisateur) request.getAttribute("ProfilSelectionne");
%>
<html>
<head>
    <title>Modifier Profil</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <h1> Modifier mon Profil :</h1>

    <form action="<%=request.getContextPath() %>/Modification" method="POST">

        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pseudo" name="pseudo" value="<%= ProfilSelectionne.getPseudo() %>"><br>

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="<%= ProfilSelectionne.getNom() %>"><br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="<%= ProfilSelectionne.getPrenom() %>" ><br>

        <label for="email">E-m@il :</label>
        <input type="text" id="email" name="email" value="<%= ProfilSelectionne.getEmail() %>" ><br>

        <label for="telephone">téléphone :</label>
        <input type="text" id="telephone" name="telephone" value="<%= ProfilSelectionne.getTelephone() %>"><br>

        <label for="rue">Adresse :</label>
        <input type="text" id="rue" name="rue" value="<%= ProfilSelectionne.getRue() %>"><br>

        <label for="cp">Code postal :</label>
        <input type="text" id="cp" name="code_postal" value="<%= ProfilSelectionne.getCodePostal() %>"><br>

        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" value="<%= ProfilSelectionne.getVille() %>"><br>

        <label for="mdp">Mot de passe :</label>
        <input type="password" id="mdp" name="mot_de_passe" value="<%= ProfilSelectionne.getMotDePasse() %>"><br>

        <label for="mdp2">Confirmation du mot de passe :</label>
        <input type="password" id="mdp2" name="mot_de_passe2"><br>

        <input type="submit" value="Modifier" class="btn btn-primary" >
        <a  class="btn btn-danger" href="<%=request.getContextPath() %>/Compte" > Annuler</a>
    </form >

</div>


</body>
</html>
