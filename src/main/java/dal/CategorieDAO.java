package dal;

import bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    public Categorie insert(Categorie categorie)throws DALException;

    public List<Categorie> selectAll() throws DALException;

    public void delete(Integer id) throws DALException;

    public void update(Categorie user) throws DALException;

    public Categorie selectById(Integer id) throws DALException;
}
