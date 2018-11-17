package application;

import controllers.AuthentificationController;
import controllers.MemoryController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.GestionnaireAdministrateur;

public class AuthentificationFrame extends Application {
	AuthentificationController controller;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/Authentification.fxml"
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
		controller.exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}

		});
		controller.connexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GestionnaireAdministrateur manager= new GestionnaireAdministrateur();
					String username = controller.login.getText().toString();
					String pass = controller.password.getText().toString();
					int value =manager.auth(username, pass);
					if(value!=-1){

						System.out.println("Connected");
						Main main = new Main();
						Controller controler=main.controller;
						controler.adminId=value;
						primaryStage.close();
						main.start(new Stage());

					}else{
						controller.errorMsg.setVisible(true);
						controller.login.clear();
						controller.password.clear();
					}
			}

		});

	}
	public static void main(String[] args) {
		launch(args);
	}

}
