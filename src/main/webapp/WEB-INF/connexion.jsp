<%--
  Created by IntelliJ IDEA.
  User: fanni
  Date: 08/04/2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>connexion</title>
    <%@include file="/head.html" %>
</head>
<body>
<h1>Se connecter :</h1>
<form action="<%=request.getContextPath() %>/connexion" method="POST">

    <label for="pseudo">Identifiant :</label>
    <input type="text" id="pseudo" name="pseudo" ><br>

    <label for="mdp">Mot de passe :</label>
    <input type="text" id="mdp" name="mot_de_passe" ><br>

    <input type="submit" value="Connexion"  ><br>

    <input type="checkbox" id="keepConnected" value="Connexion">
    <label for="keepConnected">se souvenir de moi</label><br>

    <a href="" > Mot de passe oublié</a><br>


</form>

<form action="<%=request.getContextPath() %>/profil" method="POST">
    <input type="submit" value="Créer un compte" >
</form>

</body>
</html>
