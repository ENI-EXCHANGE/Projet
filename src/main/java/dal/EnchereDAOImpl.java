package dal;

import bo.Enchere;

import java.sql.*;
import java.util.List;

public class EnchereDAOImpl implements EnchereDAO{

    private static final String sqlInsert ="INSERT INTO encheres(no_utilisateur,no_article,date_enchere, montant_enchere) VALUES(?,?,?,?)";
    private static final String sqlSelectAll ="SELECT no_utilisateur,no_article,date_enchere, montant_enchere FROM encheres";
    private static final String sqlSelectById ="SELECT no_utilisateur,no_article,date_enchere, montant_enchere  FROM encheres WHERE no_article=?";
    private static final String sqlDelete = "DELETE FROM encheres WHERE no_article=?" ;

    @Override
    public void insert(Enchere enchere) throws DALException {
        try(Connection cnx = ConnectionProvider.getConnection()) {

            PreparedStatement pStmtEnchere = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            pStmtEnchere.setInt(1, enchere.getNo_utilisateur());
            pStmtEnchere.setInt(2, enchere.getNo_article());
            pStmtEnchere.setDate(3, (Date) enchere.getDate_enchere());
            pStmtEnchere.setInt(4, enchere.getMontant_enchere());


            pStmtEnchere.executeUpdate();

            ResultSet rsEnchere = pStmtEnchere.getGeneratedKeys();
            if(rsEnchere.next()) {
                enchere.setNo_article(rsEnchere.getInt(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enchere> selectAll() throws DALException {
        return null;
    }

    @Override
    public Enchere selectById(int id) throws DALException {
        return null;
    }

    @Override
    public void delete(int id) throws DALException {

    }
}
