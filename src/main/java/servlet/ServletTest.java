package servlet;

import bll.ArticleManagerImpl;
import bll.BLLException;
import bll.UtilisateurManagerImpl;
import bo.Article;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ServletTest", value = "/accueil")

public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Article ArticleTest = new Article("artiTest", "ARtde", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 1,  2, 1, 1);

        //Utilisateur user1 = new Utilisateur( "user1", "dupond", "jean", "test@email.fr", "0299010203", "rue montjoie", "35400", "st malo", "password", 0, 1);

        UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();
        ArticleManagerImpl artTest = new ArticleManagerImpl();

        try {
            artTest.ajouterArticle(ArticleTest);
        } catch (DALException e) {
            e.printStackTrace();
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
