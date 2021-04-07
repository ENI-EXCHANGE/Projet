package bll;

import bo.Article;
import bo.Utilisateur;
import dal.ArticleDAO;
import dal.DALException;

import java.sql.Date;
import java.util.List;

public interface ArticleManager {

    Article ajouterArticle(Article nouvelArticle) throws BLLException, DALException;

    public List<Article> selectAll() throws Exception ;

    public Article selectById(int id) throws Exception ;

    public void supprimerArticle(int id) throws Exception ;

}
