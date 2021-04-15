package bll;

import bo.Article;
import dal.DALException;
import java.util.List;

public interface ArticleManager {

    void ajouterArticle(Article nouvelArticle) throws Exception;

    Article modifierArticle(Article art) throws  Exception;

    List<Article> selectAll() throws Exception ;

    public List<Article> selectAllDate() throws Exception ;

    public Article selectById(int id) throws Exception ;


    List<Article> selectByName(String name) throws Exception;

    List<Article> selectByCategorie(int noCategorie) throws Exception;

    List<Article> selectByNameCategorie(String name,int noCategorie) throws Exception;

    void supprimerArticle(int id) throws Exception ;

    List<Article> selectByUtilisateur(int id) throws Exception;

 //   void modifierArticle (Article article) throws Exception;


    List<Article> selectByUtilisateurAtt(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurCours(Integer noUtilisateur) throws Exception;

    List<Article> selectByUtilisateurTermine(Integer noUtilisateur) throws Exception;

    public List<Article> selectByCategorieWithListe(Integer noCategorie,List<Article> list) throws Exception;

    List<Article> selectByUtilisateurTermineWhithListe(Integer noUtilisateur, List<Article> list) throws Exception;

    public List<Article> selectByMesVentes(Integer noUtilisateur,List<Article> list) throws Exception;
    public List<Article> selectByUtilisateurCoursWhithListe(Integer noUtilisateur, List<Article> list) throws Exception;

    public List<Article> selectByUtilisateurWithList(Integer noUtilisateur, List<Article> list) throws Exception;

    public List<Article> selectByCours(List<Article> list) throws Exception;

    public List<Article> concat(List<Article> list1,List<Article> list2,List<Article> list3) throws Exception;

    public List<Article> selectByUtilisateurAttWithList(Integer noUtilisateur, List<Article> list) throws Exception;

    public List<Article> doublon(List<Article> list) throws Exception;
}
