<%--
  Created by IntelliJ IDEA.
  User: fanni
  Date: 08/04/2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>connexion</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="wrapper text-center ">
    <div class="container" ></div>
        <h1>Se connecter :</h1><br>

        <form action="<%=request.getContextPath() %>/connexion" method="POST">

           <%-- <a>bonjour  ${ !empty utilisateurConnecte.prenom ? utilisateurConnecte.prenom : '' }</a><br><br> --%>

            <label for="login">Identifiant :</label>
            <input type="text" id="login" name="login" ><br>

            <label for="mdp">Mot de passe :</label>
            <input type="text" id="mdp" name="mot_de_passe" ><br>
            <p style="color: #ff0000"> ${message} </p> <br><br>
            <input type="submit" value="Connexion"  ><br>

            <input type="checkbox" id="keepConnected" value="Connexion">
            <label for="keepConnected">se souvenir de moi</label><br>

            <a href="" > Mot de passe oublié</a><br>


        </form>

        <form action="<%=request.getContextPath() %>/creationCompte" method="get">
            <input type="submit" value="Créer un compte" >
        </form>
    </div>
</div>
</body>
</html>
