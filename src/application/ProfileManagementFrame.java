package application;

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

public class ProfileManagementFrame extends Application {
	public ProfileManagementController controller;
	GestionnaireAdministrateur manager=new GestionnaireAdministrateur();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/profile_management.fxml"
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

				// TODO Auto-generated method stub
				Administrateur a = manager.getAdministrateur(controller.adminID);
				controller.nomAdmin.setText(a.getNom());
				controller.prenomAdmin.setText(a.getPrenom());
				controller.loginAdmin.setText(a.getLogin());
				controller.passwordAdmin.setText(a.getPassword());

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
				Administrateur admin = new Administrateur(controller.adminID, controller.nomAdmin.getText().toString(), controller.prenomAdmin.getText().toString(), controller.loginAdmin.getText().toString(), controller.passwordAdmin.getText().toString());

				boolean updated =manager.updateAdministrateur(admin);
				if(updated){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Mise � jour eff�ctu�e avec succ�s");
					alert.setHeaderText("Mise � jour");
					alert.setContentText("I have a great message for you!");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {

									UsersManagementFrame usersList = new UsersManagementFrame();
									UsersManagementController c=usersList.controller;
								try {
									usersList.start(new Stage());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}

					});
				}
			}
			}
		});

	}
	public static void main(String[] args) {
		launch(args);
	}

}
