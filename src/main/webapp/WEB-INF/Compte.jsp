<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Enchere" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 11/04/2021
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utilisateur ProfilSelectionne = (Utilisateur) request.getAttribute("ProfilSelectionne");
%>
<html>
<head>
    <title><%= ProfilSelectionne.getPseudo() %></title>
    <%@include file="/head.html" %>

</head>
<body>
<%@include file="navbar.jsp" %>
<div class="wrapper">
    <div class="container">
        <h1 class="text-center">Fiche du Profil : <%= ProfilSelectionne.getPseudo() %></h1>
        <ul class="list-group">

            <li type="hidden" class="list-group-item">Numéro d'identification du vendeur : <%= ProfilSelectionne.getNoUtilisateur() %></li>
            <li class="list-group-item">Nom : <%= ProfilSelectionne.getNom() %></li>
            <li class="list-group-item">Prenom : <%= ProfilSelectionne.getPrenom() %></li>
            <li class="list-group-item">Pseudo : <%= ProfilSelectionne.getPseudo() %></li>
            <li class="list-group-item">E-m@il : <%= ProfilSelectionne.getEmail() %></li>
            <li class="list-group-item">Rue : <%= ProfilSelectionne.getRue() %></li>
            <li class="list-group-item">Ville : <%= ProfilSelectionne.getVille() %></li>
            <li class="list-group-item">Code Postale : <%= ProfilSelectionne.getCodePostal() %></li>
            <li class="list-group-item">Telephone : <%= ProfilSelectionne.getTelephone() %></li>
            <li class="list-group-item">Crédit : <%= ProfilSelectionne.getCredit() %></li>
            <li class="list-group-item">Administrateur : <%= ProfilSelectionne.getAdministrateur() %></li>
        </ul>
        <%
            if (resu.getPseudo().equals(ProfilSelectionne.getPseudo())){
                %>
        <hr>
        <div class="container">
            <form class="" method="post" action="<%=request.getContextPath() %>/Compte">
                <input type="hidden" id="no_utilisateur" name="no_utilisateur" value=<%= resu.getNoUtilisateur() %>>
                <input class="btn btn-primary" type="submit" value="Modifier vos Informations" >
                <input class="btn btn-primary" type="submit" value="Ajouter des Crédits" >
                <input class="btn btn-primary alert-danger" type="submit" value="Supprimer le Compte" >
            </form>
        </div>
        <div class="container">
            <hr>
            <h3 class="text-center">-Mes Encheres-</h3>
            <%
                List<Enchere> listEnch = (List<Enchere>) request.getAttribute("listeEnch");
                if(listEnch == null || listEnch.isEmpty()) {

            %>
            <h5 class="text-center">Aucunes encheres associées à ce compte</h5>
            <% }else {%>
            <div class="row">
                <%for(Enchere ench : listEnch) { %>
                <div class="card col-md-3" style="margin-left: 8%;margin-bottom: 4%;">
                    <div class="card-body">
                        <h5 class=card-title">Article : <%= ench.getArticle().getNomArticle()%></h5>
                        <p class="card-text">Date de <%= ench.getDateEnchere()%></p>
                        <p class="card-text">Prix actuel: <%= ench.getMontantEnchere()%></p>
                        <a href="#" class="btn btn-primary">Detail</a>
                    </div>
                </div>
                <%}%>
                <%}%>
            </div>
        </div>
        <div class="container">
            <hr>
            <h3 class="text-center">-Mes Ventes-</h3>
            <%
                    List<Article> listeArt = (List<Article>) request.getAttribute("listeArt");
                    if(listeArt == null || listeArt.isEmpty()) {

            %>
            <h5 class="text-center">Aucunes ventes associées à ce compte</h5>
            <% }else {%>
            <div class="row">
                <%for(Article art : listeArt) { %>
                    <div class="card col-md-3" style="margin-left: 8%;margin-bottom: 4%;">
                        <div class="card-body">
                            <h5 class=card-title"><%= art.getNomArticle()%></h5>
                            <p class="card-text"><%= art.getDescription()%></p>
                            <p class="card-text">Prix Initial: <%= art.getPrixInitial()%></p>
                            <p class="card-text">Prix de vente : <%= art.getPrixVente()%></p>
                            <a href="#" class="btn btn-primary">Detail</a>
                        </div>
                    </div>
                <%}%>
            <%}%>
            </div>
        </div>
        <%}%>
    </div>
</div>
</body>
</html>
