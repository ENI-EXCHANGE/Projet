<%@ page import="bo.Utilisateur" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: fanni
  Date: 08/04/2021
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
    <%@include file="/head.html" %>
</head>
<body>
<h1> PAGE ACCUEIL </h1>
<form action="<%=request.getContextPath() %>/accueil" method="POST">

    <p>Ajouter un Utilisateur :</p><br>

    <label for="pseudo">Pseudo :</label>
    <input type="text" id="pseudo" name="pseudo" ><br>

    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom"  ><br>

    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom"  ><br>

    <label for="email">email :</label>
    <input type="text" id="email" name="email" ><br>

    <label for="telephone">téléphone :</label>
    <input type="text" id="telephone" name="telephone" ><br>

    <label for="rue">Adresse :</label>
    <input type="text" id="rue" name="rue" ><br>

    <label for="cp">code postal :</label>
    <input type="text" id="cp" name="code_postal"><br>

    <label for="ville">Ville :</label>
    <input type="text" id="ville" name="ville" ><br>

    <label for="mdp">mot de passe :</label>
    <input type="text" id="mdp" name="mot_de_passe" ><br>

    <label for="credit">credit :</label>
    <input type="text" id="credit" name="credit"  ><br>

    <label for="admin">Administrateur :</label>
    <input type="text" id="admin" name="admin" ><br>

    <input type="submit" value="Valider" >
</form>

<div>
    <%
        List<Utilisateur> lesUtilisateurs = (List<Utilisateur>) request.getAttribute("lesUtilisateurs");
        if(lesUtilisateurs == null || lesUtilisateurs.isEmpty()) {
    %>
    <p>la Liste ne contient pas d'utilisateurs</p>

    <%
    } else {
    %>
    <p>Liste des utilisateurs</p>
    <ul class="list-group">
        <%for(Utilisateur user : lesUtilisateurs) { %>
            <li class="list-group-item"> <%= user.getNom() %> <%= user.getPrenom() %></li>
        <% } %>
    </ul>
    <%
        }
    %>
</div>


</body>
</html>
