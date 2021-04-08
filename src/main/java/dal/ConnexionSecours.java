package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionSecours {

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:mysql://mysql-encheres.alwaysdata.net/encheres_db", "encheres", "ENI-encheres2021");

        return connection;
    }

}
