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

    private static final String sqlInsert = "INSERT INTO ENCHERES(no_utilisateur, no_article, montant_enchere) VALUES(?,?,?)";
    private static final String sqlSelectAll = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
    private static final String sqlDelete = "DELETE from ENCHERES where no_utilisateur=? AND no_article=?";
    private static final String sqlUpdate = "UPDATE ENCHERES set montant_enchere=? where no_utilisateur=? AND no_article=?";
    private static final String sqlSelectById = "SELECT date_enchere,montant_enchere from ENCHERES where no_utilisateur=? AND no_article=?";
    private static final String sqlSelectByUtilisateur = "SELECT * FROM ENCHERES INNER JOIN UTILISATEURS ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateurs INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article WHERE no_utilisateurs=?";
    private static final String sqlSelectByArticle = "SELECT * FROM ENCHERES WHERE no_article=? order by date_enchere DESC";

    private UtilisateurManager usr = new UtilisateurManagerImpl();
    private ArticleManager art = new ArticleManagerImpl();

    public EnchereDAOImpl() throws BLLException, DALException {
    }

    @Override
    public Enchere insert(Enchere enchere) throws DALException {

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
            stmt.setInt(2, enchere.getArticle().getNoArticle());
            stmt.setInt(3, enchere.getMontantEnchere());

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
                        rs.getDate("date_enchere"), rs.getInt("montant_enchere"));
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

            stmt.setInt(1, enchere.getMontantEnchere());
            stmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
            stmt.setInt(3, enchere.getArticle().getNoArticle());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'une enchere");
        }
    }

    @Override
    public List<Enchere> selectByUtilisateur(int id) throws DALException {
        List<Enchere> lesEncheres = new ArrayList<Enchere>();
        Enchere enchere = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectByUtilisateur);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur idUser1 = usr.selectById(id);
                Article idArticle1 = art.selectById(rs.getInt("no_article"));
                enchere = new Enchere(idUser1,idArticle1, rs.getDate("date_enchere"),rs.getInt("montant_enchere"));
                lesEncheres.add(enchere);
            }

        } catch (SQLException | BLLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesEncheres;
    }

    @Override
    public List<Enchere> selectByArticle(int id) throws Exception {
        List<Enchere> lesEncheres = new ArrayList<Enchere>();
        Enchere enchere = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectByArticle);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur idUser1 = usr.selectById(rs.getInt("no_utilisateur"));
                Article idArticle1 = art.selectById(rs.getInt("no_article"));
                enchere = new Enchere(idUser1,idArticle1, rs.getDate("date_enchere"),rs.getInt("montant_enchere"));
                lesEncheres.add(enchere);
            }
        } catch (SQLException | BLLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesEncheres;
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
                enchere = new Enchere(idUser1,idArticle1, rs.getDate("date_enchere"),rs.getInt("montant_enchere"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        }
        return enchere;
    }

}