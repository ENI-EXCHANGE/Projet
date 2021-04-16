package servlet;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ServletIndex", value = "/")
public class ServletIndex extends HttpServlet {

    ArticleManager article = new ArticleManagerImpl();
    CategorieManager categorie = new CategorieManagerImpl();
    EnchereManager enchere = new EnchereManagerImpl();

    List<Article> listeArticles;
    List<Categorie> listeCategories;
    List<Enchere> listeEncheres;

    public ServletIndex() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


        try {
        //    System.out.println("Je passe dans le try DOGET de index");

            listeArticles = article.selectAllDate();
            request.setAttribute("articles",listeArticles );

            listeCategories = categorie.selectAll();
            request.setAttribute("categories",listeCategories);


        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("noCategorie",0);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");



        String nom = request.getParameter("filtres");
        String radio = request.getParameter("radio");
        Integer cat = Integer.valueOf(request.getParameter("categorie"));


        boolean radioVentes =false;

        boolean achatOuvertes =false;
        boolean achatCours =false;
        boolean achatRemportees =false;

        boolean ventesCours =false;
        boolean ventesNonDebutees =false;
        boolean ventesTerminees =false;




        if (nom != "") {
            try {

                listeArticles = article.selectByName(nom);
                listeArticles = article.selectByCours(listeArticles);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                listeArticles = article.selectAllDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (cat == 0)
        {
        }
        else
        {
            try {
                listeArticles = article.selectByCategorieWithListe(cat,listeArticles);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (radio != null) {

            if (nom != "")
            {
                try {
                    listeArticles = article.selectByName(nom);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    listeArticles = article.selectAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cat == 0)
            {
            }
            else
            {
                try {
                    listeArticles = article.selectByCategorieWithListe(cat,listeArticles);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (radio.equals("achats")) {

                List<Article> listeArticlesOuv = new ArrayList<>();
                if (request.getParameter("achatsOuvertes") != null)
                {
                    try {
                        listeArticlesOuv = article.selectByCours(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                List<Article> listeArticlesEnchCours = new ArrayList<>();
                if (request.getParameter("achatCours") != null)
                {
                    try {
                       // listeArticlesEnchCours = enchere.selectByUtilisateurWithList(usr.getNoUtilisateur(), listeArticles);
                        listeArticlesEnchCours = enchere.selectByUtilisateurWithList(usr, listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                List<Article> listeArticlesRempo = new ArrayList<>();
                if (request.getParameter("achatRemportees") != null)
                {
                    try {
                        listeArticlesRempo = enchere.enchereRemporteWithList(usr,listeArticles);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (request.getParameter("achatsOuvertes") != null & request.getParameter("achatCours") != null & request.getParameter("encheresRemportees") != null)
                {

                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesEnchCours,listeArticlesRempo);
                        listeArticles = article.doublon(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    achatCours = true;
                    achatRemportees = true;
                    achatOuvertes = true;
                }
                else if (request.getParameter("achatsOuvertes") != null & request.getParameter("achatCours") != null)
                {
                    try {

                        listeArticles = article.concat(listeArticlesOuv,listeArticlesEnchCours,listeArticlesRempo);
                        listeArticles = article.doublon(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    achatCours = true;
                    achatOuvertes = true;
                }
                else if (request.getParameter("achatRemportees") != null & request.getParameter("achatCours") != null)
                {
                    try {

                        listeArticles = article.concat(listeArticlesOuv,listeArticlesEnchCours,listeArticlesRempo);
                        listeArticles = article.doublon(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    achatCours = true;
                    achatRemportees = true;
                }
                else if (request.getParameter("achatRemportees") != null & request.getParameter("achatsOuvertes") != null)
                {
                    try {

                        listeArticles = article.concat(listeArticlesOuv,listeArticlesEnchCours,listeArticlesRempo);
                        listeArticles = article.doublon(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    achatOuvertes = true;
                    achatRemportees = true;
                }
                else if (request.getParameter("achatCours") != null)
                {
                    listeArticles = listeArticlesEnchCours;
                    achatCours = true;
                }
                else if (request.getParameter("achatsOuvertes") != null)
                {
                    listeArticles = listeArticlesOuv;
                    achatOuvertes = true;
                }
                else if (request.getParameter("achatRemportees") != null)
                {
                    listeArticles = listeArticlesRempo;
                    achatRemportees = true;
                }
                else
                {
                    try {
                        listeArticles = article.selectByCours(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            else if (radio.equals("ventes")) {

                radioVentes = true;
                try {
                    listeArticles = article.selectByUtilisateurWithList(usr.getNoUtilisateur(),listeArticles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<Article> listeArticlesOuv = new ArrayList<>();
                if (request.getParameter("ventesCours") != null)
                {
                    try {

                        listeArticlesOuv = article.selectByUtilisateurCoursWhithListe(usr.getNoUtilisateur(),listeArticles);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                List<Article> listeArticlesDeb = new ArrayList<>();
                if (request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticlesDeb = article.selectByUtilisateurAttWithList(usr.getNoUtilisateur(),listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                List<Article> listeArticlesTerm = new ArrayList<>();
                if (request.getParameter("ventesTerminees") != null)
                {
                    try {
                        listeArticlesTerm = article.selectByUtilisateurTermineWhithListe(usr.getNoUtilisateur(),listeArticles);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Concatenation des listes

                if (request.getParameter("ventesCours") != null & request.getParameter("ventesTerminees") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ventesCours = true;
                    ventesTerminees = true;
                    ventesNonDebutees = true;

                }
                else if (request.getParameter("ventesCours") != null & request.getParameter("ventesTerminees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ventesCours = true;
                    ventesTerminees = true;

                }
                else if (request.getParameter("ventesCours") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ventesCours = true;
                    ventesNonDebutees = true;
                }
                else if (request.getParameter("ventesTerminees") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ventesTerminees = true;
                    ventesNonDebutees = true;
                }
                else if (request.getParameter("ventesCours") != null)
                {
                    listeArticles = listeArticlesOuv;
                    ventesCours = true;

                }

                else if (request.getParameter("ventesTerminees") != null)
                {
                    listeArticles = listeArticlesTerm;
                    ventesTerminees = true;
                }
                else if (request.getParameter("ventesNonDebutees") != null)
                {
                    listeArticles = listeArticlesDeb;
                    ventesNonDebutees= true;
                }
            }
        }
        else
        {
            request.setAttribute( "filtre",nom);
            request.setAttribute( "radioVentes",radioVentes);

            request.setAttribute( "achatOuvertes",achatOuvertes);
            request.setAttribute( "achatCours",achatCours);
            request.setAttribute( "achatRemportees",achatRemportees);

            request.setAttribute( "ventesCours",ventesCours);
            request.setAttribute( "ventesNonDebutees",ventesNonDebutees);
            request.setAttribute( "ventesTerminees",ventesTerminees);

            request.setAttribute("noCategorie",cat);
            request.setAttribute("categories",listeCategories);
            request.setAttribute("articles",listeArticles);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
            rd.forward(request, response);
        }
        request.setAttribute( "filtre",nom);

        request.setAttribute( "radioVentes",radioVentes);

        request.setAttribute( "achatOuvertes",achatOuvertes);
        request.setAttribute( "achatCours",achatCours);
        request.setAttribute( "achatRemportees",achatRemportees);

        request.setAttribute( "ventesCours",ventesCours);
        request.setAttribute( "ventesNonDebutees",ventesNonDebutees);
        request.setAttribute( "ventesTerminees",ventesTerminees);

        request.setAttribute("noCategorie",cat);
        request.setAttribute("categories",listeCategories);
        request.setAttribute("articles",listeArticles);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        rd.forward(request, response);
    }
}
