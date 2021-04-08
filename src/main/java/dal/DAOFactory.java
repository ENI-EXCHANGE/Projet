package dal;

public class DAOFactory {

    public static ArticleDAO getArticleDAO(){ return new ArticleDAOImpl();}

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl();
    }

    public static RetraitDAO getRetraitDAO() { return  new RetraitDAOImpl() ; }

    public static  CategorieDAO getCategorieDAO() { return new CategorieDAOImpl() ; }

    public static  EnchereDAO getEnchereDAO() { return new EnchereDAOImpl() ; }

}
