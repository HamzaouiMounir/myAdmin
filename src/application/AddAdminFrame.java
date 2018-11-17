package application;

import controllers.AddAdministrateurController;
import controllers.ProfileManagementController;
import controllers.UsersManagementController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Administrateur;
import models.GestionnaireAdministrateur;

public class AddAdminFrame extends Application {
	public AddAdministrateurController controller;
	GestionnaireAdministrateur manager=new GestionnaireAdministrateur();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/add_user.fxml"
			      )
			    );
		BorderPane root = (BorderPane) loader.load();
		 controller = loader.getController();

		primaryStage.setTitle("myAdmin 1.0");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.setScene(new Scene(root));

		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		//primaryStage.setResizable(false);

		primaryStage.show();


		controller.cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}

		});
		controller.validation.setVisible(false);
		controller.save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(controller.nomAdmin.getText().isEmpty()||controller.prenomAdmin.getText().isEmpty()||controller.loginAdmin.getText().isEmpty()||controller.passwordAdmin.getText().isEmpty()){
					controller.validation.setVisible(true);
				}else{
				controller.validation.setVisible(false);
					Administrateur a = new Administrateur(controller.nomAdmin.getText().toString(), controller.prenomAdmin.getText().toString(), controller.loginAdmin.getText().toString(), controller.passwordAdmin.getText().toString());
					int updated=manager.addAdministrateur(a);
				if(updated!=-1){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Administrateur ajouté avec succés");
					alert.setHeaderText("Confirmation");
					alert.setContentText("Vous avez ajouter "+a.getNom()+" "+a.getPrenom()+" Comme administrateur");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Pressed OK.");
					        primaryStage.close();
					    }
					});
				}
			}
			}
		});
	}

	}
