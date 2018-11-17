package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Administrateur {
private SimpleIntegerProperty id ;
private SimpleStringProperty nom ;
private SimpleStringProperty prenom;
private SimpleStringProperty login ;
private SimpleStringProperty password;
public Administrateur(){

}

public Administrateur(int id,String nom, String prenom, String login, String password) {
	super();
	this.id = new SimpleIntegerProperty(id);
	this.nom = new SimpleStringProperty(nom);
	this.prenom = new SimpleStringProperty(prenom);
	this.login = new SimpleStringProperty(login);
	this.password = new SimpleStringProperty(password);
}
public Administrateur(String nom, String prenom, String login, String password) {

	this.nom = new SimpleStringProperty(nom);
	this.prenom = new SimpleStringProperty(prenom);
	this.login = new SimpleStringProperty(login);
	this.password = new SimpleStringProperty(password);
}

public int getId() {
	return id.get();
}
public void setId(int id) {
	this.id.set(id);
}
public String getNom() {
	return nom.get();
}
public void setNom(String nom) {
	this.nom.set(nom);
}
public String getPrenom() {
	return prenom.get();
}
public void setPrenom(String prenom) {
	this.prenom.set(prenom);
}
public String getLogin() {
	return login.get();
}
public void setLogin(String login) {
	this.login.set(login);
}
public String getPassword() {
	return password.get();
}
public void setPassword(String password) {
	this.password.set(password);
}

}
