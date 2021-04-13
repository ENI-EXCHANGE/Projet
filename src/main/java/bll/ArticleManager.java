package bll;

import bo.Article;
import dal.DALException;
import java.util.List;

public interface ArticleManager {

    public void ajouterArticle(Article nouvelArticle) throws Exception;

    public List<Article> selectAll() throws Exception ;

    public Article selectById(int id) throws Exception ;

    public void supprimerArticle(int id) throws Exception ;

    public  List<Article> selectByUtilisateur(int id) throws Exception;
}
