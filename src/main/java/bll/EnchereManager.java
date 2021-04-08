package bll;

import bo.Enchere;
import dal.DALException;

import java.util.List;

public interface EnchereManager {
    public Enchere addEnchere(Enchere enchere) throws BLLException, DALException;

    public List<Enchere> selectAll() throws BLLException;

    public Enchere SelectById (int id) throws BLLException, DALException;

    public void deleteEnchere( int id) throws BLLException;
}
