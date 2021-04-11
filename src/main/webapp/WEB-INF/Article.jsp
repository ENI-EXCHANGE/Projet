<%@ page import="bo.Article" %>
<%@ page import="bo.Categorie" %>
<%@ page import="bo.Utilisateur" %><%--
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
<%@include file="navbar.jsp" %>
    <div class="wrapper">
        <%
            Article ArticleSelectionne = (Article) request.getAttribute("ArticleSelectionne");
            Categorie CategorieArticle = (Categorie) request.getAttribute("CategorieArticle");
            Utilisateur UtilisateurArticle = (Utilisateur) request.getAttribute("UtilisateurArticle");
            if(ArticleSelectionne == null) {
        %>
        <p>Erreur : L'article n'éxiste pas !</p>

        <%
        } else {
        %>
        <h1>Fiche de l'article : <%= ArticleSelectionne.getNom_article() %></h1>
        <ul class="list-group">

            <li type="hidden" class="list-group-item">Numéro d'identification de l'article <%= ArticleSelectionne.getNo_article() %></li>
            <li class="list-group-item">Nom : <%= ArticleSelectionne.getNom_article() %></li>
            <li class="list-group-item">Description : <%= ArticleSelectionne.getDescription() %></li>
            <li class="list-group-item">Date de mise aux enchères : <%= ArticleSelectionne.getDate_debut_encheres() %></li>
            <li class="list-group-item">Date de fin de l'enchère : <%= ArticleSelectionne.getDate_fin_encheres() %></li>
            <li class="list-group-item">Prix de départ : <%= ArticleSelectionne.getPrix_initial() %></li>
            <li class="list-group-item">Prix : <%= ArticleSelectionne.getPrix_vente() %></li>
            <li class="list-group-item">Catégorie : <%= CategorieArticle.getLibelle() %></li>
            <li class="list-group-item">Vendeur : <a href="<%=request.getContextPath() %>/Compte?pseudo=<%= UtilisateurArticle.getPseudo() %>"> <%= UtilisateurArticle.getPseudo() %></a></li>

        </ul>
        <%
            }
        %>
    </div>

</body>

</html>
