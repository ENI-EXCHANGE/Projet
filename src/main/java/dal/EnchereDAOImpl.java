package dal;

import bll.*;
import bo.Article;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOImpl implements EnchereDAO {

    private static final String sqlInsert = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?)";
    private static final String sqlSelectAll = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
    private static final String sqlDelete = "DELETE from ENCHERES where no_utilisateur=? AND no_article=?";
    private static final String sqlUpdate = "UPDATE ENCHERES set date_enchere=?,montant_enchere=? where no_utilisateur=? AND no_article=?";
    private static final String sqlSelectById = "SELECT date_enchere,montant_enchere from ENCHERES where no_utilisateur=? AND no_article=?";

    private UtilisateurManager usr = new UtilisateurManagerImpl();
    private ArticleManager art = new ArticleManagerImpl();

    @Override
    public Enchere insert(Enchere enchere) throws DALException {

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
            stmt.setInt(2, enchere.getArticle().getNoArticle());
            stmt.setTimestamp(3, enchere.getDateEnchere());
            stmt.setInt(4, enchere.getMontantEnchere());

            int nbRows = stmt.executeUpdate();
            if (nbRows == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    enchere.getUtilisateur().setNoUtilisateur(rs.getInt(1));
                    enchere.getArticle().setNoArticle(rs.getInt(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("L'enchere" + enchere + " n'a pas été inséré");
        }
        return enchere;
    }

    @Override
    public List<Enchere> selectAll() throws Exception {

        List<Enchere> lesEncheres = new ArrayList<Enchere>();
        Enchere enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur user = usr.selectById(rs.getInt("no_utilisateur"));
                Article article = art.selectById(rs.getInt("no_article"));
                //    Timestamp dateTime = rs.getObject("date_enchere",LocalDateTime.class);
                // si besoin LocalDateTime
                enchere = new Enchere(user,
                        article,
                        rs.getTimestamp("date_enchere"), rs.getInt("montant_enchere"));
                lesEncheres.add(enchere);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de récuperer la liste des encheres");
        }
        return lesEncheres;
    }

    @Override
    public void delete(int idUser, int idArticle) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, idUser);
            stmt.setInt(2, idArticle);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("L'enchere avec le num user " + idUser + " et le num article "+ idArticle+" n'a pas été effacé ..");
        }
    }

    @Override
    public void update(Enchere enchere) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);

            stmt.setTimestamp(1, enchere.getDateEnchere());
            stmt.setInt(2, enchere.getMontantEnchere());
            stmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
            stmt.setInt(4, enchere.getArticle().getNoArticle());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'une enchere");
        }
    }

    @Override
    public Enchere selectById(int idUser, int idArticle) throws Exception {

        Enchere enchere = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);
            stmt.setInt(1, idUser);
            stmt.setInt(2, idArticle);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur idUser1 = usr.selectById(idUser);
                Article idArticle1 = art.selectById(idArticle);
                enchere = new Enchere(idUser1,idArticle1, rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        }
        return enchere;
    }
}