<%@ page import="bo.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 09/04/2021
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/">Encheres</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
        <%
            Utilisateur resu = ((Utilisateur) session.getAttribute("utilisateurConnecté"));
            if ( resu != null ){
                if( resu.getAdministrateur() ==0) {
        %>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/">Enchères</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() %>/Article">Vendre un Article</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/creation_compte">Mon profil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
            </li>
        <%
        }       else {
        %>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() %>/Categories">Gestion des Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/creation_compte">Mon profil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
            </li>
        <%
                }
            } else {
                %>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/connexion">S'inscrire/Se Connecter</a>
            </li>
        <%}%>
        </ul>
    </div>
</nav>
