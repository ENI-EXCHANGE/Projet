package dal;

public class DAOFactory {

    public static ArticleDAO getArticleDAO(){ return new ArticleDAOImpl();}

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl();
    }

}
