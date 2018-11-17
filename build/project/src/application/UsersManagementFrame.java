package application;

import controllers.ProfileManagementController;
import controllers.UsersManagementController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Administrateur;

public class UsersManagementFrame extends Application {
	UsersManagementController controller;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//primaryStage.initModality(Modality.APPLICATION_MODAL);
		final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/users_managment.fxml"
			      )
			    );
		BorderPane root = (BorderPane) loader.load();
		 controller = loader.getController();

		//primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.setScene(new Scene(root));

		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		//primaryStage.setResizable(false);
		primaryStage.show();
		controller.modify.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Administrateur a = controller.adminTable.getSelectionModel().getSelectedItem();
				System.out.println("Ram Information");
				ProfileManagementFrame profile = new ProfileManagementFrame();
				ProfileManagementController controler=profile.controller;
				controler.adminID=a.getId();
					try {
						primaryStage.close();
						profile.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}




			}

		});


	}
	/*public static void main(String[] args) {
		launch(args);
	}*/

}
