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

    public ArticleManagerImpl() throws BLLException, DALException {
        dao = DAOFactory.getArticleDAO();
    }

    @Override
    public void ajouterArticle(Article nouvelArticle) throws DALException {
        dao.insert(nouvelArticle);
    }

    @Override
    public List<Article> selectAll() throws Exception {
        return articleDAO.selectAll();
    }

    @Override
    public Article selectById(int id) throws Exception {
        return  articleDAO.selectById(id);
    }

    @Override
    public List<Article> selectByName(String name) throws Exception {
        return articleDAO.selectByName(name);
    }

    @Override
    public List<Article> selectByCategorie(int noCategorie) throws Exception {
        return articleDAO.selectByCategorie(noCategorie);
    }

    @Override
    public List<Article> selectByNameCategorie(String name,int noCategorie) throws Exception {
        return articleDAO.selectByNameCategorie(name, noCategorie);
    }



    @Override
    public void supprimerArticle(int id) throws Exception {
        articleDAO.delete(id);
    }

    @Override
    public List<Article> selectByUtilisateur(int id) throws Exception {
        return articleDAO.selectByUtilisateur(id);
    }
}
