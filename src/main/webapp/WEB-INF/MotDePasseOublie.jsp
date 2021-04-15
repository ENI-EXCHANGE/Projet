<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 15/04/2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mot De Passe oublié</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">-Mot de Passe oublié-</h2>
<h4 class="text-center">Veuillez entrer vos identifiants de connexion</h4>
<h5 class="text-center">Un lien de réinitialisation vous sera envoyé à l'adresse mail lié au compte</h5>
<div class="container">
    <form method="post" action="<%=request.getContextPath() %>/MotDePasse">

        <label for="pseudo">Pseudo : </label>
        <input type="text" id="pseudo" name="pseudo" required><br>

        <label for="mail">Mail : </label>
        <input type="email" id="mail" name="mail" required><br>

        <input type="submit" value="Envoyer" class="btn btn-primary" >
        <a  class="btn" href="<%=request.getContextPath() %>/connexion" > Annuler</a>
    </form>
</div>
</body>
</html>
