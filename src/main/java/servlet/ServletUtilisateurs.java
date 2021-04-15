package servlet;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletUtilisateurs", value = {"/Utilisateurs", "/SupprimerUtil", "/Invalider"})
public class ServletUtilisateurs extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();
    ArticleManager art = new ArticleManagerImpl();

    public ServletUtilisateurs() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
        request.setAttribute("usr",user);
        try {
            List<Utilisateur> listeUtilisateurs = usr.getListUsers();
            request.setAttribute("listeUtilisateurs",listeUtilisateurs );

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesUtilisateurs.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecté");
        request.setAttribute("usr", user);

        switch(request.getServletPath()) {
            case "/SupprimerUtil" :

                try {
                    if (request.getParameter("no_utilisateur") != null) {
                        int no = Integer.parseInt(request.getParameter("no_utilisateur"));
                        usr.removeUser(usr.selectById(no));
                    }

                    List<Utilisateur> listeUtilisateurs = usr.getListUsers();
                    request.setAttribute("listeUtilisateurs", listeUtilisateurs);

                } catch (BLLException | DALException e) {
                    e.printStackTrace();
                }

                break;
            case "/Invalider" :

                try {
                    if (request.getParameter("no_utilisateur") != null) {
                        int no = Integer.parseInt(request.getParameter("no_utilisateur"));
                        usr.invalider(usr.selectById(no));
                        for (Article arti : art.selectByUtilisateur(no)){
                            art.supprimerArticle(arti.getNoArticle());
                        }
                    }

                    List<Utilisateur> listeUtilisateurs = usr.getListUsers();
                    request.setAttribute("listeUtilisateurs", listeUtilisateurs);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesUtilisateurs.jsp");
        rd.forward(request, response);
    }
}
