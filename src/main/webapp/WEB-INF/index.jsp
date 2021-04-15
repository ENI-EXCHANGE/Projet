<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Categorie" %>
<%@ page import="bo.Enchere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <br>
        <h1 class="text-center">Liste des enchères</h1>

            <form action="<%=request.getContextPath() %>/index" method="post">

                <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="filtres">Filtres :</label>
                        <input type="text" class="form-control" id="filtres" name="filtres" placeholder="Le nom de l'article contient" >
                    </div>
                    <div class="form-group">
                        <label for="categorie">Catégorie :</label>
                        <select id="categorie" name="categorie" class="form-control">
                            <% int noCategorie;
                                if(request.getAttribute("noCategorie") == null){ noCategorie = 0;}else{
                                 noCategorie = (int) request.getAttribute("noCategorie");} %>
                            <option value="0" <%if(noCategorie == 0){%>selected<%} %>>Toutes</option>
                                <% List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("categories");
                                    if(listeCategories != null) {
                                        for(Categorie categorie : listeCategories) {%>
                            <option <%if(noCategorie == categorie.getNoCategorie()){%>selected<%}%> value="<%=categorie.getNoCategorie()%>"><%= categorie.getLibelle()%></option>
                            <%}}%>
                        </select>
                    </div>
                </div>


                    <%
                    if ( resu != null ){
                        %>

                <div class="col-md-6" style="margin-top: 4%;">
                    <div class="row">
                <div class="col-md-6" id="achats">
                    <input class="form-check-input" type="radio" name="radio" value="achats" id="radioAchats" onclick="disabledFile(1)" checked>
                    <label class="form-check-label" for="radioAchats">
                        Achats
                    </label>

                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="achatsOuvertes" id="achatsOuvertes">
                        <label class="form-check-label" for="achatsOuvertes">
                            enchères ouvertes
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="achatCours" name="achatCours">
                        <label class="form-check-label" for="achatCours">
                            mes enchères en cours
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="achatRemportees" name="achatRemportees">
                        <label class="form-check-label" for="achatRemportees">
                            mes enchères remportées
                        </label>
                    </div>
                </div>


                <div class="col-md-6 " >
                    <input class="form-check-input" type="radio" name="radio" value="ventes" id="radioVentes" onclick="disabledFile(2)">
                    <label class="form-check-label" for="radioVentes">
                        Mes ventes
                    </label>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ventesCours" name="ventesCours">
                        <label class="form-check-label" for="ventesCours">
                            mes ventes en cours
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox"  id="ventesNonDebutees" name="ventesNonDebutees" >
                        <label class="form-check-label" for="ventesNonDebutees">
                            ventes non débutées
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ventesTerminees" name="ventesTerminees" >
                        <label class="form-check-label" for="ventesTerminees">
                            ventes terminées
                        </label>
                    </div>
                </div>

                </div></div>
                <% } %>


            </div>
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </form>

<br>
        <hr>
        <div class="row">
            <% List<Article> listeArticles = (List<Article>) request.getAttribute("articles");

            if(listeArticles == null) {

            %>
            <p>Aucun article</p>

            <% }else{
            %>

            <% // String nom = null;
              //  if(request.getAttribute("filtres") != null){ nom = (String) request.getAttribute("filtres");}
                for(Article article : listeArticles) {

                %>
            <div class="card col-md-3" style="margin-left: 8%;margin-bottom: 4%;">
                <img src="..." class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%= article.getNomArticle()%></h5>
                    <p class="card-text">Prix : <%= article.getPrixVente()%></p>
                    <p class="card-text">Début de l'enchère :<br> <%= article.getDateDebutEncheres()%></p>
                    <p class="card-text">Fin de l'enchère :<br> <%= article.getDateFinEncheres()%></p>
                    <p class="card-text">Vendeur : <% if( resu != null ){%><a href="Compte?pseudo=<%=article.getUtilisateur().getPseudo()%>"><%=article.getUtilisateur().getPseudo() %></a>
                        <%}else{%><%=article.getUtilisateur().getPseudo()%><%}%></p>
                    <a href="Article?id=<%=article.getNoArticle()%>" class="btn btn-primary">Detail</a>
                </div>
            </div>

            <% }} %>

        </div>
    </div>
</div>
<script>
    $('document').ready(function(){
        disabledFile(1);
    })

    function disabledFile(value) {
        if (value == 1) {
            document.getElementById("achatsOuvertes").disabled = false;
            document.getElementById("achatCours").disabled = false;
            document.getElementById("achatRemportees").disabled = false;

            document.getElementById("ventesCours").disabled = true;
            document.getElementById("ventesNonDebutees").disabled = true;
            document.getElementById("ventesTerminees").disabled = true;
        } else {
            document.getElementById("ventesCours").disabled = false;
            document.getElementById("ventesNonDebutees").disabled = false;
            document.getElementById("ventesTerminees").disabled = false;

            document.getElementById("achatsOuvertes").disabled = true;
            document.getElementById("achatCours").disabled = true;
            document.getElementById("achatRemportees").disabled = true;
        }
    }


</script>
</body>
</html>