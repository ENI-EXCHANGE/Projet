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

        request.setAttribute("encheres",null);
        request.setAttribute("noCategorie",0);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*


        System.out.println("Achat ouvertes : "+request.getParameter("achatsOuvertes"));
        System.out.println("Achat cours : "+request.getParameter("achatCours"));
        System.out.println("Achat remportees : "+request.getParameter("achatRemportees"));

        System.out.println("ventesCours : "+request.getParameter("ventesCours"));
        System.out.println("ventes non debutes : "+request.getParameter("ventesNonDebutees"));
        System.out.println("ventes termine : "+request.getParameter("ventesTerminees"));

*/      HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");



        String nom = request.getParameter("filtres");
        String radio = request.getParameter("radio");
        Integer cat = Integer.valueOf(request.getParameter("categorie"));


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
                        listeArticlesEnchCours = enchere.selectByUtilisateurWithList(usr.getNoUtilisateur(), listeArticles);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                List<Article> listeArticlesRempo = new ArrayList<>();
                if (request.getParameter("encheresRemportees") != null)
                {
                    try {
                        listeArticlesRempo = enchere.gagneWithList(usr,listeArticles);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (request.getParameter("achatsOuvertes") != null & request.getParameter("achatCours") != null & request.getParameter("encheresRemportees") != null)
                {
                    /*
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                }
                if (request.getParameter("achatsOuvertes") != null & request.getParameter("achatCours") != null)
                {
                    try {


                        listeArticles = article.concat(listeArticlesOuv,listeArticlesEnchCours,listeArticlesRempo);
                        listeArticles = article.doublon(listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (request.getParameter("achatCours") != null)
                {
                    listeArticles = listeArticlesEnchCours;
                }
                else if (request.getParameter("achatsOuvertes") != null)
                {
                    listeArticles = listeArticlesOuv;
                }
                else if (request.getParameter("encheresRemportees") != null)
                {
                   // listeArticles = listeArticlesDeb;
                }
    /*
                else if (request.getParameter("ventesTerminees") != null)
                {
                    listeArticles = listeArticlesTerm;
                }
                else if (request.getParameter("encheresRemportees") != null)
                {
                    listeArticles = listeArticlesDeb;
                }

                */

            }

            else if (radio.equals("ventes")) {


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

                if (request.getParameter("ventesCours") != null & request.getParameter("ventesTerminees") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (request.getParameter("ventesCours") != null & request.getParameter("ventesTerminees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (request.getParameter("ventesCours") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (request.getParameter("ventesTerminees") != null & request.getParameter("ventesNonDebutees") != null)
                {
                    try {
                        listeArticles = article.concat(listeArticlesOuv,listeArticlesDeb,listeArticlesTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (request.getParameter("ventesCours") != null)
                {
                    listeArticles = listeArticlesOuv;
                }

                else if (request.getParameter("ventesTerminees") != null)
                {
                    listeArticles = listeArticlesTerm;
                }
                else if (request.getParameter("ventesNonDebutees") != null)
                {
                    listeArticles = listeArticlesDeb;
                }
            }
        }
        else
        {
            request.setAttribute("noCategorie",cat);
            request.setAttribute("categories",listeCategories);
            request.setAttribute("articles",listeArticles);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
            rd.forward(request, response);
        }
/*
        try {
            listeArticles = article.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

   //     request.setAttribute("filtres",nom);

        int noCategorie = 0;

        if (request.getParameter("categorie") !=null)
        {
            noCategorie = Integer.parseInt(request.getParameter("categorie"));
        }




      //  int noCategorie = Integer.parseInt(request.getParameter("categorie"));
/*
        if (radio !=null)
        {
            System.out.println("a");
            System.out.println(radio);
            String achats = "achats";
           if (radio.equals(achats) )
           {
               System.out.println("b");

               if (request.getParameter("achatCours") == "on")
               {
                   System.out.println("c");
                   try {
                       System.out.println("d");
                       listeEncheres = enchere.selectByUtilisateurDate(usr.getNoUtilisateur());
                       listeArticles = article.selectAllDate();
                       for(Enchere enchere : listeEncheres) {
                        //   System.out.println("Nom de l'article :"+enchere.getArticle().getNomArticle());
                       }
                       request.setAttribute("encheres", listeEncheres);
                       request.setAttribute("articles", listeArticles);
                       request.setAttribute("noCategorie",noCategorie);
                       request.setAttribute("categories",listeCategories);

                       RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
                       rd.forward(request, response);

                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }
           }
           else
           {

           }
        }
        else {

            if (nom != "") {
                if (noCategorie == 0) {
                    try {
                        listeArticles = article.selectByName(nom);
                        request.setAttribute("articles", listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        listeArticles = article.selectByNameCategorie(nom, noCategorie);
                        request.setAttribute("articles", listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {
                if (noCategorie == 0) {
                    try {
                        listeArticles = article.selectAllDate();
                        request.setAttribute("articles", listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        listeArticles = article.selectByCategorie(noCategorie);
                        request.setAttribute("articles", listeArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        try {
            listeCategories = categorie.selectAll();
        } catch (BLLException e) {
            e.printStackTrace();
        }*/


        request.setAttribute("noCategorie",cat);
        request.setAttribute("categories",listeCategories);
        request.setAttribute("articles",listeArticles);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        rd.forward(request, response);
    }
}
