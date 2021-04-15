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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ServletArticle", value = {"/Article", "/encherir"})
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
            Article ArticleSelectionneGet = art.selectById(Integer.parseInt(request.getParameter("id")));
            Categorie CategorieArticle = cat.selectById(ArticleSelectionneGet.getCategorie().getNoCategorie());
            Utilisateur UtilisateurArticle = usr.selectById(ArticleSelectionneGet.getUtilisateur().getNoUtilisateur());
            request.setAttribute("ArticleSelectionne",ArticleSelectionneGet );
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
        // recupération de la session :
        HttpSession session = request.getSession();
        // variables de session :
        Utilisateur utilisateurConnected = (Utilisateur) session.getAttribute("utilisateurConnecte");


        switch (request.getServletPath()) {
            case "/Article":
                try {


                    // request de CreerEnchere.jsp pour la création d'un nouvel article à mettre en vente :
                    String nom = request.getParameter("nom");
                    String desc = request.getParameter("description");
                    String f = request.getParameter("dateFin");
                    String s = request.getParameter("dateDebut");
                    LocalDate dateDebut = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
                    LocalDate dateFin = LocalDate.parse(f, DateTimeFormatter.ISO_DATE);
                    Date dateDeb = Date.valueOf(dateDebut);
                    Date dateFi = Date.valueOf(dateFin);

                    String rue = request.getParameter("rue");
                    String ville = request.getParameter("ville");
                    String cp = request.getParameter("cp");
                    Categorie categorie = cat.selectByLibelle(request.getParameter("categorie"));
                    //Categorie CategorieArticle  = cat.selectById(ArticleSelectionnePost.getCategorie().getNoCategorie());
                    int prix = Integer.parseInt(request.getParameter("prix"));

                    Article nouvelArticle = new Article(nom, desc, dateDeb, dateFi, prix, prix, utilisateurConnected, categorie);
                    //Enchere nouvelEnchere = new Enchere(utilisateurConnected, nouvelArticle, dateFi, prix);
                    Retrait nouveauRetrait = new Retrait(nouvelArticle, rue, ville, cp);

                    art.ajouterArticle(nouvelArticle);
                    ret.insert(nouveauRetrait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article.jsp");
                rd.forward(request, response);
                break;

            case "/encherir":
                try {
                    // request de Article.jsp pour encherir :
                    Article ArtSelectedEnch = art.selectById(Integer.parseInt(request.getParameter("idArticleEnch")));
                    Categorie CategorieArticle = cat.selectById(ArtSelectedEnch.getCategorie().getNoCategorie());
                    int proposition = Integer.parseInt(request.getParameter("point"));
                    Utilisateur UtilisateurArticle = usr.selectById(ArtSelectedEnch.getUtilisateur().getNoUtilisateur());


                    request.setAttribute("ArticleSelectionne", ArtSelectedEnch);
                    request.setAttribute("UtilisateurArticle", UtilisateurArticle);
                    request.setAttribute("CategorieArticle", CategorieArticle);

                    System.out.println("user connecté = " + utilisateurConnected.getPseudo() + " num= "+ utilisateurConnected.getNoUtilisateur());
                    System.out.println("ancien encherisseur = " + UtilisateurArticle.getNoUtilisateur());

                    if (proposition > ArtSelectedEnch.getPrixVente() && (utilisateurConnected.getCredit()- proposition)>0 ) {
                        System.out.println("condtions pour encherir validées");
                        //System.out.println(Date.valueOf(LocalDateTime.now());

                        Enchere nouvelleEnchere = new Enchere(utilisateurConnected, ArtSelectedEnch, proposition);
                        ench.insert(nouvelleEnchere); //ok
                        Enchere enchUpdate = ench.selectById(utilisateurConnected.getNoUtilisateur(),ArtSelectedEnch.getNoArticle());

                        usr.debiterCredit(enchUpdate.getUtilisateur().getNoUtilisateur(), proposition);//ok
                        usr.ajouterCredit(UtilisateurArticle.getNoUtilisateur(), ArtSelectedEnch.getPrixVente()); // ne prend pas le bon user

                        ArtSelectedEnch.setPrixVente(proposition);//ok
                        art.modifierArticle(ArtSelectedEnch);//ok
                    }else {
                        throw new Exception(" vous n'avez pas assez de crédit");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Article.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }}
