<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 13/04/2021
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmer Suppression</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container"></div>
    <h1 class="text-center">Désirez-vous vraiment supprimer votre Compte ?</h1>
    <h2 class="text-center">Toute suppression est définitive.</h2>
    <h2 class="text-center">Les ventes et articles qui vous sont liés seront également supprimés</h2>
    <div class="offset-md-5 col-md-2">
        <form method="post" action="<%=request.getContextPath() %>/ConfirmationSuppression">
            <input class="btn alert-danger" type="submit" value="CONFIRMER !">
            <a  class="btn btn-primary" href="<%=request.getContextPath() %>/Compte" > Annuler</a>
        </form>
    </div>

</div>
</body>
</html>
