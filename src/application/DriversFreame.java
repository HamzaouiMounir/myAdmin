package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import controllers.DriveInformationsController;
import controllers.PortDetectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DriversFreame  extends Application {
	PortDetectionController controller;
	public String ip ;
   /* public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage) {

    	BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 380, 150, Color.WHITE);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(root.getMaxWidth());
        ColumnConstraints column2 = new ColumnConstraints(300);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1);
        gridpane.setGridLinesVisible(true);

        // First name label
        File[] list_root = File.listRoots();
    	String infoHD="<disc>";
    	float total=0;

    	    /*for (int i = 0; i < list_root.length; i++)
    	    {
    	     	File disc=list_root[i];
    	     	total+=disc.getTotalSpace()/(1024.0*1024.0*1024.0);
    	     	double used = total - (disc.getFreeSpace()/(1024.0*1024.0*1024.0));
    	     	double level = (used*100/total)/100;
    	     	 BorderPane b1 = generateItem(disc.toString(),String.valueOf(Math.round(used))+" Go utilisés de "+String.valueOf(Math.round(total))+" Go",level,"/res/harddrive.png");
    	     	gridpane.add(b1, 0, i);
    	     	total=0;

    		}*/
    	    try {
		 		Socket s = new Socket(ip, 60010);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

				// while (true) {
				out.write("storage");
				out.newLine();
				out.flush();


				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String line;



				int i = 0;


		 		    	    while ((line = in.readLine()) != null) {
		 		    	    	if(!line.isEmpty()){
		 		    	    		String [] output = line.split("\\s+");
		 		    	    		String discName = output[0] ;
		 		    	    		String capacity = output[1];
		 		    	    		String used = output[2];
		 		    	    		String level = output[3];
		 		    	    		System.out.println(discName);
		 		    	    		 BorderPane b1 = generateItem(discName,String.valueOf(Math.round(Double.parseDouble(used)))+" Go utilisés de "+String.valueOf(Math.round(Double.parseDouble(capacity)))+" Go",Double.parseDouble(level),"/res/harddrive.png");
		 		        	     	gridpane.add(b1, 0, i);
		 		        	     	i++;
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




        root.setCenter(gridpane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lecteurs de stockage");
        primaryStage.setWidth(600);
        primaryStage.setHeight(gridpane.getHeight());
        primaryStage.show();
      }
    public BorderPane generateItem(String name , String details , double levelValue , String imagePath){


		final FXMLLoader loader = new FXMLLoader(
			      Main.class.getResource(
			        "/views/discStorage.fxml"
			      )
			    );
		BorderPane root = null;
		try {
			root = (BorderPane) loader.load();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriveInformationsController item = loader.<DriveInformationsController>getController();
		item.setName(name);
		item.setDetails(details);
		item.setDriveType(imagePath);
		item.setLevelValue(levelValue);
		return root;


	}
    }

