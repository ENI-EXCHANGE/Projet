package TEST;

import bll.ArticleManager;
import bll.ArticleManagerImpl;
import bll.BLLException;
import bll.BLLFactory;
import bo.Article;


import java.sql.*;
import java.time.LocalDate;

public class testCo {


    private static final String sqlInsert ="INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";

    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://mysql-encheres.alwaysdata.net/encheres_db", "encheres", "ENI-encheres2021");
        }
        return connection;
    }

    public static void main(String[] args) throws BLLException {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver installé !!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection cnx = getConnection()){
            System.out.println("Connexion OK !!");

            Article ArticleTest = new Article("artiTest", "ARtde", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 1,  2, 1, 1);
            System.out.println("on se prepare a ajouter l'article");


            try {

                PreparedStatement pStmtArticle = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
                pStmtArticle.setString(1, ArticleTest.getNom_article());
                pStmtArticle.setString(2, ArticleTest.getDescription());
                pStmtArticle.setDate(3, ArticleTest.getDate_debut_encheres());
                pStmtArticle.setDate(4, ArticleTest.getDate_fin_encheres());
                pStmtArticle.setInt(5, ArticleTest.getPrix_initial());
                pStmtArticle.setInt(6, ArticleTest.getPrix_vente());
                pStmtArticle.setInt(7, ArticleTest.getNo_utilisateur());
                pStmtArticle.setInt(8, ArticleTest.getNo_categorire());

                pStmtArticle.executeUpdate();

                ResultSet rsArticle = pStmtArticle.getGeneratedKeys();
                if(rsArticle.next()) {
                    ArticleTest.getNo_article(rsArticle.getInt(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("C'est GOOD !!!");

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}