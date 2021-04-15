package bll;

import bo.Article;
import dal.DALException;
import java.util.List;

public interface ArticleManager {

    void ajouterArticle(Article nouvelArticle) throws Exception;

    Article modifierArticle(Article art) throws  Exception;

    List<Article> selectAll() throws Exception ;

    Article selectById(int id) throws Exception ;

    List<Article> selectByName(String name) throws Exception;

    List<Article> selectByCategorie(int noCategorie) throws Exception;

    List<Article> selectByNameCategorie(String name,int noCategorie) throws Exception;

    void supprimerArticle(int id) throws Exception ;

    List<Article> selectByUtilisateur(int id) throws Exception;

    List<Article> selectByUtilisateurAtt(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurCours(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurTermine(Integer noUtilisateur) throws Exception;
}
