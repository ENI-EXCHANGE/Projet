package dal;

import bo.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {


    private static final String sqlInsert = "INSERT INTO Categorie(no_categorie,libelle) VALUES(?,?)";
    private static final String sqlSelectAll = "SELECT no_categorie, libelle FROM Categorie";
    private static final String sqlDelete = "DELETE from Categorie where noCategorie=?";
    private static final String sqlUpdate = "UPDATE Categorie set libelle=? where no_categorie=?";
    private static final String sqlSelectById = "SELECT libelle from Categorie where no_categorie = ?";

    @Override
    public Categorie insert(Categorie categorie) throws DALException {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(2, categorie.getLibelle());

            int nbRows = stmt.executeUpdate();
            if (nbRows == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    categorie.setNoCategorie(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("La categorie" + categorie + " n'a pas été inséré");
        }
        return categorie;
    }

    @Override
    public List<Categorie> selectAll() throws DALException {

        List<Categorie> lesCategories = new ArrayList<Categorie>();
        Categorie cat = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

                lesCategories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de récuperer la liste des articles");
        }
        return lesCategories;
    }

    @Override
    public void delete(Integer id) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("l'article avec l'id " + id + " n'a pas été effacé ..");
        }
    }

    @Override
    public void update(Categorie categorie) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);
            stmt.setString(1, categorie.getLibelle());
            stmt.setInt(2, categorie.getNoCategorie());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'un article");
        }
    }

    @Override
    public Categorie selectById(Integer id) throws DALException {
        Categorie laCategorie = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            laCategorie = new Categorie(id,rs.getString("libelle"));


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'une catégorie par l'id");
        }
        return laCategorie;
    }
}
