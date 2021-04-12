package dal;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{


    private static final String sqlInsert ="INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
    private static final String sqlSelectAll ="SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
    private static final String sqlSelectById ="SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article=?";
    private static final String sqlDelete = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?" ;
    private static final String sqlUpdate = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,no_utilisateur=?, no_categorie=? WHERE no_article=?";
    private UtilisateurManager usr = new UtilisateurManagerImpl();
    private CategorieManager cat = new CategorieManagerImpl();

    @Override
    public Article insert(Article nouvelArticle) throws DALException {

        try(Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pStmtArticle = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            pStmtArticle.setString(1, nouvelArticle.getNomArticle());
            pStmtArticle.setString(2, nouvelArticle.getDescription());
            pStmtArticle.setDate(3, nouvelArticle.getDateDebutEncheres());
            pStmtArticle.setDate(4, nouvelArticle.getDateFinEncheres());
            pStmtArticle.setInt(5, nouvelArticle.getPrixInitial());
            pStmtArticle.setInt(6, nouvelArticle.getPrixVente());
            pStmtArticle.setInt(7, nouvelArticle.getUtilisateur().getNoUtilisateur());
            pStmtArticle.setInt(8, nouvelArticle.getCategorie().getNoCategorie());

            pStmtArticle.executeUpdate();

            ResultSet rsArticle = pStmtArticle.getGeneratedKeys();
            if(rsArticle.next()) {
                nouvelArticle.setNoArticle(rsArticle.getInt(1));
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
                Utilisateur user = usr.selectById(rs.getInt("no_utilisateur"));
                Categorie cate = cat.selectById(rs.getInt("no_categorie"));
                art = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), user,cate);
                liste.add(art);
            }
        } catch (SQLException | BLLException e) {
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
            while (rs.next()){
                Utilisateur user = usr.selectById(rs.getInt("no_utilisateur"));
                Categorie cate = cat.selectById(rs.getInt("no_categorie"));
                art = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), user,cate);
            }

        } catch (SQLException | BLLException e) {
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
            stmt.setString(1, art.getNomArticle());
            stmt.setString(2, art.getDescription());
            stmt.setDate(3, art.getDateDebutEncheres());
            stmt.setDate(4, art.getDateFinEncheres());
            stmt.setInt(5, art.getPrixInitial());
            stmt.setInt(6, art.getPrixVente());
            stmt.setInt(7, art.getUtilisateur().getNoUtilisateur());
            stmt.setInt(8, art.getCategorie().getNoCategorie());
            stmt.setInt(9, art.getNoArticle());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'un article");
        }
    }
}
