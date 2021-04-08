package dal;

import bo.Categorie;
import bo.Retrait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetraitDAOImpl implements RetraitDAO {

    private static final String sqlInsert = "INSERT INTO RETRAITS(no_article,rue,code_postale,ville) VALUES(?,?,?,?)";
    private static final String sqlSelectAll = "SELECT no_article,rue,code_postale,ville FROM RETRAITS";
    private static final String sqlDelete = "DELETE from RETRAITS where no_article=?";
    private static final String sqlUpdate = "UPDATE RETRAITS set rue=?,code_postale=?,ville=? where no_article=?";
    private static final String sqlSelectById = "SELECT rue,code_postale,ville from RETRAITS where no_article = ?";


    @Override
    public Retrait insert(Retrait retrait) throws DALException {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, retrait.getNoArticle());
            stmt.setString(2, retrait.getRue());
            stmt.setString(3, retrait.getCp());
            stmt.setString(4, retrait.getVille());

            int nbRows = stmt.executeUpdate();
            if (nbRows == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    retrait.setNoArticle(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("Le retrait" + retrait + " n'a pas été inséré");
        }
        return retrait;
    }

    @Override
    public List<Retrait> selectAll() throws DALException {
        List<Retrait> lesRetraits = new ArrayList<Retrait>();
        Retrait retrait = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"),
                    rs.getString("code_postale"), rs.getString("ville"));
                lesRetraits.add(retrait);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de récuperer la liste des retraits");
        }
        return lesRetraits;
    }

    @Override
    public void delete(Integer id) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("Le retrait avec l'id " + id + " n'a pas été effacé ..");
        }
    }

    @Override
    public void update(Retrait retrait) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);
            stmt.setString(1, retrait.getRue());
            stmt.setString(2, retrait.getCp());
            stmt.setString(3, retrait.getVille());
            stmt.setInt(4,retrait.getNoArticle());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'un retrait");
        }
    }

    @Override
    public Retrait selectById(Integer id) throws DALException {
        Retrait leRetrait = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                leRetrait = new Retrait(id, rs.getString("rue"), rs.getString("code_postale"), rs.getString("ville"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("probleme dans la sélection d'un retrait par l'id");
        }
        System.out.println(leRetrait);
        return leRetrait;
    }
}
