
package bll;

import bo.Categorie;
import bo.Retrait;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.RetraitDAO;

import java.util.List;

public class RetraitManagerImpl implements RetraitManager{

    private RetraitDAO retraitDao;

    public RetraitManagerImpl() throws BLLException, DALException {

        retraitDao = DAOFactory.getRetraitDAO();
    }

    public List<Retrait> selectAll() throws Exception {

        try {
            return retraitDao.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectAll retrait dans BLL");
        }
    }

    public Retrait selectById(int id) throws Exception {

        try {
            return retraitDao.selectById(id);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectById retrait dans BLL");
        }
    }

    public void delete(int id) throws BLLException {

        try {
            retraitDao.delete(id);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du supprimerCategorie retrait dans BLL");
        }

    }

    public Retrait insert(Retrait retrait) throws BLLException {

        try {
            return retraitDao.insert(retrait);

        } catch (DALException e) {
            throw new BLLException("Erreur lors du ajouterNouveauRetrait dans BLL");
        }
    }

    public void update(Retrait retrait) throws BLLException {
        try {
            retraitDao.update(retrait);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la modif d'un retrait dans BLL");
        }
    }
}

