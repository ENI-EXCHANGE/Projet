package bll;

import bo.Article;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;

import java.util.List;

public class ArticleManagerImpl implements ArticleManager{

    private ArticleManager articleManager ;
    //public ArticleManagerImpl() { articleManager = BLLFactory.getArticleBLL(); }

    private ArticleDAO articleDAO =  DAOFactory.getArticleDAO();

    private ArticleDAO dao;

    public ArticleManagerImpl() {
        dao = DAOFactory.getArticleDAO();
    }

    @Override
    public void ajouterArticle(Article nouvelArticle) throws DALException {
        dao.insert(nouvelArticle);
    }

    @Override
    public List<Article> selectAll() throws Exception {
        List<Article> articles = articleDAO.selectAll();

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
