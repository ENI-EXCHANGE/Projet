package TEST;

import bll.BLLException;
import bo.Categorie;
import bo.Utilisateur;
import dal.CategorieDAOImpl;
import dal.DALException;

import java.util.ArrayList;
import java.util.List;

public class TestCat {
    public static void main(String[] args) throws BLLException {

        CategorieDAOImpl a = new CategorieDAOImpl();

        try {
            List<Categorie> cats = a.selectAll();
            System.out.println(cats);
        }
        catch (DALException e)
        {
            System.out.println("probleme");
        }

    }
}
