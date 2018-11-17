package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Task;

public class MemoryController implements Initializable {
	ObservableList<Data> answer = FXCollections.observableArrayList();
    @FXML
    public  PieChart memoryChart;
    @FXML
    public Label totalMemory,usedMemory,freeMemory;
    public static String ip;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    	assert memoryChart != null : "";
    	assert totalMemory != null : "";
    	assert usedMemory != null : "";
    	assert freeMemory != null : "";

     //   memoryChart.setData(getChartData());
        ///getMemoryInfo("ram",totalMemory,freeMemory).start();
        //memoryChart.setData(getChartData(20, 2));
    	try{
    	Socket s = new Socket(ip, 60010);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			// while (true) {
			out.write("ram");
			out.newLine();
			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			/*while(in.readLine()!=null){
				String line = String.valueOf(in.readLine());
				System.out.println(line);
				total.setText(line);
			}*/
				String line = String.valueOf(in.readLine());
				System.out.println(line);
				String[] output = line.split("\\s+");

				String totalM = output[0];
				String freeM = output[1];
				//totalMemory.setText(String.valueOf(totalM));
				//freeMemory.setText(String.valueOf(freeM));
				System.err.println("Total "+totalM +" Free"+freeM);

					float t = Float.parseFloat(totalM);
					float f = Float.parseFloat(freeM);

					memoryChart.setData(getChartData(t, f));



			// x = new PrintWriter(s.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();}
		}




    private ObservableList<Data> getChartData(float mem,float free) {

            float used = mem-free;
            long total =Math.round(mem/(1024*1024*1024));
            long libre =Math.round(free/(1024*1024));
            long utilise =Math.round(used/(1024*1024));

            totalMemory.setText(String.valueOf(total)+" Go");
            usedMemory.setText(String.valueOf(utilise)+" Mo");
            freeMemory.setText(String.valueOf(libre)+" Mo");
            ObservableList<Data> answer = FXCollections.observableArrayList();
            answer.addAll(
                    new PieChart.Data("Mémoire utilisée ", used),
                    new PieChart.Data("Mémoire libre ", free));
            String [] pieColors = new String []{"#F44336","#3F51B5","#FFC107"};

            return answer;
        }

     public Thread getMemoryInfo(String cmd,Label total,Label free) {

 		javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<Void>() {

 			@Override
 			protected Void call() throws Exception {
 				// TODO Auto-generated method stub
 				try {
 					Socket s = new Socket(ip, 60010);
 					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

 					// while (true) {
 					out.write(cmd);
 					out.newLine();
 					out.flush();

 					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
 					/*while(in.readLine()!=null){
 						String line = String.valueOf(in.readLine());
 						System.out.println(line);
 						total.setText(line);
 					}*/
	 					String line = String.valueOf(in.readLine());
	 					System.out.println(line);
	 					String[] output = line.split("\\s+");

	 					String totalM = output[0];
	 					String freeM = output[1];
	 					total.setText(String.valueOf(totalM));
	 					free.setText(String.valueOf(freeM));
	 					System.err.println("Total "+totalM +" Free"+freeM);
	 					if(!totalM.isEmpty() && !freeM.isEmpty()){
	 						float t = Float.parseFloat(totalM);
	 						float f = Float.parseFloat(freeM);

	 						//getChartData(22, 23);
	 					}


 					// x = new PrintWriter(s.getOutputStream());

 				} catch (UnknownHostException e) {
 					e.printStackTrace();
 				} catch (IOException e) {
 					e.printStackTrace();
 				}

 				return null;

 			}
 		};
 		Thread thread = new Thread(task);
 	//	thread.setDaemon(true);

 		return thread;
 	}

}
