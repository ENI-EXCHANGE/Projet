package servlet;

import bll.*;
import bo.*;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ServletCreerEnchere", value = "/CreerEnchere")
public class ServletCreerEnchere extends HttpServlet {

    CategorieManager cat = new CategorieManagerImpl();

    public ServletCreerEnchere() throws BLLException, DALException {
    }

    ArticleManager art = new ArticleManagerImpl();
    UtilisateurManager usr = new UtilisateurManagerImpl();
    EnchereManager ench = new EnchereManagerImpl();
    RetraitManager ret = new RetraitManagerImpl();

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


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article.jsp");
        rd.forward(request, response);
    }
}
