<%@ page import="bo.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 08/04/2021
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion des Catégories</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<%
    if( resu == null || resu.getAdministrateur()==0) {
%>
<h1 class="text-center">Vous devez etre connecté en tant qu'administrateur pour accéder à cette page</h1>
<%
}       else {
%>
<div class="container">
    <%
        List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategories");
        if(listeCategories == null || listeCategories.isEmpty()) {
    %>
    <p>Aucune Catégories pour l'instant</p>

    <%
    } else {
    %>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Liste des Catégories</th>
        </tr>
        </thead>
        <tbody>
        <%for(Categorie cat : listeCategories) { %>
        <tr>
            <th scope="row"><%= cat.getNoCategorie() %></th>
            <td><%= cat.getLibelle() %></td>
            <td>
                <form action="<%=request.getContextPath() %>/Categories"
                      method="post">
                    <input type="hidden" id="no_categorie" name="no_categorie" value=<%= cat.getNoCategorie() %>>
                    <input class="btn btn-primary" type="submit" value="Supprimer" >
                </form>
            </td>
            <% } %>
        </tr>
        </tbody>
    </table>

    <%
        }
    %>
    <div class="wrapper">
        <h2>Ajouter une catégorie</h2>
        <form class="form-horizontal" action="<%=request.getContextPath() %>/Categories" method="POST">
            <div class="form-group">
                <label for="libelle">Nom de la Catégorie :</label>
                <input type="text" id="libelle" name="libelle" required>
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Ajouter" >
            </div>
        </form>
    </div>
</div>
<%}%>
</body>
</html>
