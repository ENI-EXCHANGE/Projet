package bll;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;

import java.util.List;

public interface EnchereManager {

    public Enchere insert(Enchere enchere) throws BLLException;

    public List<Enchere> selectAll() throws Exception;

    public Enchere selectById (int idUser, int idArticle) throws Exception;

    public List<Enchere> selectByUtilisateur(int id) throws Exception;

    public void delete( int idUser, int idArticle) throws BLLException;

    public void update(Enchere enchere) throws BLLException;

    Enchere dernierUtilisateur(int art) throws Exception ;

    boolean gagne(Utilisateur uti, Article art) throws Exception ;
}
