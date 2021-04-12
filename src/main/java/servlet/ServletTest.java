package servlet;

import bll.ArticleManagerImpl;
import bll.BLLException;
import bll.UtilisateurManagerImpl;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletTest", value = "/test")

public class ServletTest extends HttpServlet {

    UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();
    ArticleManagerImpl artTest = new ArticleManagerImpl();

    public ServletTest() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*

        try {
            List<Utilisateur> lesUtilisateurs = userTest.getListUsers();
            request.setAttribute("lesUtilisateurs",lesUtilisateurs );

        } catch (BLLException e) {
            e.printStackTrace();
        }
*/

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* try {
            System.out.println("dans do post");
            String pseudo = request.getParameter("pseudo");
            String nom =  request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email =request.getParameter("email");
            String telephone =request.getParameter("telephone");
            String rue =request.getParameter("rue");
            String code_postal =request.getParameter("code_postal");
            String ville =request.getParameter("ville");
            String mot_de_passe =request.getParameter("mot_de_passe");
            int credit = Integer.parseInt(request.getParameter("credit"));
            int admin = Integer.parseInt(request.getParameter("admin"));
            Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, admin);
            userTest.addUser(user);


        } catch (BLLException e) {
            e.printStackTrace();
        }*/

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/.jsp");
        rd.forward(request, response);

    }
}
