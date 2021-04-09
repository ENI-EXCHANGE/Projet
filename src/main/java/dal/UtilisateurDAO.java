package dal;

import bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    public void insert(Utilisateur user)throws DALException;

    public List<Utilisateur> selectAll() throws DALException;

    public void delete(Utilisateur user) throws DALException;

    public void update(Utilisateur user) throws DALException;

    public Utilisateur selectById(int pseudo) throws DALException;

    public Utilisateur selectByPseudo(String pseudo) throws DALException;

}