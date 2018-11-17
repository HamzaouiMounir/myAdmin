package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import controllers.MessagingController;
import controllers.NetworkCardInformationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NetworkCardInformation extends Application{

	NetworkCardInformationController controller;
	public String ip;
   /* public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage) {

    	final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/network_card_information.fxml"
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
		//primaryStage.initStyle(StageStyle.UNDECORATED);

					// TODO Auto-generated method stub


		primaryStage.setScene(new Scene(root));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setResizable(false);
		primaryStage.show();

				// TODO Auto-generated method stub
				WebEngine webEngine = controller.rsxInfo.getEngine();
				webEngine.loadContent("<b>salem </b>");
			     Process p;
			     StringBuilder sb = new StringBuilder();

			 	try {
			 		Socket s = new Socket(ip, 60010);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

					// while (true) {
					out.write("ipconfig");
					out.newLine();
					out.flush();


					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line;



					int i = 0;


			 		    	    while ((line = in.readLine()) != null) {
			 		    	    	if(!line.isEmpty()){
			 		    	    		sb.append("<pre>"+line+"</pre>");
				 		    	    	sb.append("\n");
				 		    	    	System.err.println("line :"+sb.toString());
				 		    	    	webEngine.loadContent(sb.toString());
				 		    	    	//break;
			 		    	    	}







			 		    	   // System.out.println(line);
			 		    	       }
			 		    		s.close();

			 	} catch (IOException e) {
			 		// TODO Auto-generated catch block
			 		e.printStackTrace();
			 	}
			 	//rsxInfo.setHtmlText(sb.toString());





    }

}
