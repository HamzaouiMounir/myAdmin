package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Task;

public class NetworkCardInformationController implements Initializable{
	public static String ip;
	@FXML
	public WebView rsxInfo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//rsxInfo.getEngine().loadContent("<h3>jhazkjazkjh</h3>");
		assert rsxInfo != null : "";





	}

	private void executeCmd(String cmd){
		rsxInfo.getEngine().loadContent("");
		final WebEngine webEngine = rsxInfo.getEngine();
		 Process p;
	     StringBuilder sb = new StringBuilder();

	 	try {
	 		p = Runtime.getRuntime().exec(cmd);
	 		  BufferedReader reader = new BufferedReader(new InputStreamReader(
	 		    	     p.getInputStream()));
	 		    	     String line;

	 		    	    while ((line = reader.readLine()) != null) {

	 		    	    	sb.append("<pre><b style='color:green;'>"+line+"</b></pre>");



	 		    	   // System.out.println(line);
	 		    	       }
	 	} catch (IOException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	//cmdResult.setHtmlText(sb.toString());
	 	//testarea.setText(sb.toString());
	 	webEngine.loadContent(sb.toString());
	}

	public void getRTNetworkInterfaces() {

		rsxInfo.getEngine().loadContent("");
		final WebEngine webEngine = rsxInfo.getEngine();
		StringBuilder sb=getOutput();

				webEngine.loadContent(sb.toString());


	}
	StringBuilder getOutput(){
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

			//final WebEngine webEngine = rsxInfo.getEngine();
			while ((line = in.readLine()) != null) {
				sb.append("<pre><b style='color:green;'>"+line+"</b></pre>");

				System.out.println(sb.toString());
				//webEngine.loadContent(sb.toString());
				}


			return sb;


			//s.close();

			// x = new PrintWriter(s.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
