package dal;

public class DAOFactory {

    public static ArticleDAO getArticleDAO(){ return new ArticleDAOJDBCImpl();}

}
