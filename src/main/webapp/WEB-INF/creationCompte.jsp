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
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 class="text-center"> Créer mon profil :</h1>

            <form action="<%=request.getContextPath() %>/creationCompte" method="POST">

                <label for="pseudo">Pseudo :</label>
                <input type="text" id="pseudo" name="pseudo" required><br>

                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom"  required><br>

                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom"  required><br>

                <label for="email">email :</label>
                <input type="email" id="email" name="email" required><br>

                <label for="telephone">téléphone :</label>
                <input type="text" id="telephone" name="telephone" required><br>

                <label for="rue">Adresse :</label>
                <input type="text" id="rue" name="rue" required><br>

                <label for="cp">code postal :</label>
                <input type="text" id="cp" name="code_postal" required><br>

                <label for="ville">Ville :</label>
                <input type="text" id="ville" name="ville" required><br>

                <label for="mdp">mot de passe :</label>
                <input type="password" id="mdp" name="mot_de_passe" required><br>

                <label for="mdp2">confirmation du mot de passe :</label>
                <input type="password" id="mdp2" name="mot_de_passe2" required><br>

                <input type="submit" value="Créer" class="btn btn-primary" >
                <a  class="btn btn-danger" href="<%=request.getContextPath() %>/connexion" > Annuler</a>
            </form >
        </div>
    </div>
</div>


</body>
</html>
