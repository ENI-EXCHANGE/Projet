package servlet;

import bll.*;
import bo.*;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ServletArticle", value = "/Article")
public class ServletArticle extends HttpServlet {

    ArticleManager art = new ArticleManagerImpl();
    UtilisateurManager usr = new UtilisateurManagerImpl();
    CategorieManager cat = new CategorieManagerImpl();
    EnchereManager ench = new EnchereManagerImpl();
    RetraitManager ret = new RetraitManagerImpl();

    public ServletArticle() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Article ArticleSelectionne = art.selectById(Integer.parseInt(request.getParameter("id")));
            Categorie CategorieArticle = cat.selectById(ArticleSelectionne.getCategorie().getNoCategorie());
            Utilisateur UtilisateurArticle = usr.selectById(ArticleSelectionne.getUtilisateur().getNoUtilisateur());
            request.setAttribute("ArticleSelectionne",ArticleSelectionne );
            request.setAttribute("CategorieArticle", CategorieArticle);
            request.setAttribute("UtilisateurArticle", UtilisateurArticle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
            String nom = request.getParameter("nom");
            String desc = request.getParameter("description");
            Categorie categorie = cat.selectByLibelle(request.getParameter("categorie"));

            String f = request.getParameter("dateFin");
            String s = request.getParameter("dateDebut");
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss").parse(s);

            LocalDate dateDebut = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
            LocalDate dateFin = LocalDate.parse(f, DateTimeFormatter.ISO_DATE);

            Date dateDeb = Date.valueOf(dateDebut);
            Date dateFi = Date.valueOf(dateFin);
            //Date dateDebut = (Date) sdf.parse(s);
            //Date dateFin = (Date) sdf.parse(f);

            int prix = Integer.parseInt(request.getParameter("prix"));

            String rue = request.getParameter("rue");
            String ville = request.getParameter("ville");
            String cp = request.getParameter("cp");

            //Timestamp dateFinal = new Timestamp(LocalDate.now().getLong());
            System.out.println(user.getPseudo());
            Article nouvelArticle = new Article(nom, desc, dateDeb, dateFi, prix, prix, user, categorie);
            Enchere nouvelEnchere = new Enchere(user, nouvelArticle, dateFi, prix);
            Retrait nouveauRetrait = new Retrait(nouvelArticle, rue, ville, cp);
            art.ajouterArticle(nouvelArticle);
            ret.insert(nouveauRetrait);
            ench.insert(nouvelEnchere);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Article ArticleSelectionne = art.selectById(2);
            Categorie CategorieArticle = cat.selectById(ArticleSelectionne.getCategorie().getNoCategorie());
            Utilisateur UtilisateurArticle = usr.selectById(ArticleSelectionne.getUtilisateur().getNoUtilisateur());
            request.setAttribute("ArticleSelectionne",ArticleSelectionne );
            request.setAttribute("CategorieArticle", CategorieArticle);
            request.setAttribute("UtilisateurArticle", UtilisateurArticle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article.jsp");
        rd.forward(request, response);

    }
}
