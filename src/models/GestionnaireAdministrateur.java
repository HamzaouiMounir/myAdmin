package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.util.text.BasicTextEncryptor;

import com.mysql.jdbc.Statement;

import security.Encryption;

public class GestionnaireAdministrateur {
	Connexion c = new Connexion();
	private com.mysql.jdbc.Connection connexion;
	public GestionnaireAdministrateur(){
		try {
			connexion=c.getConnected();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void close(){
		this.connexion=null;
	}
	public ArrayList<Administrateur> getAllAdministrateur() {


		ArrayList<Administrateur> list = new ArrayList<>() ;
		try {
			Statement stmt = (Statement) connexion.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM administrateur");
			while(res.next()){
				Administrateur admin = new Administrateur(res.getInt("id"),res.getString("nom"), res.getString("prenom"), res.getString("login"),res.getString("password"));
			if(admin!=null){
				list.add(admin);

			}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

		return list;
	}

	public int addAdministrateur(Administrateur admin) {
		// TODO Auto-generated method stub
		int res=-1;
		try{
			Statement stmt = (Statement) connexion.createStatement();
			 res= stmt.executeUpdate("INSERT INTO administrateur(login,password,nom,prenom) values ('"+admin.getLogin()+"','"+admin.getPassword()+"','"+admin.getNom()+"','"+admin.getPrenom()+"')");
			if(res<0){
				System.err.println("Error while inserting");
			}else{
				System.out.println("Bingo");
			}
		}catch(Exception e){

		}
		return res;
	}

	public Administrateur getAdministrateur(int id) {
		// TODO Auto-generated method stub
		Administrateur admin = null;
		try {
			Statement stmt = (Statement) connexion.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM administrateur WHERE id ="+id);
			while(res.next()){
				 admin = new Administrateur(res.getInt("id"),res.getString("nom"), res.getString("prenom"), res.getString("login"),res.getString("password"));
			System.out.println("hi");
			}




		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error");
			e.printStackTrace();
		}
		return admin;
	}

	public boolean deleteAdministrateur(int id) {
		// TODO Auto-generated method stub
		int res=-1;
		boolean deleted=false;
		try{
			Statement stmt = (Statement) connexion.createStatement();
			 res= stmt.executeUpdate("DELETE FROM administrateur WHERE id ="+id);
			if(res<0){
				System.err.println("Error while deleting");
			}else{
				System.out.println("Bingo");
				deleted=true;

			}
		}catch(Exception e){

		}

		return deleted;
	}
	public boolean updateAdministrateur(Administrateur a) {
		// TODO Auto-generated method stub
		int res=-1;
		//String password = Encryption.encrypt(a.getPassword());
		boolean updated=false;
		try{
			Statement stmt = (Statement) connexion.createStatement();
			 res= stmt.executeUpdate("UPDATE administrateur  SET nom ='"+a.getNom()+"' , prenom='"+a.getPrenom()+"',login ='"+a.getLogin()+"' , password ='"+a.getPassword()+"' WHERE id ="+a.getId()+"");
			if(res<0){
				System.err.println("Error while updating");
			}else{
				System.out.println("Bingo");
				updated=true;

			}
		}catch(Exception e){

		}

		return updated;
	}
	public int auth(String login , String password){

			Administrateur admin = null;
			boolean connected=false;
			int id=-1;
			try {
				Statement stmt = (Statement) connexion.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM administrateur WHERE login = '"+login+"'");
				while(res.next()){
					 admin = new Administrateur(res.getInt("id"),res.getString("nom"), res.getString("prenom"), res.getString("login"),res.getString("password"));
					 String adminPassword=admin.getPassword();
					 if(adminPassword.equals(password)){
						 connected =true;
						 id=admin.getId();
					 }

				}




			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Error");
				e.printStackTrace();
			}
			return id;

	}
	public static void main(String[] args) {
		GestionnaireAdministrateur manager = new GestionnaireAdministrateur();
		manager.addAdministrateur(new Administrateur("Hamzaoui", "Mounir", "hamzaoui", Encryption.encrypt("123456")));
		/*BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("myAdmin");
		String encrypted1 = textEncryptor.encrypt(manager.getAdministrateur(2).getPassword());
		System.out.println(encrypted1);
		String encrypted2 = textEncryptor.encrypt(manager.getAdministrateur(2).getPassword());
		System.out.println(textEncryptor.decrypt(encrypted1));
		//System.err.println(textEncryptor.decrypt("sbEwlnBVjZFJVGFx3MvYAw=="));
		int res= manager.auth("HamzaouiMounir", "123456");
		if(res!=-1){
			System.out.println("Connected");
		}else{
			System.err.println("No Such User here ");
		}*/
		for(Administrateur admin : manager.getAllAdministrateur()){
			System.out.println(admin.getNom());
		}
		manager.close();
	}
}
