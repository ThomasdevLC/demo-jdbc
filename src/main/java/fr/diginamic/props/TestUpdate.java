package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {

	public static void main(String[] args) {
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");

		Connection conn = null;
		Statement stmt = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			int nb = stmt.executeUpdate(
					"UPDATE FOURNISSEUR SET NOM = 'La Maison des Peintures' WHERE NOM = 'La Maison de la Peinture'");

			System.out.println(nb);

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
