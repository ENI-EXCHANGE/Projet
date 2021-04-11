package servlet;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletArticle", value = "/Article")
public class ServletArticle extends HttpServlet {

    ArticleManager art = new ArticleManagerImpl();
    UtilisateurManager usr = new UtilisateurManagerImpl();
    CategorieManager cat = new CategorieManagerImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Article ArticleSelectionne = art.selectById(2);
            Categorie CategorieArticle = cat.selectById(ArticleSelectionne.getNo_categorire());
            Utilisateur UtilisateurArticle = usr.selectById(ArticleSelectionne.getNo_article());
            request.setAttribute("ArticleSelectionne",ArticleSelectionne );
            request.setAttribute("CategorieArticle", CategorieArticle);
            request.setAttribute("UtilisateurArticle", UtilisateurArticle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
