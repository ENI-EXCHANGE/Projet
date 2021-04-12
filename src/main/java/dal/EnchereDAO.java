package dal;

import bll.BLLException;
import bo.Article;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;

import java.util.List;

public interface EnchereDAO {

    public Enchere insert(Enchere enchere) throws DALException;

    public List<Enchere> selectAll() throws Exception;

    public Enchere selectById(int idUser, int idArticle) throws Exception;

    public void delete(int idUser, int idArticle) throws DALException ;

    public void update(Enchere enchere) throws DALException;
}
