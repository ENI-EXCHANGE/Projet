
package bll;

import bo.Categorie;
import dal.ArticleDAO;
import dal.CategorieDAO;
import dal.DALException;
import dal.DAOFactory;

import java.util.List;

public class CategorieManagerImpl implements CategorieManager{

    private CategorieDAO categorieDao;

    public CategorieManagerImpl() {
        categorieDao = DAOFactory.getCategorieDAO();
    }

    public List<Categorie> selectAll() throws BLLException {

        try {
            return categorieDao.selectAll();
        } catch (DALException e) {
          throw new BLLException("Erreur lors du SelectAll categorie dans BLL");
        }
    }

    public Categorie selectById(int id) throws BLLException {
        try {
            return categorieDao.selectById(id);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectById categorie dans BLL");
        }
    }

    public void supprimerCategorie(int id) throws BLLException {
        try {
            categorieDao.delete(id);
        }
        catch (DALException e)
        {
            throw new BLLException("Erreur lors du supprimerCategorie dans BLL");
        }

    }

    public Categorie ajouterNouvelleCategorie(String libelle) throws BLLException {
        try {
            Categorie nouvelleCategorie = new Categorie(libelle);

            categorieDao.insert(nouvelleCategorie);
            return nouvelleCategorie;
        }
        catch (DALException e)
        {
            throw new BLLException("Erreur lors du ajouterNouvelleCategorie dans BLL");
        }


    }

    public void update(Categorie laCat) throws BLLException {
        try {
             categorieDao.update(laCat);
        }
        catch (DALException e)
        {
            throw new BLLException("Erreur lors de la modif de la categorie dans BLL");
        }

    }
}

