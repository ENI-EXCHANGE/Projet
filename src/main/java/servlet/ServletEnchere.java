package servlet;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ServletEnchere", value = {"/Enchere", "/Modifier", "/Supprimer"})
public class ServletEnchere extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();
    ArticleManager art = new ArticleManagerImpl();
    EnchereManager ench = new EnchereManagerImpl();
    CategorieManager cat = new CategorieManagerImpl();
    RetraitManager ret = new RetraitManagerImpl();

    public ServletEnchere() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Article ArticleSelectionne = art.selectById(Integer.parseInt(request.getParameter("id")));
            Categorie CategorieArticle = cat.selectById(ArticleSelectionne.getCategorie().getNoCategorie());
            Utilisateur UtilisateurArticle = usr.selectById(ArticleSelectionne.getUtilisateur().getNoUtilisateur());
            Retrait RetraitArticle = ret.selectById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("Retrait", RetraitArticle);
            request.setAttribute("ArticleSelectionne",ArticleSelectionne );
            request.setAttribute("CategorieArticle", CategorieArticle);
            request.setAttribute("UtilisateurArticle", UtilisateurArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Enchere.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
        int id = Integer.parseInt(request.getParameter("idArticle"));

        switch (request.getServletPath()) {
            case "/Modifier" :

                try {
                    String nom = request.getParameter("nom");
                    String desc = request.getParameter("description");
                    Categorie categorie = cat.selectByLibelle(request.getParameter("categorie"));
                    int idArticle = Integer.parseInt(request.getParameter("idArticle"));

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

                    Article nouvelArticle = new Article(idArticle, nom, desc, dateDeb, dateFi, prix, prix, user, categorie);
                    Retrait nouveauRetrait = new Retrait(nouvelArticle, rue, ville, cp);

                    System.out.println(nouvelArticle);

                    Article noArt = art.modifierArticle(nouvelArticle);
                    ret.update(nouveauRetrait);

                    Categorie CategorieArticle = cat.selectById(nouvelArticle.getCategorie().getNoCategorie());
                    Utilisateur UtilisateurArticle = usr.selectById(nouvelArticle.getUtilisateur().getNoUtilisateur());
                    Retrait RetraitArticle = ret.selectById(idArticle);
                    request.setAttribute("Retrait", RetraitArticle);
                    request.setAttribute("ArticleSelectionne",nouvelArticle );
                    request.setAttribute("CategorieArticle", CategorieArticle);
                    request.setAttribute("UtilisateurArticle", UtilisateurArticle);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Enchere.jsp");
                rd.forward(request, response);

                break;

            case "/Supprimer" :
                try {
                    art.supprimerArticle(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("ProfilSelectionne",user );
                RequestDispatcher rd1 = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
                rd1.forward(request, response);

                break;
        }
    }
}
