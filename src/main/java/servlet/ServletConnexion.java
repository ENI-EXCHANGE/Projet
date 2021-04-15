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
    String destinationPage = null;



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
                    RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/index.jsp");
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
                    if ( user!=null){
                        HttpSession session = request.getSession();
                        session.setAttribute("utilisateurConnecte", user);
                        userTest.addUser(user);

                        destinationPage = "/WEB-INF/index.jsp";
                    }else {
                        String message = "champs vides ou incomplets, veuillez recommencer";
                        request.setAttribute("message", message);
                        destinationPage = "/WEB-INF/creationCompte.jsp";
                    }

                } catch (BLLException e) {
                    request.setAttribute("messageErreur", e.getMessage());
                    destinationPage = "/WEB-INF/creationCompte.jsp";
                }
                RequestDispatcher rd = request.getRequestDispatcher(destinationPage);
                rd.forward(request, response);
                break;

            case "/connexion":

                try {
                    //String remember = request.getParameter("remember");
                    String loguser = request.getParameter("login");
                    String mdpuser = request.getParameter("mot_de_passe");
                    Utilisateur checkuser = userTest.authentification(loguser,mdpuser);
                    boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

                    if (checkuser != null ){
                        HttpSession session = request.getSession();
                        session.setAttribute("utilisateurConnecte", checkuser);

                        Cookie cookieLogin = new Cookie("login",loguser);
                        Cookie cookiemdp = new Cookie("mdp",mdpuser);
                        String log = null;
                        String pwd = null;
                        cookieLogin.setMaxAge(60*60);
                        cookiemdp.setMaxAge(60*60);
                        response.addCookie(cookieLogin);
                        response.addCookie(cookiemdp);
                        // voir comment faire pour récupéré la valeur de si c'est coché ou non
                        if (rememberMe){
                            System.out.println("la checkbox remember est cochée");
                            Cookie[] cookies = request.getCookies();

                            if(cookies != null ) {
                                System.out.println("liste des cookies = " +cookies);
                                for(Cookie c : cookies) {
                                    System.out.println("dans le for des cookies");
                                    System.out.println(c.getName());
                                    if(c.getName().equals("login") ) {
                                        cookieLogin = c;
                                        log = c.getValue();
                                        cookieLogin.setValue(log);

                                    }
                                    else if(c.getName().equals("mdp") ) {
                                        cookiemdp = c;
                                        pwd = c.getValue();
                                        cookiemdp.setValue(pwd);
                                        break;
                                    }
                                }
                            }
                        }

                        request.setAttribute("login", log);
                        request.setAttribute("mdp", pwd);

                        destinationPage = "/WEB-INF/index.jsp";

                    } else {
                        String message = "la connexion à votre compte a échoué, vérifiez votre login et/ou mot de passe";
                        request.setAttribute("message", message);
                        destinationPage = "/WEB-INF/connexion.jsp";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }




                RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
                System.out.println(dispatcher);
                dispatcher.forward(request, response);
                break;

            case "/deconnexion":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                    String message = "Vous êtes maintenant déconnecté";
                    request.setAttribute("message", message);
                    RequestDispatcher rd2 = request.getRequestDispatcher("ServletIndex");
                    rd2.forward(request, response);
                }
                break;
        }
    }
}
