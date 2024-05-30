package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TestConfigurationProps {

    public static void main(String[] args) {
        ResourceBundle config = ResourceBundle.getBundle("config");
        String url = config.getString("database.url");
        String user = config.getString("database.user");
        String pwd = config.getString("database.password");

        Iterator<String> iterateur = config.getKeys().asIterator();
        while (iterateur.hasNext()) {
            String key = iterateur.next();
            System.out.println(key);
        }
        System.out.println(url);

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn = DriverManager.getConnection(url, user, pwd);

            System.out.println(conn.isClosed());

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

