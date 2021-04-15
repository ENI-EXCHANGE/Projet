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

@WebServlet(name = "ServletCompte", value = {"/Compte", "/ModifierCompte","/AjouterCredits","/Suppression","/ConfirmationSuppression","/Modification", "/ValiderCredits"})
public class ServletCompte extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();
    ArticleManager art = new ArticleManagerImpl();
    EnchereManager ench = new EnchereManagerImpl();

    public ServletCompte() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                if (request.getParameter("pseudo") != null) {

                    Utilisateur UtilisateurArticle = usr.selectByPseudo(request.getParameter("pseudo"));
                    request.setAttribute("ProfilSelectionne", UtilisateurArticle);
                } else {
                    HttpSession session = request.getSession();
                    Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
                    List<Article> ListeArticleAttente = art.selectByUtilisateurAtt(user.getNoUtilisateur());
                    List<Article> ListeArticleEnCours = art.selectByUtilisateurCours(user.getNoUtilisateur());
                    List<Article> listeArticlesTermine = art.selectByUtilisateurTermine(user.getNoUtilisateur());
                    List<Enchere> listeEncheres = ench.selectByUtilisateur(user.getNoUtilisateur());
                    request.setAttribute("listeArtAtt", ListeArticleAttente);
                    request.setAttribute("listeArtCours", ListeArticleEnCours);
                    request.setAttribute("listeEnch", listeEncheres);
                    request.setAttribute("listeArt", listeArticlesTermine);
                    request.setAttribute("ProfilSelectionne", user);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
            rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");

        switch(request.getServletPath()) {
            case "/ModifierCompte" :
                request.setAttribute("ProfilSelectionne", user);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierCompte.jsp");
                rd.forward(request, response);
                break;
            case "/Modification" :

                String pseudo = request.getParameter("pseudo");
                String nom =  request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email =request.getParameter("email");
                String telephone =request.getParameter("telephone");
                String rue =request.getParameter("rue");
                String code_postal =request.getParameter("code_postal");
                String ville =request.getParameter("ville");
                String mot_de_passe =request.getParameter("mot_de_passe");
                int no_utilisateur = user.getNoUtilisateur();
                int credits=user.getCredit();
                int admin=user.getAdministrateur();

                Utilisateur utilisateurModifie = new Utilisateur(no_utilisateur,pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credits,admin);
                try {
                    usr.updateUser(utilisateurModifie);
                } catch (BLLException e) {
                    e.printStackTrace();
                }

                session.removeAttribute("utilisateurConnecte");
                session.setAttribute("utilisateurConnecte", utilisateurModifie);

                List<Article> listeArticles = null;
                try {
                    listeArticles = art.selectByUtilisateur(utilisateurModifie.getNoUtilisateur());
                    List<Enchere> listeEncheres = ench.selectByUtilisateur(utilisateurModifie.getNoUtilisateur());

                    request.setAttribute("listeEnch", listeEncheres);
                    request.setAttribute("listeArt", listeArticles);
                    request.setAttribute("ProfilSelectionne", utilisateurModifie);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                RequestDispatcher rd4 = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
                rd4.forward(request, response);
                break;
            case "/ValiderCredits" :
                int id = user.getNoUtilisateur();
                int credit = Integer.parseInt(request.getParameter("ajout"));
                try {
                    usr.ajouterCredit(id, credit);
                    Utilisateur utilisateurCredit = usr.selectById(id);
                    session.setAttribute("ProfilSelectionne", utilisateurCredit);
                    listeArticles = art.selectByUtilisateur(utilisateurCredit.getNoUtilisateur());
                    List<Enchere> listeEncheres = ench.selectByUtilisateur(utilisateurCredit.getNoUtilisateur());

                    request.setAttribute("listeEnch", listeEncheres);
                    request.setAttribute("listeArt", listeArticles);
                    request.setAttribute("ProfilSelectionne", utilisateurCredit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestDispatcher rd1 = request.getRequestDispatcher("/WEB-INF/Compte.jsp");
                rd1.forward(request, response);
                break;
            case "/AjouterCredits" :
                RequestDispatcher rd8 = request.getRequestDispatcher("/WEB-INF/AjouterCredits.jsp");
                rd8.forward(request, response);
                break;
            case "/Suppression" :
                RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/Suppression.jsp");
                rd2.forward(request, response);
                break;
            case "/ConfirmationSuppression" :
                try {
                    usr.removeUser(user);
                } catch (BLLException e) {
                    e.printStackTrace();
                }
                session.removeAttribute("utilisateurConnecte");
                RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/index.jsp");
                rd3.forward(request, response);
                break;
        }
    }
}
