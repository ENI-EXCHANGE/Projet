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
    <div class="container">
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
            <h1>Fiche de l'article :
                <a href="<%=request.getContextPath() %>/Article?id=<%= ArticleSelectionne.getNoArticle() %>"> </a>
                <%= ArticleSelectionne.getNomArticle() %></h1>
            <ul class="list-group">

                <li type="hidden" class="list-group-item">Numéro d'identification de l'article <%= ArticleSelectionne.getNoArticle() %></li>
                <li class="list-group-item">Nom : <%= ArticleSelectionne.getNomArticle() %></li>
                <li class="list-group-item">Description : <%= ArticleSelectionne.getDescription() %></li>
                <li class="list-group-item">Mis en vente le <%= ArticleSelectionne.getDateDebutEncheres() %></li>
                <li class="list-group-item">Fin de l'enchère le <%= ArticleSelectionne.getDateFinEncheres() %></li>
                <li class="list-group-item">Mise à Prix : <%= ArticleSelectionne.getPrixInitial() %> points</li>
                <li class="list-group-item">Meilleure Offre : <%= ArticleSelectionne.getPrixVente() %> points
                    <%
                        Utilisateur dernierEncherisseur = (Utilisateur) request.getAttribute("dernierEncherisseur");
                        if (dernierEncherisseur!= null){
                    %>
                    faite par <a href="<%=request.getContextPath() %>/Compte?id=<%= dernierEncherisseur.getNoUtilisateur() %>"> <%= dernierEncherisseur.getPseudo() %> </a>
                    <%
                    } else {
                    %>
                        (pas encore d'enchère)
                    <%
                        }
                    %>
                </li>
                <li class="list-group-item">Catégorie : <%= CategorieArticle.getLibelle() %></li>
                <li class="list-group-item">Vendeur : <a href="<%=request.getContextPath() %>/Compte?pseudo=<%= UtilisateurArticle.getPseudo() %>">
                    <%= UtilisateurArticle.getPseudo() %></a>
                </li>

            </ul>
        </div>
        <div class="wrapper">
            <div class="offset-md-2 col-md-6">
                <h2>A retirer à l'adresse suivante :</h2>
                <a><%= UtilisateurArticle.getRue() %> </a>
                <br>
                <a> <%= UtilisateurArticle.getCodePostal() %> <%= UtilisateurArticle.getVille() %></a>
                <br>
            </div>
        </div>
        <div class="wrapper">
        <form action="<%=request.getContextPath() %>/encherir" method="post">
            <input type="hidden" name="idArticleEnch" id="idArticleEnch" value="<%= ArticleSelectionne.getNoArticle() %>">

            <div class="row">
                <div class="offset-md-2 col-md-6">
                    <p><strong>${message}</strong></p>
                    <label for="point"> Ma Proposition</label>
                    <input class="point" type="number"  id="point" name="point"><br>

                </div>
                <div class="offset-md-2 col-md-6">
                    <button type="submit"
                            class="btn btn-success "
                            name="encherir"
                            href="<%=request.getContextPath() %>/Article?idArticleEnch=<%= ArticleSelectionne.getNoArticle() %>" >
                        Encherir </button>
                    <a class="btn btn-primary" href="<%=request.getContextPath() %>/Compte" > Retour</a>
                </div>

            </div>
        </form>
        </div>
        <%
            }
        %>


    </div>



</body>

</html>
