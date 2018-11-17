package models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
public class Connexion {
	public  Connection connexion=null;
	public  Connection getConnected() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/myadmin";
		String login = "root";
		String password = "";
		try {
		Class.forName("com.mysql.jdbc.Driver");
		connexion =  (Connection) DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
		System.out.println("Problème lors de la connection sur la base");
		e.printStackTrace();
		}
		return connexion;
		}

	/*public static void main(String[] args) {
		DaoCrud crud = new DaoCrud();
		try {
			crud.getConnected();
			Compte c = new Compte("Hamzaoui", "Mounir", 255.0f);
			crud.addCompte(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}*/


}
