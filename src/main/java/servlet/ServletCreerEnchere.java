package servlet;

import bll.BLLException;
import bll.CategorieManager;
import bll.CategorieManagerImpl;
import bo.Categorie;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletCreerEnchere", value = "/CreerEnchere")
public class ServletCreerEnchere extends HttpServlet {

    CategorieManager cat = new CategorieManagerImpl();

    public ServletCreerEnchere() throws BLLException, DALException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("utilisateurConnecte");
        request.setAttribute("usr",usr);
        try {
            List<Categorie> listeCatégorie = cat.selectAll();
            request.setAttribute("listeCategories",listeCatégorie );

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerEnchere.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerEnchere.jsp");
        rd.forward(request, response);
    }
}
