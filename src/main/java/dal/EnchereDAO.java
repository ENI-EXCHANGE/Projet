package dal;

import bo.Article;
import bo.Enchere;

import java.util.List;

public interface EnchereDAO {

    public void insert(Enchere enchere) throws DALException;

    public List<Enchere> selectAll() throws DALException ;

    public Enchere selectById(int id) throws DALException ;

    public void delete(int id) throws DALException ;
}
