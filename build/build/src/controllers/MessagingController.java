package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import application.TaskListFrame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MessagingController implements Initializable {
	public static String ip;
	@FXML
	public Button cancel,deleteAll,send;
	@FXML
	public TextArea msgBody;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    	deleteAll.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				msgBody.clear();

			}

		});
    	send.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
			 		Socket s = new Socket(ip, 60010);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

					// while (true) {
					out.write("msg ** "+msgBody.getText());
					out.newLine();
					out.flush();


					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			 		    	     String line;

			 		    	    while ((line = in.readLine()) != null) {
			 		    	    	if(line.equals(""))
			 		    	    		continue;





			 		    	   // System.out.println(line);
			 		    	       }
			 	} catch (IOException e) {
			 		// TODO Auto-generated catch block
			 		e.printStackTrace();
			 	}
			}
		});

    }

}
