package bll;

import bo.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManagerImpl  implements UtilisateurManager {

    private UtilisateurDAO =  UtilisateurDAOFact.getUtilisateurDAO();
    /*List<Utilisateur> users = new ArrayList<>();*/

    @Override
    public List<Utilisateur> getListUsers() throws BLLException {
        /*List<Utilisateur> users = null;
        try{
            users = dao.selectAll();

        }catch (DALException e) {
            throw new BLLException("Erreur récupération des utilisateurs");
        }*/
        return users;
    }

    /*
    * TODO : mettre en place un try catch qui verifie si l'utilisateur existe déjà
    * */
    @Override
    public void addUser(Utilisateur user) throws BLLException {
        users.add(user);
    }

    @Override
    public void updateUser(Utilisateur user) throws BLLException {

    }

    @Override
    public void removeUser(Utilisateur user) throws BLLException {

    }
}
