package servlet;

import bll.BLLException;
import bll.UtilisateurManager;
import bll.UtilisateurManagerImpl;
import bo.Categorie;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletUtilisateurs", value = "/Utilisateurs")
public class ServletUtilisateurs extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();

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

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesUtilisateurs.jsp");
        rd.forward(request, response);
    }
}
