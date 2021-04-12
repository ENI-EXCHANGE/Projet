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
        <h1>Fiche de l'article : <%= ArticleSelectionne.getNomArticle() %></h1>
        <ul class="list-group">

            <li type="hidden" class="list-group-item">Numéro d'identification de l'article <%= ArticleSelectionne.getNoArticle() %></li>
            <li class="list-group-item">Nom : <%= ArticleSelectionne.getNomArticle() %></li>
            <li class="list-group-item">Description : <%= ArticleSelectionne.getDescription() %></li>
            <li class="list-group-item">Date de mise aux enchères : <%= ArticleSelectionne.getDateDebutEncheres() %></li>
            <li class="list-group-item">Date de fin de l'enchère : <%= ArticleSelectionne.getDateFinEncheres() %></li>
            <li class="list-group-item">Prix de départ : <%= ArticleSelectionne.getPrixInitial() %></li>
            <li class="list-group-item">Prix : <%= ArticleSelectionne.getPrixVente() %></li>
            <li class="list-group-item">Catégorie : <%= CategorieArticle.getLibelle() %></li>
            <li class="list-group-item">Vendeur : <a href="<%=request.getContextPath() %>/Compte?pseudo=<%= UtilisateurArticle.getPseudo() %>"> <%= UtilisateurArticle.getPseudo() %></a></li>

        </ul>
        <%
            }
        %>
    </div>

</body>

</html>
