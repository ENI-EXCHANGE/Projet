package servlet;

import bll.*;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletCompte", value = "/Compte")
public class ServletCompte extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();
    ArticleManager art = new ArticleManagerImpl();
    EnchereManager ench = new EnchereManagerImpl();

    public ServletCompte() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (request.getParameter("pseudo") != null){
                Utilisateur UtilisateurArticle = usr.selectByPseudo(request.getParameter("pseudo"));
                request.setAttribute("ProfilSelectionne", UtilisateurArticle);
            }else{
                HttpSession session = request.getSession();
                Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");
                System.out.println(usr.getNoUtilisateur());

                List<Article> listeArticles = art.selectByUtilisateur(usr.getNoUtilisateur());
                List<Enchere> listeEncheres = ench.selectByUtilisateur(usr.getNoUtilisateur());
                System.out.println(listeEncheres);
                request.setAttribute("listeEnch", listeEncheres);
                request.setAttribute("listeArt", listeArticles);
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
