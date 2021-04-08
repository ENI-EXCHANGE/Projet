package dal;

import bo.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{


    private static final String sqlInsert ="INSERT INTO articles_vendus(nom_article,description,dete_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?)";
    private static final String sqlSelectAll ="SELECT no_article,nom_article,description,dete_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM articles_vendus";
    private static final String sqlSelectById ="SELECT no_article,nom_article,description,dete_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM articles_vendus WHERE no_article=?";
    private static final String sqlDelete = "DELETE FROM articles_vendus WHERE no_article=?" ;


    @Override
    public void insert(Article nouvelArticle) throws DALException {

        try(Connection cnx = JdbcTools.getConnection()) {

            PreparedStatement pStmtArticle = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            pStmtArticle.setString(1, nouvelArticle.getNom_article());
            pStmtArticle.setString(2, nouvelArticle.getDescription());
            pStmtArticle.setDate(3, nouvelArticle.getDate_debut_encheres());
            pStmtArticle.setDate(4, nouvelArticle.getDate_fin_encheres());
            pStmtArticle.setInt(5, nouvelArticle.getPrix_initial());
            pStmtArticle.setInt(6, nouvelArticle.getPrix_vente());
            pStmtArticle.setInt(7, nouvelArticle.getNo_utilisateur());
            pStmtArticle.setInt(8, nouvelArticle.getNo_categorire());

            pStmtArticle.executeUpdate();

            ResultSet rsArticle = pStmtArticle.getGeneratedKeys();
            if(rsArticle.next()) {
                nouvelArticle.getNo_article(rsArticle.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Article> selectAll() throws DALException {
        return null;
    }

    @Override
    public Article selectById(int id) throws DALException {
        return null;
    }

    @Override
    public void delete(int id) throws DALException {

    }
}
