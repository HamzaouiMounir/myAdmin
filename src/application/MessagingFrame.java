package application;

import java.io.IOException;

import controllers.MessagingController;
import controllers.TaskListController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessagingFrame extends Application{
	MessagingController controller;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    	final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/sendMessage.fxml"
			      )
			    );
		BorderPane root = null;
		try {
			root = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 controller = loader.getController();
		//primaryStage.setTitle("myAdmin 1.0");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(new Scene(root));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setResizable(false);
		primaryStage.show();
		controller.cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("close");
				primaryStage.close();


			}

		});
    }
}
