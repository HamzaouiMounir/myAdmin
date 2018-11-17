package models;

import javafx.beans.property.SimpleStringProperty;

public class Connection {
private final SimpleStringProperty protocol ;
private final SimpleStringProperty localAdress ;
private final SimpleStringProperty publicAdress ;
private final SimpleStringProperty etat;

public Connection(String protocol, String localAdress, String publicAdress,
		String etat) {

	this.protocol = new SimpleStringProperty(protocol);
	this.localAdress = new SimpleStringProperty(localAdress);
	this.publicAdress = new SimpleStringProperty(publicAdress);
	this.etat = new SimpleStringProperty(etat);
}
public String getProtocol() {
	return protocol.get();
}
public String getLocalAdress() {
	return localAdress.get();
}
public String getPublicAdress() {
	return publicAdress.get();
}
public String getEtat() {
	return etat.get();
}

}
