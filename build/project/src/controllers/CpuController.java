package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;

public class CpuController implements Initializable {
	public static String ip;
    @FXML
    private Label nom,id,loadLevel ,description,type,etat,fabriquant,creationClassSystem,architecture,addressWidth,speed,maxSpeed,voltage;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    	assert nom != null : "";
    	assert description != null : "";
    	assert type != null : "";
    	assert etat != null : "";
    	assert fabriquant != null : "";
    	assert creationClassSystem != null : "";
    	assert architecture != null : "";
    	assert addressWidth != null : "";
    	assert speed != null : "";
    	assert maxSpeed != null : "";
    	assert voltage != null : "";
    	assert id != null : "";
    	assert loadLevel != null : "";
    	getCPUInfo();


    }
    private void getCPUInfo(){
    	 Process p;
	     StringBuilder sb = new StringBuilder();

	 	try {
	 		Socket s = new Socket(ip, 60010);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			// while (true) {
			out.write("wmic cpu get name , description ,ProcessorType,Status,Manufacturer,SystemCreationClassName, Architecture ,AddressWidth ,CurrentClockSpeed , MaxClockSpeed , CurrentVoltage , ProcessorId , LoadPercentage /format:list");
			out.newLine();
			out.flush();


			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	 	/*	p = Runtime.getRuntime().exec("wmic cpu get name , description ,ProcessorType,Status,Manufacturer,SystemCreationClassName, Architecture ,AddressWidth ,CurrentClockSpeed , MaxClockSpeed , CurrentVoltage , ProcessorId , LoadPercentage /format:list" );
	 		p.waitFor();
	 		BufferedReader reader = new BufferedReader(new InputStreamReader(
	 		    	     p.getInputStream()));*/
	 		    	     String line;

	 		    	    while ((line = in.readLine()) != null) {
	 		    	    	if(line.equals(""))
	 		    	    		continue;

	 		    	    	sb.append(line);
	 		    	    	lineToLabel(line);
	 		    	    	System.err.println(line);



	 		    	   // System.out.println(line);
	 		    	       }
	 	} catch (IOException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	System.out.println(sb.toString());
    }
    private void lineToLabel(String line){
    	String entete = line.substring(0, line.indexOf('='));
    	String value= line.substring(line.indexOf('=')+1,line.length());
    	switch(entete){
    	case "AddressWidth":
    		addressWidth.setText(value);
    		break;

    	case "Architecture":
    		architecture.setText(value);
    		break;

    	case "CurrentClockSpeed":
    		speed.setText(value);
    		break;

    	case "CurrentVoltage":
    		voltage.setText(value);
    		break;

    	case "Description":
    		description.setText(value);
    		break;

    	case "LoadPercentage":
    		loadLevel.setText(value+" %");
    		break;

    	case "Manufacturer":
    		fabriquant.setText(value);
    		break;

    	case "MaxClockSpeed":
    		maxSpeed.setText(value);
    		break;

    	case "Name":
    		nom.setText(value);
    		break;

    	case "ProcessorId":
    		id.setText(value);
    		break;

    	case "ProcessorType":
    		type.setText(value);
    		break;

    	case "Status":
    		etat.setText(value);
    		break;

    	case "SystemCreationClassName":
    		creationClassSystem.setText(value);
    		break;
    	}
    }


}
