package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
	
	
	

	ResourceBundle config = ResourceBundle.getBundle("config");
	String url = config.getString("database.url");
	String user = config.getString("database.user");
	String pwd = config.getString("database.password");

	@Override
	public List<Fournisseur> extraire() {
		List<Fournisseur> listeFournisseurs = new ArrayList<>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, pwd);
			connection.setAutoCommit(false);

			statement = connection.prepareStatement("SELECT * FROM fournisseur");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				listeFournisseurs.add(fournisseur);
				System.out.println(fournisseur);
			}

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {

				traiterErreur(e);
			} finally {
				try {
					resultSet.close();
					statement.close();
					connection.close();

				} catch (SQLException e1) {

					traiterErreur(e);

				}
			}
		}
		return listeFournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, pwd);

			connection.setAutoCommit(false);

			statement = connection.prepareStatement("INSERT INTO FOURNISSEUR (ID, NOM) VALUES (?, ?)");
			statement.setInt(1, fournisseur.getId());
			statement.setString(2, fournisseur.getNom());
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {

				traiterErreur(e);
			} finally {
				closeResources(connection, statement, e);
			}
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Connection connection = null;
		PreparedStatement statement = null;
		int nb = 0;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, pwd);

			connection.setAutoCommit(false);

			statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?");
			statement.setString(1, nouveauNom);
			statement.setString(2, ancienNom);
			nb = statement.executeUpdate();

			System.out.println("Nombre de lignes mises à jour: " + nb);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {

				traiterErreur(e);
			} finally {
				closeResources(connection, statement, e);
			}
		}

		return nb;
	}



	

	@Override
	public boolean delete(Fournisseur fournisseur) {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, pwd);

			connection.setAutoCommit(false);

			statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE NOM = ?");
			statement.setString(1, fournisseur.getNom());
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {

				traiterErreur(e);
			} finally {
				closeResources(connection, statement, e);
			}
		}

		return false;
	}
	
	
	private void traiterErreur(SQLException e) {
		System.err.println(e.getMessage());
		e.printStackTrace();
	}
	
	private void closeResources(Connection connection, PreparedStatement statement, SQLException e) {
		try {
			statement.close();
			connection.close();

		} catch (SQLException e1) {

			traiterErreur(e);

		}
	}
}



