package servlet;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DALException;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletIndex", value = "/")
public class ServletIndex extends HttpServlet {

    ArticleManager article = new ArticleManagerImpl();
    CategorieManager categorie = new CategorieManagerImpl();
    List<Article> listeArticles;
    List<Categorie> listeCategories;

    public ServletIndex() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();


        try {
            System.out.println("Je passe dans le try");
            listeArticles = article.selectAll();
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
        String nom = request.getParameter("filtres");
        int noCategorie = Integer.parseInt(request.getParameter("categorie"));

        if (nom != "")
        {
            if (noCategorie == 0) {
                try {
                    listeArticles = article.selectByName(nom);
                    request.setAttribute("articles", listeArticles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    listeArticles = article.selectByNameCategorie(nom,noCategorie);
                    request.setAttribute("articles", listeArticles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        else
        {
            if (noCategorie == 0)
            {
                try{
                    listeArticles = article.selectAll();
                    request.setAttribute("articles",listeArticles );
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else {
                try{
                    listeArticles = article.selectByCategorie(noCategorie);
                    request.setAttribute("articles",listeArticles );
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }

        try {
            listeCategories = categorie.selectAll();
        } catch (BLLException e) {
            e.printStackTrace();
        }
        request.setAttribute("noCategorie",noCategorie);
        request.setAttribute("categories",listeCategories);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        rd.forward(request, response);
    }
}
