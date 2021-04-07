package dal;

import bo.Categorie;

import java.sql.*;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {


    private static final String sqlInsert = "INSERT INTO Categorie(noCategorie,libelle) VALUES(?,?)";
    private static final String sqlSelectAll = "SELECT noCategorie, libelle FROM Categorie";
    private static final String sqlDelete = "DELETE from Categorie where noCategorie=?";
    private static final String sqlUpdate = "UPDATE Categorie set libelle=? where noCategorie=?";
    private static final String sqlSelectById = "SELECT libelle from Categorie where noCategorie = ?";

    @Override
    public Categorie insert(Categorie categorie) throws DALException {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, categorie.getNoCategorie());
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
        return null;
    }

    @Override
    public void delete(Integer id) throws DALException {

    }

    @Override
    public void update(Categorie user) throws DALException {

    }

    @Override
    public Categorie selectById(Integer id) throws DALException {
        return null;
    }
}
