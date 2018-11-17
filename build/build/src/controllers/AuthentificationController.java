package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthentificationController implements Initializable {
	@FXML
	public TextField login ;
	@FXML
	public PasswordField password;
	@FXML
	public Button connexion,exit;

	@FXML
	public Label errorMsg;
	@Override
	public void initialize(URL location, ResourceBundle resources) {



	}

}
