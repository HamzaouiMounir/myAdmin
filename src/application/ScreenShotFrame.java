package application;

import java.io.IOException;

import controllers.MessagingController;
import controllers.ScreenShotController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenShotFrame extends Application{
	public ScreenShotController controller;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    	final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/screenshot.fxml"
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
		primaryStage.setTitle("Imprime écran");
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(new Scene(root));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();
		/*controller.cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("close");
				primaryStage.close();


			}

		});*/
    }
}