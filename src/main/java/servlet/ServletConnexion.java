package servlet;

import bll.BLLException;

import bll.UtilisateurManagerImpl;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletConnexion", value = {"/connexion", "/creationCompte", "/deconnexion"})
public class ServletConnexion extends HttpServlet {
/*
TODO : mettre en place les cookies pour se souvenir de moi
TODO : gérer le mot de passe oublié
TODO : gérer les sessions User/admin
  */
    UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();
    String destPage = null;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getServletPath()) {
            case "/creationCompte":
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
                rd.forward(request, response);
                break;

            case "/connexion":

                RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
                rd2.forward(request, response);
                break;
            case "/deconnexion":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("utilisateurConnecte");
                    String message = "Vous êtes maintenant déconnecté";
                    request.setAttribute("message", message);
                    RequestDispatcher rd3 = request.getRequestDispatcher("/index.jsp");
                    rd3.forward(request, response);
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch(request.getServletPath()) {
            case "/creationCompte":
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
                    if ( user.checkuser()==true){
                        HttpSession session = request.getSession();
                        session.setAttribute("utilisateurConnecte", user);
                        userTest.addUser(user);

                        destPage = "/index.jsp";
                    }else {
                        String message = "champs vides ou incomplets, veuillez recommencer";
                        request.setAttribute("message", message);
                        destPage = "/WEB-INF/creationCompte.jsp";
                    }

                } catch (BLLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher rd = request.getRequestDispatcher(destPage);
                rd.forward(request, response);
                break;

            case "/connexion":
                try {
                    String login = request.getParameter("pseudo");
                    String mot_de_passe = request.getParameter("mot_de_passe");
                    Utilisateur user = userTest.selectByPseudo(login);
                    Utilisateur checkuser = userTest.checkLogin(login,mot_de_passe);

                    if (checkuser != null ){
                        HttpSession session = request.getSession();
                        session.setAttribute("utilisateurConnecte", user);
                        destPage = "/index.jsp";
                    } else {
                        String message = "la connexion à votre compte a échoué, vérifiez votre login et/ou mot de passe";
                        request.setAttribute("message", message);
                        destPage = "/WEB-INF/connexion.jsp";
                    }

                } catch (DALException e) {
                    e.printStackTrace();
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                System.out.println(dispatcher);
                dispatcher.forward(request, response);
                break;

            case "/deconnexion":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("utilisateurConnecte");
                    String message = "Vous êtes maintenant déconnecté";
                    request.setAttribute("message", message);
                    RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/index.jsp");
                    rd2.forward(request, response);
                }
                break;
        }
    }
}
