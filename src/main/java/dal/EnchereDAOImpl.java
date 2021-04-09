package dal;

import bo.Categorie;
import bo.Enchere;

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

    @Override
    public Enchere insert(Enchere enchere) throws DALException {

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enchere.getNo_utilisateur());
            stmt.setInt(2, enchere.getNo_article());
            stmt.setTimestamp(3, enchere.getDate_enchere());
            stmt.setInt(4, enchere.getMontant_enchere());

            int nbRows = stmt.executeUpdate();
            if (nbRows == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    enchere.setNo_utilisateur(rs.getInt(1));
                    enchere.setNo_article(rs.getInt(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("L'enchere" + enchere + " n'a pas été inséré");
        }
        return enchere;
    }

    @Override
    public List<Enchere> selectAll() throws DALException {

        List<Enchere> lesEncheres = new ArrayList<Enchere>();
        Enchere enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //    Timestamp dateTime = rs.getObject("date_enchere",LocalDateTime.class);
                // si besoin LocalDateTime
                enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
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
    public void delete(Integer noUtilisateurs, Integer noArticle) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, noUtilisateurs);
            stmt.setInt(2, noArticle);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("L'enchere avec le num user " + noUtilisateurs + " et le num article "+ noArticle+" n'a pas été effacé ..");
        }
    }

    @Override
    public void update(Enchere enchere) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);

            stmt.setTimestamp(1, enchere.getDate_enchere());
            stmt.setInt(2, enchere.getMontant_enchere());
            stmt.setInt(3, enchere.getNo_utilisateur());
            stmt.setInt(4, enchere.getNo_article());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'une enchere");
        }
    }

    @Override
    public Enchere selectById(Integer noUtilisateurs, Integer noArticle) throws DALException {

        Enchere enchere = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);
            stmt.setInt(1, noUtilisateurs);
            stmt.setInt(2, noArticle);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                enchere = new Enchere(noUtilisateurs,noArticle, rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        }
        return enchere;
    }
}