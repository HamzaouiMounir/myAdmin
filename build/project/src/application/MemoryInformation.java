package application;

import java.io.IOException;

import controllers.MemoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemoryInformation extends Application {
	MemoryController controller;
   /* public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage) {

    	final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/memory.fxml"
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
		 controller.getMemoryInfo("ram", controller.totalMemory, controller.freeMemory).start();
		primaryStage.setTitle("myAdmin 1.0");
		primaryStage.initStyle(StageStyle.UNIFIED);
		primaryStage.setScene(new Scene(root));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setResizable(false);
		primaryStage.show();
    }

}
