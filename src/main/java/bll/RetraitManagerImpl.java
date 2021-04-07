package bll;

import bo.Retrait;
import java.util.List;

public class RetraitManagerImpl implements RetraitManager{

    private RetraitDao retraitDao;

    public List<Retrait> selectAll() throws BLLException {
        return retraitDao.selectAll();
    }

    public Retrait selectById(int id) throws BLLException {
        return retraitDao.selectById(id);
    }

    public void supprimerCategorie(int id) throws BLLException {
        retraitDao.delete(id);
    }

    public Retrait ajouterNouveauRetrait(int noArticle, String rue, int cp, String ville) throws BLLException {

        Retrait nouveauRetrait = new Retrait(noArticle, rue,cp, ville);
        retraitDao.insert(nouveauRetrait);
        return nouveauRetrait;
    }
}
