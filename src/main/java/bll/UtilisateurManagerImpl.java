package bll;

import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;
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

    /*
     * TODO : mettre en place un try catch qui verifie si l'utilisateur existe déjà
     * */
    @Override
    public void addUser(Utilisateur user) throws BLLException {
        try {
            dao.instert(user);

        } catch (DALException e) {
            throw new BLLException(" l'insertion de l'utilisateur à échoué !");
        }
    }

    @Override
    public void updateUser(Utilisateur user) throws BLLException {
        try {
            dao.update(user);

        } catch (DALException e) {
            throw new BLLException("Echec update Utilisateur :"+user);
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
}
