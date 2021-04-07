package dal;

import bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String DELETE = "DELETE FROM utilisateurs WHERE no_utilisateurs=? ";
    private static final String INSERT = "INSERT INTO utilisateurs(no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECBYID = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE no_utilisateurs = ? ";
    private static final String SELECTALL = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs";

    public UtilisateurDAOImpl(){

    }

    @Override
    public void insert(Utilisateur user) throws DALException {
        try ( Connection cnx = ConnectionProvider.getConnection();){

                PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                pStmt.setInt(1, user.getNoUtilisateur());
                pStmt.setString(2, user.getPseudo());
                pStmt.setString(3, user.getNom());
                pStmt.setString(4, user.getPrenom());
                pStmt.setString(5, user.getEmail());
                pStmt.setString(6, user.getTelephone());
                pStmt.setString(7, user.getRue());
                pStmt.setInt(8, user.getCodePostal());
                pStmt.setString(9, user.getVille());
                pStmt.setString(10, user.getMotDePasse());
                pStmt.setInt(11, user.getCredit());
                pStmt.setBoolean(12, user.isAdministrateur());


                int nbRows = pStmt.executeUpdate();
                if (nbRows == 1) {
                    ResultSet rs = pStmt.getGeneratedKeys();
                    if (rs.next()) {
                        user.setNoUtilisateur(rs.getInt(1));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DALException("l'utilisateur " + user + " n'a pas été inséré ..");

            }

    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        ArrayList lesUsers = new ArrayList();
        Utilisateur user = new Utilisateur();
        try ( Connection cnx = ConnectionProvider.getConnection();){

            PreparedStatement pStmt = cnx.prepareStatement(SELECTALL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pStmt.executeQuery();
            lesUsers.add(user);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de récupérer la liste des utilisateurs");
        }

        return lesUsers;
    }

    @Override
    public void delete(Utilisateur user) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, user.getNoUtilisateur());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("l'utilisateur " + user + " n'a pas été supprimé ..");
        }

    }

    @Override
    public void update(Utilisateur user) throws DALException {

    }

    @Override
    public Utilisateur selectById(Integer no_utilisateur) throws DALException {
        return null;
    }
}
