package servlet;

import bll.ArticleManagerImpl;
import bll.BLLException;
import bean.ConnexionForm;
import bll.UtilisateurManagerImpl;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletConnexion", value = {"/connexion", "/profil"})
public class ServletConnexion extends HttpServlet {
/*
TODO : mettre en place les cookies pour se souvenir de moi
TODO : gérer le mot de passe oublié
TODO : gérer les sessions User/admin
  */
    UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getServletPath()) {
            case "/profil":
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
                rd.forward(request, response);
                break;

            case "/connexion":

                ArticleManagerImpl article = new ArticleManagerImpl();
                try {
                    System.out.println(article.selectAll());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
                rd2.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch(request.getServletPath()) {
            case "/profil":
                try {
                    //System.out.println("dans do post");
                    String pseudo = request.getParameter("pseudo");
                    String nom =  request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    String email =request.getParameter("email");
                    String telephone =request.getParameter("telephone");
                    String rue =request.getParameter("rue");
                    String code_postal =request.getParameter("code_postal");
                    String ville =request.getParameter("ville");
                    String mot_de_passe =request.getParameter("mot_de_passe");
                    int credit = 0;
                    int admin = 0;
                    //int credit = Integer.parseInt(request.getParameter("credit"));
                    //int admin = Integer.parseInt(request.getParameter("admin"));
                    Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, admin);
                    try {
                        user.validationEmail(email);
                        user.validationMotDePasse(mot_de_passe);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    userTest.addUser(user);


                } catch (BLLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
                rd.forward(request, response);
                break;

            case "/connexion":
                try {
                    String pseudo = request.getParameter("pseudo");
                    String mot_de_passe = request.getParameter("mot_de_passe");
                    Utilisateur user = userTest.selectByPseudo(pseudo);

                    // Création de la session utilisateur
                    HttpSession session = request.getSession();
                    session.setAttribute("utilisateurConnecté", user);

                    //System.out.println(user);


                } catch (DALException | BLLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
                rd2.forward(request, response);
                break;
        }
    }
}
