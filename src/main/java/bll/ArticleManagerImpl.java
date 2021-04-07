package bll;

import bo.Article;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;

import java.util.List;

public class ArticleManagerImpl implements ArticleManager{

    private final ArticleDAO articleDAO ;

    public ArticleManagerImpl() { articleDAO = DAOFactory.getArticleDAO(); }


    @Override
    public Article ajouterArticle(Article nouvelArticle) throws DALException {
        articleDAO.insert(nouvelArticle);

        return nouvelArticle;
    }

    @Override
    public List<Article> selectAll() throws Exception {
        List<Article> articles = null;

        articleDAO.selectAll();

        return articles;
    }

    @Override
    public Article selectById(int id) throws Exception {
        return  articleDAO.selectById(id);
    }

    @Override
    public void supprimerArticle(int id) throws Exception {
        articleDAO.delete(id);
    }
}
