package servlet;

import bll.UtilisateurManager;
import bll.UtilisateurManagerImpl;
import bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletCompte", value = "/Compte")
public class ServletCompte extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (request.getParameter("pseudo") != null){
                Utilisateur UtilisateurArticle = usr.selectByPseudo(request.getParameter("pseudo"));
                request.setAttribute("ProfilSelectionne", UtilisateurArticle);
            }else{
                HttpSession session = request.getSession();
                Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");
                request.setAttribute("ProfilSelectionne", usr);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
        rd.forward(request, response);
    }
}
