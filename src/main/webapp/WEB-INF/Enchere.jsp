<%@ page import="bo.Article" %>
<%@ page import="bo.Categorie" %>
<%@ page import="bo.Retrait" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 14/04/2021
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Vente</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container">
    <%
        Article ArticleSelectionne = (Article) request.getAttribute("ArticleSelectionne");
        Categorie CategorieArticle = (Categorie) request.getAttribute("CategorieArticle");
        Utilisateur UtilisateurArticle = (Utilisateur) request.getAttribute("UtilisateurArticle");
        Retrait Retrait = (Retrait) request.getAttribute("Retrait");
        if(ArticleSelectionne == null) {
    %>
    <p>Erreur : L'article n'éxiste pas !</p>

    <%
    } else {
    %>
    <h1 class="text-center">Fiche de l'article : <%= ArticleSelectionne.getNomArticle() %></h1>
    <hr>

    <script>
        function envoyer(a)
        {
            if (a==1) document.formulaire.action="<%=request.getContextPath() %>/Modifier";
            if (a==2) document.formulaire.action="<%=request.getContextPath() %>/Supprimer";
            document.formulaire.submit()
        }
    </script>

    <form name="formulaire" method="POST">

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="<%= ArticleSelectionne.getNomArticle() %>"><br>

        <label for="description">Description :</label>
        <input type="text" id="description" name="description" value="<%= ArticleSelectionne.getDescription() %>" ><br>

        <label for="dateDebut">Date de mise aux enchères :</label>
        <input type="date" id="dateDebut" name="dateDebut" value="<%= ArticleSelectionne.getDateDebutEncheres() %>" ><br>

        <label for="dateFin">Date de fin de l'enchère :</label>
        <input type="date" id="dateFin" name="dateFin" value="<%= ArticleSelectionne.getDateFinEncheres() %>"><br>

        <label for="prix">Prix de départ :</label>
        <input type="text" id="prix" name="prix" value="<%= ArticleSelectionne.getPrixInitial() %>"><br>

        <label for="categorie">Catégorie :</label>
        <input type="text" id="categorie" name="categorie" value="<%= ArticleSelectionne.getCategorie().getLibelle() %>"><br>

        <hr>
        <h2>Retrait</h2>

        <label for="rue">Rue</label>
        <input type="text" id="rue" name="rue" value="<%= Retrait.getRue() %>"><br>

        <label for="cp">Code Postale :</label>
        <input type="text" id="cp" name="cp" value="<%= Retrait.getCp() %>"><br>

        <label for="ville">Mot de passe :</label>
        <input type="text" id="ville" name="ville" value="<%= Retrait.getVille() %>"><br>

        <input type="hidden" id="idArticle" name="idArticle" value=<%= ArticleSelectionne.getNoArticle() %>>
        <input class="btn btn-primary" type="button" id="modifier" name="modifier" value="Modifier la Vente" onClick="envoyer(1)">
        <input class="btn btn-primary" type="button" id="Supprimer" name="Supprimer" value="Supprimer la Vente" onClick="envoyer(2)">
    </form >
    <%
        }
    %>
</div>

</body>
</html>
