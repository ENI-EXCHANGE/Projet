package bll;

import bo.Article;
import dal.ArticleDAO;
import dal.DAOFactory;

import java.sql.Date;
import java.util.List;

public class ArticleManagerImpl implements ArticleManager{

    private ArticleDAO articleDAO ;

    public ArticleManagerImpl() { articleDAO = DAOFactory.getArticleDAO(); }

    public List<Article> selectAll() throws Exception {
        return ArticleDAO.selectAll();
    }

    public Article selectById(int id) throws Exception {
        return ArticleDAO.selectById(id);
    }

    public void supprimerArticle(int id) throws Exception{
        ArticleDAO.delete(id);
    }

    public Article ajouterArticle(String nom, String description, Date dateDebut, int prixInit, int noUtil, int noCategorie){
        Article nouvelArticle = new Article(nom, description, dateDebut, prixInit, noUtil, noCategorie);

        ArticleDAO.insert(nouvelArticle);

        return nouvelArticle;
    }
}
