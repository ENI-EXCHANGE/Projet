package bll;

import bo.Categorie;

import java.util.List;

public class CategorieManagerImpl implements CategorieManager{

    private CategorieDao categorieDao;

    public List<Categorie> selectAll() throws BLLException {
        return categorieDao.selectAll();
    }

    public Categorie selectById(int id) throws BLLException {
        return categorieDao.selectById(id);
    }

    public void supprimerCategorie(int id) throws BLLException {
        categorieDao.delete(id);
    }

    public Categorie ajouterNouvelleCategorie(int noCategorie, String libelle) throws BLLException {

        Categorie nouvelleCategorie = new Categorie(noCategorie, libelle);
        categorieDao.insert(nouvelleCategorie);
        return nouvelleCategorie;
    }
}
