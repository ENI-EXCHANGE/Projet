package servlet;

import bll.ArticleManagerImpl;
import bll.BLLException;
import bo.Article;
import bo.Utilisateur;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletIndex", value = "/")
public class ServletIndex extends HttpServlet {

    ArticleManagerImpl article = new ArticleManagerImpl();
    List<Article> listeArticles = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
