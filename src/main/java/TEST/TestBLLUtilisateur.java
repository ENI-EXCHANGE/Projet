package TEST;

import bll.BLLException;
import bll.UtilisateurManagerImpl;
import bo.Utilisateur;
import dal.DALException;
import dal.UtilisateurDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestBLLUtilisateur {

    public static void main(String[] args) throws BLLException {

        List<Utilisateur> users = new ArrayList<>();
        /*Utilisateur user1 = new Utilisateur(0001, "user1", "dupond", "jean", "test@email.fr", "0299010203", "rue montjoie", 35400, "st malo", "password", 0, false);
        Utilisateur user2 = new Utilisateur(0002, "user2", "trevis", "marc", "test2@email.fr", "0299010203", "rue montjoie", 35400, "st malo", "password", 0, true);



        users.add(user1);
        users.add(user2);
        System.out.println(users);

        UtilisateurManagerImpl userTest = new UtilisateurManagerImpl();

         try {
            userTest.addUser(user1);
        } catch (BLLException e) {
            e.printStackTrace();
        }
*/
    }
}