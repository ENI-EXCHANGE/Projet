<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 13/04/2021
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion Des Utilisateurs</title>
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
        List<Utilisateur> listeUtilisateurs = (List<Utilisateur>) request.getAttribute("listeUtilisateurs");
        if(listeUtilisateurs == null || listeUtilisateurs.isEmpty()) {
    %>
    <p>Pas d'Utilisateurs pour l'instant</p>

    <%
    } else {
    %>
    <h1 class="text-center">Liste des Utilisateurs</h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Pseudo</th>
            <th scope="col">Nom</th>
            <th scope="col">Prénom</th>
            <th scope="col">e-m@il</th>
            <th scope="col">Admin</th>
        </tr>
        </thead>
        <tbody>
        <%for(Utilisateur usr : listeUtilisateurs) { %>
        <tr>
            <th scope="row"><%= usr.getNoUtilisateur() %></th>
            <td><%= usr.getPseudo() %></td>
            <td><%= usr.getNom() %></td>
            <td><%= usr.getPrenom() %></td>
            <td><%= usr.getEmail() %></td>
            <td><%= usr.getAdministrateur() %></td>
            <td>
                <form action="<%=request.getContextPath() %>/Utilisateurs"
                      method="post">
                    <input type="hidden" id="no_utilisateur" name="no_utilisateur" value=<%= usr.getNoUtilisateur() %>>
                    <input class="btn btn-primary" type="submit" value="Supprimer" >
                </form>
            </td>
            <% } %>
        </tr>
        </tbody>
    </table>
    <%}%>
</div>
<%}%>
</body>
</html>
