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
<%@include file="navbar.jsp" %>
<div class="wrapper">
<h1>Se connecter :</h1>

<form action="<%=request.getContextPath() %>/connexion" method="POST">
    <c:if test="${ !empty sessionScope.utilisateurConnecté.pseudo && !empty sessionScope.utilisateurConnecté.motDePasse }">
        <p>Vous êtes ${ sessionScope.utilisateurConnecté.pseudo } !</p>
    </c:if>
    <label for="pseudo">Identifiant :</label>
    <input type="text" id="pseudo" name="pseudo" ><br>

    <label for="mdp">Mot de passe :</label>
    <input type="text" id="mdp" name="mot_de_passe" ><br>
    <a> ${message} </a> <br><br>
    <input type="submit" value="Connexion"  ><br>

    <input type="checkbox" id="keepConnected" value="Connexion">
    <label for="keepConnected">se souvenir de moi</label><br>

    <a href="" > Mot de passe oublié</a><br>


</form>

<form action="<%=request.getContextPath() %>/creation_compte" method="get">
    <input type="submit" value="Créer un compte" >
</form>

<form action="<%=request.getContextPath() %>/deconnexion" method="post">
    <input type="submit" value="se déconnecter" >
</form>

</div>
</body>
</html>
