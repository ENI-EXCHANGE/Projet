package dal;

import bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateurs=? ";
    private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECBYID = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateurs = ? ";
    private static final String SELECTALL = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
    private static final String SELECBYPSEUDO = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ? ";
    private static final String CHECKUSER = "SELECT * FROM UTILISATEURS WHERE pseudo=? and mot_de_passe=?";
    private static final String UPDATE ="UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE pseudo=?";
    private static final String AJOUTERCREDIT ="UPDATE UTILISATEURS SET credit=? WHERE no_utilisateurs=?";
    private static final String DEBITER_CREDIT ="UPDATE UTILISATEURS SET credit=? WHERE no_utilisateurs=?";
    private static final String CHECK_USER_BY_LOGIN = "SELECT * FROM UTILISATEURS WHERE (pseudo=? and mot_de_passe=?) or (email=? and mot_de_passe=?) ";
    private static final String CHECK_PSEUDO_IS_UNIQUE = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
    private static final String CHECK_EMAIL_IS_UNIQUE = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
    private static final String SELECBYEMAIL = "SELECT no_utilisateurs, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email = ? ";
    private static final String INVALIDER ="UPDATE UTILISATEURS SET administrateur=2 WHERE no_utilisateurs=?";

    public UtilisateurDAOImpl(){

    }

    @Override
    public void insert(Utilisateur user) throws DALException {
        try ( Connection cnx = ConnectionProvider.getConnection();){

                PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                pStmt.setString(1, user.getPseudo());
                pStmt.setString(2, user.getNom());
                pStmt.setString(3, user.getPrenom());
                pStmt.setString(4, user.getEmail());
                pStmt.setString(5, user.getTelephone());
                pStmt.setString(6, user.getRue());
                pStmt.setString(7, user.getCodePostal());
                pStmt.setString(8, user.getVille());
                pStmt.setString(9, user.getMotDePasse());
                pStmt.setInt(10, user.getCredit());
                pStmt.setInt(11, user.getAdministrateur());


                pStmt.executeUpdate();
                ResultSet rs = pStmt.getGeneratedKeys();

                if (rs.next()) {
                    user.setNoUtilisateur(rs.getInt(1));
                }


            } catch (SQLException e) {
                e.printStackTrace();
                throw new DALException("l'utilisateur " + user + " n'a pas ??t?? ins??r?? ..");

            }

    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        List<Utilisateur> lesUsers = new ArrayList<>();
        Utilisateur user = null;

        try ( Connection cnx = ConnectionProvider.getConnection();){

            PreparedStatement pStmt = cnx.prepareStatement(SELECTALL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()){
                user = new Utilisateur(rs.getInt("no_utilisateurs"),
                        rs.getString("pseudo"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville"),
                        rs.getString("mot_de_passe"),
                        rs.getInt("credit"),
                        rs.getInt("administrateur"));

                lesUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de r??cup??rer la liste des utilisateurs");
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
            throw new DALException("l'utilisateur " + user + " n'a pas ??t?? supprim?? ..");
        }

    }

    @Override
    public void update(Utilisateur user) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(UPDATE);
            stmt.setString(1, user.getPseudo());
            stmt.setString(2, user.getNom());
            stmt.setString(3, user.getPrenom());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getTelephone());
            stmt.setString(6, user.getRue());
            stmt.setString(7, user.getCodePostal());
            stmt.setString(8, user.getVille());
            stmt.setString(9, user.getMotDePasse());
            stmt.setString(10, user.getPseudo());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DALException("probleme dans la modification d'un article");
        }
    }

    @Override
    public Utilisateur selectById(int id) throws DALException {
        Utilisateur user = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(SELECBYID);
            stmt.setInt(1, id);
            System.out.println();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new Utilisateur(rs.getInt("no_utilisateurs"),
                        rs.getString("pseudo"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville"),
                        rs.getString("mot_de_passe"),
                        rs.getInt("credit"),
                        rs.getInt("administrateur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de r??cup??rer l'utilisateur par l'ID");
        }
        return user;
    }

    @Override
    public Utilisateur selectByPseudo(String pseudo) throws DALException {
        Utilisateur user = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(SELECBYPSEUDO);
            stmt.setString(1, pseudo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                    user = new Utilisateur(rs.getInt("no_utilisateurs"),
                            rs.getString("pseudo"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("rue"),
                            rs.getString("code_postal"),
                            rs.getString("ville"),
                            rs.getString("mot_de_passe"),
                            rs.getInt("credit"),
                            rs.getInt("administrateur"));
            }
        } catch (SQLException e) {
            throw new DALException("impossible de r??cup??rer l'utilisateur par le pseudo");
        }
        return user;
    }

    @Override
    public Utilisateur selectByEmail(String email) throws DALException {
        Utilisateur user = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(SELECBYEMAIL);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(email.equals( rs.getString("email"))){
                    throw new DALException("Cet email existe d??j?? !!");
                }else {
                    user = new Utilisateur(rs.getInt("no_utilisateurs"),
                            rs.getString("pseudo"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("rue"),
                            rs.getString("code_postal"),
                            rs.getString("ville"),
                            rs.getString("mot_de_passe"),
                            rs.getInt("credit"),
                            rs.getInt("administrateur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("impossible de r??cup??rer l'utilisateur par l'email");
        }
        return user;
    }

    @Override
    public Utilisateur authentification(String pseudo, String mdp) {
        Utilisateur user = null;
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement stmt = cnx.prepareStatement(CHECK_USER_BY_LOGIN);
            stmt.setString(1, pseudo);
            stmt.setString(2, mdp);
            stmt.setString(3, pseudo);
            stmt.setString(4, mdp);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                user = new Utilisateur(rs.getInt("no_utilisateurs"),
                        rs.getString("pseudo"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville"),
                        rs.getString("mot_de_passe"),
                        rs.getInt("credit"),
                        rs.getInt("administrateur"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean pseudoExist(String pseudo){
        boolean resulat = false;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(CHECK_PSEUDO_IS_UNIQUE);
            stmt.setString(1,pseudo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                resulat = rs.getString("pseudo").equals(pseudo);
            }
        }catch (SQLException throwables) {
            throwables.getMessage();
        }
        return resulat;
    }

    @Override
    public boolean emailExist(String email){
        boolean resulat = false;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(CHECK_EMAIL_IS_UNIQUE);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                resulat = rs.getString("email") == email;
            }

        }catch (SQLException throwables) {
            throwables.getMessage();
        }
        return resulat;
    }

    @Override
    public Utilisateur checkLogin(String login, String mdp) {
        Utilisateur user = null;
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement stmt = cnx.prepareStatement(CHECKUSER);
            stmt.setString(1, login);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                user = new Utilisateur(rs.getString("pseudo"),
                        rs.getString("mot_de_passe"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void ajouterCredit(int id, int point) {
        try(Connection cnx = ConnectionProvider.getConnection()){
            Utilisateur util = selectById(id);
            int resultat = util.getCredit() + point;
            PreparedStatement stmt = cnx.prepareStatement(AJOUTERCREDIT);
            stmt.setInt(1, resultat);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | DALException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void debiterCredit(int id, int point) {
        try(Connection cnx = ConnectionProvider.getConnection()){
            Utilisateur util = selectById(id);
            int resultat = util.getCredit() - point;
            PreparedStatement stmt = cnx.prepareStatement(DEBITER_CREDIT);
            stmt.setInt(1, resultat);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | DALException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void invalider(Utilisateur id) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(INVALIDER);
            stmt.setInt(1, id.getNoUtilisateur());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
