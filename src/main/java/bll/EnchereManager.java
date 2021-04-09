package bll;

import bo.Enchere;
import dal.DALException;

import java.util.List;

public interface EnchereManager {

    public Enchere insert(Enchere enchere) throws BLLException;

    public List<Enchere> selectAll() throws BLLException;

    public Enchere selectById (Integer noUtilisateurs, Integer noArticle) throws BLLException;

    public void delete( Integer noUtilisateurs, Integer noArticle) throws BLLException;

    public void update(Enchere enchere) throws BLLException;
}
