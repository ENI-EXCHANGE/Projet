package bll;

import dal.DALException;

public class BLLFactory {

    public static ArticleManager getArticleBLL() throws BLLException, DALException { return new ArticleManagerImpl();}

    public static UtilisateurManager getUtilisateurBLL(){ return new UtilisateurManagerImpl();}

    public static RetraitManager getRetraitManager() throws BLLException, DALException { return new RetraitManagerImpl();}

    public static CategorieManager getCategorieManager() throws BLLException, DALException { return new CategorieManagerImpl();}

    public static EnchereManager getEnchereManager() throws BLLException, DALException { return new EnchereManagerImpl();}
}

