package application;

import java.io.BufferedReader;
import java.io.IOException;

import controllers.PortDetectionController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConnectionsInformation extends Application {
	PortDetectionController controller;
	public Params param = new Params(controller);

   /* public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage) {

    	final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/portDetection.fxml"
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

		primaryStage.setTitle("Liste des connections actives");
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(new Scene(root));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Connexions");
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

    class Params{
    	String ip ="";
    	PortDetectionController c;
    	public Params(PortDetectionController c){
    		this.c=c;
    	}
		public String getIp() {
			c.ip=ip;
			return ip;
		}

		public void setIp(String ip) {
			c.ip = ip;
		}

    }

}
