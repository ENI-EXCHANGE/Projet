package bll;

import bo.Categorie;
import bo.Utilisateur;

import java.util.List;

public interface CategorieManager {

    public List<Categorie> selectAll() throws BLLException;

    public Categorie insert(String libelle) throws BLLException;

    public void delete(int id) throws BLLException;

    public Categorie selectById(int id) throws BLLException;

    public void update(Categorie laCat) throws BLLException;

    Categorie selectByLibelle(String categorie) throws BLLException;
}
