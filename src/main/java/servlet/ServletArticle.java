package servlet;

import bll.ArticleManager;
import bll.ArticleManagerImpl;
import bll.BLLException;
import bo.Article;
import bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletArticle", value = "/Article")
public class ServletArticle extends HttpServlet {

    ArticleManager art = new ArticleManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Article ArticleSelectionne = art.selectById(2);
            request.setAttribute("ArticleSelectionne",ArticleSelectionne );

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
