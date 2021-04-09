package dal;

import bo.Article;
import bo.Categorie;
import bo.Enchere;

import java.util.List;

public interface EnchereDAO {

    public Enchere insert(Enchere enchere) throws DALException;

    public List<Enchere> selectAll() throws DALException ;

    public Enchere selectById(Integer noUtilisateurs, Integer noArticle) throws DALException ;

    public void delete(Integer noUtilisateurs, Integer noArticle) throws DALException ;

    public void update(Enchere enchere) throws DALException;
}
