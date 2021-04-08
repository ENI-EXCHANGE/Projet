package dal;

import bo.Article;
import bo.Categorie;
import bo.Retrait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{


    private static final String sqlInsert ="INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";
    private static final String sqlSelectAll ="SELECT no_article,nom_article,description,dete_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM articles_vendus";
    private static final String sqlSelectById ="SELECT no_article,nom_article,description,dete_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM articles_vendus WHERE no_article=?";
    private static final String sqlDelete = "DELETE FROM articles_vendus WHERE no_article=?" ;
    private static final String sqlUpdate = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,no_utilisateur=?, no_categorie=? WHERE no_article=?";


    @Override
    public Article insert(Article nouvelArticle) throws DALException {

        try(Connection cnx = ConnectionProvider.getConnection()) {

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

        return nouvelArticle;

    }

    @Override
    public List<Article> selectAll() throws DALException {
        List<Article> liste = new ArrayList<Article>();
        Article art = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                art = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),rs.getInt("no_categorie"));
                liste.add(art);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de récuperer la liste des articles");
        }
        return liste;
    }

    @Override
    public Article selectById(int id) throws DALException {
        Article art = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            art = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),rs.getInt("no_categorie"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'un retrait par l'id");
        }
        return art;
    }

    @Override
    public void delete(int id) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("L'Article d'id :" + id + " n'a pas été effacé ..");
        }
    }

    @Override
    public void update(Article art) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);
            stmt.setString(1, art.getNom_article());
            stmt.setString(2, art.getDescription());
            stmt.setDate(3, art.getDate_debut_encheres());
            stmt.setDate(4, art.getDate_fin_encheres());
            stmt.setInt(5, art.getPrix_initial());
            stmt.setInt(6, art.getPrix_vente());
            stmt.setInt(7, art.getNo_utilisateur());
            stmt.setInt(8, art.getNo_categorire());
            stmt.setInt(9, art.getNo_article());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'un article");
        }
    }
}
