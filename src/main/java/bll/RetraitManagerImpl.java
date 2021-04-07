package bll;

import bo.Retrait;
import dal.DALException;
import dal.RetraitDAO;

import java.util.List;

public class RetraitManagerImpl implements RetraitManager{

    private RetraitDAO retraitDao;

    public List<Retrait> selectAll() throws BLLException {

        try {
            return retraitDao.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectAll retrait dans BLL");
        }
    }

    public Retrait selectById(int id) throws BLLException {


        try {
            return retraitDao.selectById(id);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectById retrait dans BLL");
        }
    }

    public void supprimerRetrait(int id) throws BLLException {

        try {
            retraitDao.delete(id);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du supprimerCategorie retrait dans BLL");
        }

    }

    public Retrait ajouterNouveauRetrait(int noArticle, String rue, int cp, String ville) throws BLLException {

        try {
            Retrait nouveauRetrait = new Retrait(noArticle, rue,cp, ville);
            retraitDao.insert(nouveauRetrait);
            return nouveauRetrait;

        } catch (DALException e) {
            throw new BLLException("Erreur lors du ajouterNouveauRetrait dans BLL");
        }
    }
}
