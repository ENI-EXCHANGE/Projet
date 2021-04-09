<%@ page import="bo.Article" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 08/04/2021
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
    <%@include file="/head.html" %>
</head>
<body>
    <h1>Article</h1>
    <div>
        <%
            Article ArticleSelectionne = (Article) request.getAttribute("ArticleSelectionne");
            if(ArticleSelectionne == null) {
        %>
        <p>Erreur : L'article n'éxiste pas !</p>

        <%
        } else {
        %>
        <p>Attributs de L'article Selectionné</p>
        <ul class="list-group">

            <li class="list-group-item"> <%= ArticleSelectionne.getNo_article() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getNom_article() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getDescription() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getDate_debut_encheres() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getDate_fin_encheres() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getPrix_initial() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getPrix_vente() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getNo_categorire() %></li>
            <li class="list-group-item"> <%= ArticleSelectionne.getNo_utilisateur() %></li>

        </ul>
        <%
            }
        %>
    </div>




</body>

</html>
