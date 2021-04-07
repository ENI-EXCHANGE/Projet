package dal;

public class UtilisateurDAOFact {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl();
    }
}
