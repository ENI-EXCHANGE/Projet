package servlet;

import bll.ArticleManagerImpl;
import bll.BLLException;
import bo.Article;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletIndex", value = "/")
public class ServletIndex extends HttpServlet {

    ArticleManagerImpl article = new ArticleManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManagerImpl article = new ArticleManagerImpl();
        try {
        //    List<Article> listeArticles = article.selectAll();
          //  request.setAttribute("liste",listeArticles );

         //   System.out.println(listeArticles);
       //    System.out.println(article.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
