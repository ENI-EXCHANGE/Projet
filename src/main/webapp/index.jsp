
<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <%@include file="/head.html" %>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <form method="post">
                <div class="form-group">
                    <label for="filtres">Filtres :</label>
                    <input type="text" class="form-control" id="filtres" name="filtres" placeholder="Le nom de l'article contient">
                </div>
                <div class="form-group">
                    <label for="categorie">Catégorie :</label>
                    <select id="categorie" name="categorie" class="form-control">
                        <option selected>Toutes</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </form>
        </div>

        <div class="row">
           <% List<Article> listeArticles = (List<Article>) request.getAttribute("liste");
               if(listeArticles == null || listeArticles.isEmpty()) {
               } else {
               %>

            <%for(Article article : listeArticles) { %>
            <div class="card" style="width: 18rem;">
                <img src="..." class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><% article.getNom_article();%></h5>
                    <p class="card-text"><% article.getDescription();%></p>
                    <p class="card-text">Prix : <% article.getPrix_vente();%></p>
                    <p class="card-text">Fin de l'enchère : <% article.getDate_fin_encheres();%></p>
                    <p class="card-text">Vendeur : <% %>></p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
            <% }} %>

        </div>
    </div>
</div>
</body>
</html>