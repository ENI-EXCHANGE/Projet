package servlet;

import bll.BLLException;
import bll.CategorieManager;
import bll.CategorieManagerImpl;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletCategorie", value = "/Categories")
public class ServletCategorie extends HttpServlet {

    CategorieManager cat = new CategorieManagerImpl();

    public ServletCategorie() throws BLLException, DALException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");
        request.setAttribute("usr",usr);
        try {
            List<Categorie> listeCategorie = cat.selectAll();
            request.setAttribute("listeCategories",listeCategorie );

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
            Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnectÃ©");
            request.setAttribute("usr",usr);

            if (request.getParameter("no_categorie") != null){
                int no =Integer.parseInt(request.getParameter("no_categorie"));
                cat.delete(no);
            }
            if (request.getParameter("libelle") != null) {
                String libelle = request.getParameter("libelle");
                cat.insert(libelle);
            }

            List<Categorie> listeCatÃ©gorie = cat.selectAll();
            request.setAttribute("listeCategories",listeCatÃ©gorie );

        } catch (BLLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GestionDesCategories.jsp");
        rd.forward(request, response);

    }

}
