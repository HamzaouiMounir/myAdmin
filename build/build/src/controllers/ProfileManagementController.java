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

public class ProfileManagementController implements Initializable {
	public static int adminID=2;
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
		System.err.println("ADMIN ID ="+adminID);

				// TODO Auto-generated method stub
			/*	Administrateur a = manager.getAdministrateur(adminID);
				nomAdmin.setText(a.getNom());
				prenomAdmin.setText(a.getPrenom());
				loginAdmin.setText(a.getLogin());
				passwordAdmin.setText(a.getPassword());*/

		/*Administrateur a = manager.getAdministrateur(adminID);
		nomAdmin.setText(a.getNom());
		prenomAdmin.setText(a.getPrenom());
		loginAdmin.setText(a.getLogin());
		passwordAdmin.setText(a.getPassword());*/
		/*validation.setVisible(false);
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(nomAdmin.getText().isEmpty()||prenomAdmin.getText().isEmpty()||loginAdmin.getText().isEmpty()||passwordAdmin.getText().isEmpty()){
					validation.setVisible(true);
				}else{
					validation.setVisible(false);
				Administrateur admin = new Administrateur(adminID, nomAdmin.getText().toString(), prenomAdmin.getText().toString(), loginAdmin.getText().toString(), passwordAdmin.getText().toString());

				boolean updated =manager.updateAdministrateur(admin);
				if(updated){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Mise à jour efféctuée avec succés");
					alert.setHeaderText("Mise à jour");
					alert.setContentText("I have a great message for you!");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Pressed OK.");
					    }
					});
				}
			}
			}
		});*/
	}

}
