package bll;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;
import dal.EnchereDAO;
import dal.UtilisateurDAO;

import java.util.List;

/**
 * méthodes ajouter / modifier / suprimer et getlist des utilisateurs
 * avec gestion des exception
 * @author fannie
 *
 */

public class UtilisateurManagerImpl  implements UtilisateurManager {

    private UtilisateurDAO  dao =  DAOFactory.getUtilisateurDAO();

    public UtilisateurManagerImpl() throws BLLException, DALException {
    }

    @Override
    public List<Utilisateur> getListUsers() throws BLLException {
        List<Utilisateur> users =null;
        try{
            users = dao.selectAll();

        }catch ( DALException e) {
            throw new BLLException("Erreur récupération des utilisateurs");
        }
        return users;
    }
    /**
     * Valide l'adresse email saisie.
     */
    public void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            if ( dao.emailExist(email) == true ){
                throw new Exception( "cet email existe déjà." );
            }
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /**
     * Valide si le pseudo saisi existe déjà.
     */
    public void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo != null && !pseudo.matches( "[a-zA-Z_0-9]" ) ) {
            if (dao.pseudoExist(pseudo) == true) {
                throw new Exception("ce pseudo existe déjà.");
            }
        }
    }
    /**
     * Valide le mot de passe saisi.
     */
    public void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 6 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 6 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    @Override
    public void addUser(Utilisateur user) throws BLLException {
        try {

            validationPseudo(user.getPseudo());
            validationEmail(user.getEmail());
            validationMotDePasse(user.getMotDePasse());
            dao.insert(user);

        } catch (Exception e) {
            throw new BLLException(e.getMessage());
        }
    }

    @Override
    public Utilisateur selectById(int id) throws BLLException, DALException {
        return dao.selectById(id);

    }

    @Override
    public Utilisateur selectByEmail(String email) throws DALException {
        return dao.selectByEmail(email);
    }
    @Override
    public Utilisateur selectByPseudo(String pseudo) throws DALException {
        return dao.selectByPseudo(pseudo);
    }

    @Override
    public void updateUser(Utilisateur user) throws BLLException {
        try {
            dao.update(user);

        } catch (DALException e) {
            throw new BLLException("Echec update Utilisateur : "+user);
        }
    }

    @Override
    public void removeUser(Utilisateur user) throws BLLException {
        try {
            dao.delete(user);
        } catch (DALException e) {
            throw new BLLException("Echec de la suppression de l'utilisateur - ");
        }
    }

    @Override
    public Utilisateur authentification(String pseudo, String mdp)  {
        return dao.authentification(pseudo, mdp);
    }

    @Override
    public void ajouterCredit(int id, int point) throws Exception {
        dao.ajouterCredit(id, point);
    }
    @Override
    public void debiterCredit(int id, int point) throws Exception {
        dao.debiterCredit(id, point);
    }
}
