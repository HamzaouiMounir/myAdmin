package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Administrateur;
import models.GestionnaireAdministrateur;

public class AddAdministrateurController implements Initializable{

	GestionnaireAdministrateur manager=new GestionnaireAdministrateur();
	@FXML
	public Button cancel , save;
	@FXML
	public TextField nomAdmin,prenomAdmin , loginAdmin ;
	@FXML
	public PasswordField passwordAdmin ;
	@FXML
	public Label validation;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


				// TODO Auto-generated method stub




	}

}
