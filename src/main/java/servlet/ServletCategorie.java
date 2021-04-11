package servlet;

import bll.BLLException;
import bll.CategorieManager;
import bll.CategorieManagerImpl;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletCategorie", value = "/Categories")
public class ServletCategorie extends HttpServlet {

    CategorieManager cat = new CategorieManagerImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecté");
        request.setAttribute("usr",usr);
        try {
            List<Categorie> listeCatégorie = cat.selectAll();
            request.setAttribute("listeCategories",listeCatégorie );

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesCategories.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecté");
            request.setAttribute("usr",usr);

            if (request.getParameter("no_categorie") != null){
                int no =Integer.parseInt(request.getParameter("no_categorie"));
                cat.delete(no);
            }
            if (request.getParameter("libelle") != null) {
                String libelle = request.getParameter("libelle");
                cat.insert(libelle);
            }

            List<Categorie> listeCatégorie = cat.selectAll();
            request.setAttribute("listeCategories",listeCatégorie );

        } catch (BLLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesCategories.jsp");
        rd.forward(request, response);

    }

}
