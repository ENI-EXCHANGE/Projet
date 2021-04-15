package bll;

import bo.Article;
import dal.DALException;
import java.util.List;

public interface ArticleManager {

    public void ajouterArticle(Article nouvelArticle) throws Exception;

    public List<Article> selectAll() throws Exception ;

    public Article selectById(int id) throws Exception ;

    public List<Article> selectByName(String name) throws Exception;

    public List<Article> selectByCategorie(int noCategorie) throws Exception;

    public List<Article> selectByNameCategorie(String name,int noCategorie) throws Exception;

    public void supprimerArticle(int id) throws Exception ;

    public  List<Article> selectByUtilisateur(int id) throws Exception;

    void modifierArticle (Article article) throws Exception;


    List<Article> selectByUtilisateurAtt(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurCours(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurTermine(Integer noUtilisateur) throws Exception;
}
