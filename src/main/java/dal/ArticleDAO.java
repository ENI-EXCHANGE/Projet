package dal;

import bo.Article;

import java.util.List;

public interface ArticleDAO {

    public Article insert(Article nouvelArticle) throws DALException;

    public List<Article> selectAll() throws DALException ;

    public List<Article> selectByName(String name) throws DALException ;

    public List<Article> selectByCategorie(int noCategorie) throws DALException ;

    public List<Article> selectByNameCategorie(String name,int noCategorie) throws DALException ;

    public Article selectById(int id) throws DALException ;

    public List<Article> selectByUtilisateur(int id) throws DALException ;

    public void delete(int id) throws DALException ;

    void update(Article art) throws  DALException;
}
