package bll;

public class BLLFactory {

    public static ArticleManager getArticleBLL(){ return new ArticleManagerImpl();}

    public static UtilisateurManager getUtilisateurBLL(){ return new UtilisateurManagerImpl();}

    public static RetraitManager getRetraitManager(){ return new RetraitManagerImpl();}

    public static CategorieManager getCategorieManager(){ return new CategorieManagerImpl();}

    public static EnchereManager getEnchereManager(){ return new EnchereManagerImpl();}
}

