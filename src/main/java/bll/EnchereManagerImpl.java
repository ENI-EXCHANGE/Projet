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
    public Enchere insert(Enchere enchere) throws BLLException {
        try {
            return enchereDAO.insert(enchere);

        } catch (DALException e) {
            throw new BLLException("Erreur lors du ajouter NouveauRetrait dans BLL");
        }
    }

    @Override
    public List<Enchere> selectAll() throws BLLException {
        List<Enchere> lesEncheres = null;
        try {
            lesEncheres = enchereDAO.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectAll enchere dans BLL");
        }
        return lesEncheres;
    }

    @Override
    public Enchere selectById(Integer noUtilisateurs, Integer noArticle) throws BLLException {

        try {
            return enchereDAO.selectById(noUtilisateurs,noArticle);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectById enchere dans BLL");
        }

    }

    @Override
    public void delete(Integer noUtilisateurs, Integer noArticle) throws BLLException {
        try {
            enchereDAO.delete(noUtilisateurs,noArticle);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors du delete retrait dans BLL");
        }
    }

    @Override
    public void update(Enchere enchere) throws BLLException {
        try {
            enchereDAO.update(enchere);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la modif d'un retrait dans BLL");
        }
    }
}
