package dal;

import bo.Article;

import java.util.List;

public interface ArticleDAO {

    public Article insert(Article nouvelArticle) throws DALException;

    public List<Article> selectAll() throws DALException ;

    public Article selectById(int id) throws DALException ;

    public List<Article> selectByUtilisateur(int id) throws DALException ;

    public void delete(int id) throws DALException ;

    void update(Article art) throws  DALException;
}
