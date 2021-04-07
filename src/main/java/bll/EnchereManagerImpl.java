package bll;

import bo.Enchere;
import dal.DALException;
import dal.DAOFactory;
import dal.EnchereDAO;

import java.util.List;

public class EnchereManagerImpl implements EnchereManager {

    private final EnchereDAO enchereDAO ;

    public EnchereManagerImpl(){ enchereDAO= DAOFactory.getEnchereDAO(); }

    @Override
    public Enchere addEnchere(Enchere enchere) throws BLLException, DALException {
        enchereDAO.insert(enchere);
        return enchere;
    }

    @Override
    public List<Enchere> selectAll() throws BLLException {
        List<Enchere> lesEncheres = null;
        try {
            lesEncheres = enchereDAO.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
        }

        return lesEncheres;
    }

    @Override
    public Enchere SelectById(int id) throws BLLException, DALException {
        return enchereDAO.selectById(id);

    }

    @Override
    public void deleteEnchere(int id) throws BLLException {
        try {
            enchereDAO.delete(id);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}
