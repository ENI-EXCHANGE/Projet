package dal;

import bo.Article;

import java.util.List;

public interface ArticleDAO {

    static void insert(Article nouvelArticle) { }

    static List<Article> selectAll() throws Exception { return null; }

    static Article selectById(int id) throws Exception { return null; }

    static void delete(int id) throws Exception { }
}
