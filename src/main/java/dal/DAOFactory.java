package dal;

import bll.BLLException;

public class DAOFactory {

    public static ArticleDAO getArticleDAO() throws BLLException, DALException { return new ArticleDAOImpl();}

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl();
    }

    public static RetraitDAO getRetraitDAO() throws BLLException, DALException { return  new RetraitDAOImpl() ; }

    public static  CategorieDAO getCategorieDAO() { return new CategorieDAOImpl() ; }

    public static  EnchereDAO getEnchereDAO() throws BLLException, DALException { return new EnchereDAOImpl() ; }

}
