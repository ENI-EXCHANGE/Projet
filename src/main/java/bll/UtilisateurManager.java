package bll;

import bo.Utilisateur;

import java.util.List;

/**
 * Manager des Utilisateurs
 * @author fannie
 *
 */
public interface UtilisateurManager {
    /**
     * permet d'ajouter/modifier/supprimer un user
     * permet de recuperer tous les users
     * OPTION (permet de vérifier si l'user répond aux contraintes suivantes :
     *              CT01: Les attributs des utilisateurs sont obligatoires.
     *              CT02: vérifier le format de l'adresse mail et du num de tel)
     *
     * @throws BLLException
     */


    public List<Utilisateur> getListUsers() throws BLLException;

    public void addUser(Utilisateur user) throws BLLException;

    public void updateUser(Utilisateur user) throws BLLException;

    public void removeUser(Utilisateur user) throws BLLException;

    /*public void validerUser(Utilisateur user) throws BLLException;*/





}
