<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 09/04/2021
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/accueil">Encheres</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <%
            if(session.getAttribute("ADMIN").equals(false)) {
        %>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/Encheres">Enchères</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() %>/Article">Vendre un Article</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/profil">Mon profil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/accueil">Déconnexion</a>
            </li>
        </ul>
        <%
        } else if (session.getAttribute("ADMIN").equals(true)){
        %>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() %>/Categories">Vendre un Article</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/profil">Mon profil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/accueil">Déconnexion</a>
            </li>
        </ul>
        <%
            } else {
                %>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/connexion">S'inscrire/Se Connecter</a>
            </li>
        </ul>
        <%}%>

    </div>
</nav>
