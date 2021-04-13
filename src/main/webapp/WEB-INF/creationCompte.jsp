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
    <title>Profil</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="wrapper text-center ">
    <div class="container" >
    <h1> Créer mon profil :</h1><br>

    <form action="<%=request.getContextPath() %>/creationCompte" method="POST">
        <p style="color: red"> ${messageErreur}</p><br>

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

        <label for="mdp2">confirmation du mot de passe :</label>
        <input type="text" id="mdp2" name="mot_de_passe2" ><br>

        <input type="submit" value="Créer" class="btn btn-primary" >
        <a  class="btn btn-danger" href="<%=request.getContextPath() %>/connexion" > Annuler</a>
    </form >
    </div>
</div>


</body>
</html>
