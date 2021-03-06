package servlet;

import bll.*;

import bo.Article;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletConnexion", value = {"/connexion", "/creationCompte", "/deconnexion"})
public class ServletConnexion extends HttpServlet {
/*
TODO : mettre en place les cookies pour se souvenir de moi
TODO : gérer le mot de passe oublié
TODO : gérer les sessions User/admin
  */
    UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();
    String destinationPage = null;

    ArticleManager article = new ArticleManagerImpl();
    CategorieManager categorie = new CategorieManagerImpl();
    EnchereManager enchere = new EnchereManagerImpl();

    List<Article> listeArticles;
    List<Categorie> listeCategories;
    List<Enchere> listeEncheres;

    public ServletConnexion() throws BLLException, DALException {
    }


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
                    try {
                        //    System.out.println("Je passe dans le try DOGET de index");

                        listeArticles = article.selectAllDate();
                        request.setAttribute("articles",listeArticles );

                        listeCategories = categorie.selectAll();
                        request.setAttribute("categories",listeCategories);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    request.setAttribute("encheres",null);
                    request.setAttribute("noCategorie",0);
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
                    Cookie cookieLogin = new Cookie("login",loguser);
                    Cookie cookiemdp = new Cookie("mdp",mdpuser);
                    String log = null;
                    String pwd = null;
                    cookieLogin.setMaxAge(60*60);
                    cookiemdp.setMaxAge(60*60);
                    response.addCookie(cookieLogin);
                    response.addCookie(cookiemdp);

                    if (rememberMe){
                        Cookie[] cookies = request.getCookies();

                        if(cookies != null ) {
                            for(Cookie c : cookies) {
                               if(c.getName().equals("login") ) {
                                    cookieLogin = c;
                                    log = cookieLogin.getValue();
                                }
                                else if(c.getName().equals("mdp")) {
                                    cookiemdp = c;
                                    pwd = cookiemdp.getValue();

                                }
                            }
                        }
                    }
                    request.setAttribute("login", log);
                    request.setAttribute("mdp", pwd);

                    if (checkuser != null ){
                        if (checkuser.getAdministrateur()==2){
                            String message = "Ce compte est invalide, désolé...";
                            request.setAttribute("message", message);
                            destinationPage = "/WEB-INF/connexion.jsp";
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("utilisateurConnecte", checkuser);

                            try {
                                //    System.out.println("Je passe dans le try DOGET de index");

                                listeArticles = article.selectAllDate();
                                request.setAttribute("articles",listeArticles );

                                listeCategories = categorie.selectAll();
                                request.setAttribute("categories",listeCategories);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            request.setAttribute("encheres",null);
                            request.setAttribute("noCategorie",0);

                            destinationPage = "/WEB-INF/index.jsp";
                        }
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
                    RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/index.jsp");
                    rd2.forward(request, response);
                }
                break;
        }
    }
}
