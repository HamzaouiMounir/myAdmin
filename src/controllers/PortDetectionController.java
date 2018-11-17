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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Connection;

public class PortDetectionController implements Initializable {
	@FXML
	public TableView<Connection> connections;
	private  ObservableList<Connection> data = FXCollections.observableArrayList();
	@FXML
	private TableColumn protocolCol, localIpCol, foreginIpCol, stateCol;
	@FXML
	public Button cancel;
	public static String ip;



	public String getIp() {
			return ip;
		}


		public void setIp(String ip) {
			this.ip = ip;
		}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		assert connections != null : "";

		protocolCol.setCellValueFactory(new PropertyValueFactory<Connection, String>("protocol"));
		localIpCol.setCellValueFactory(new PropertyValueFactory<Connection, String>("localAdress"));
		foreginIpCol.setCellValueFactory(new PropertyValueFactory<Connection, String>("publicAdress"));
		stateCol.setCellValueFactory(new PropertyValueFactory<Connection, String>("etat"));
		// connections.setItems(data);
		//loadConnections();
		getConnectivities(connections,data,"netstat -an").start();
		System.out.println(ip);

	}
	public PortDetectionController(){
		System.out.println(ip);
	}


	public void runCmd(String cmd) {

		try {
			Socket s = new Socket(ip, 60010);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			// while (true) {
			out.write(cmd);
			out.newLine();
			out.flush();

			PrintWriter x;
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line;
			while ((line=in.readLine()) != null) {
				System.out.println("$" + in.readLine());
				if (!line.isEmpty()) {
					String[] output = line.split("\\s+");
					System.out.println(output.length);
					System.err.println(line);
					String state = "";
					String protocol = "";
					String localip = "";
					String externip = "";
					if (output.length == 5) {
						protocol = output[1];
						localip = output[2];
						;
						externip = output[3];
						state = output[4];

					} else if (output.length == 4) {
						protocol = output[1];
						localip = output[2];
						;
						externip = output[3];

						state = "-";
					}
					if (!protocol.isEmpty() && !localip.isEmpty() && !externip.isEmpty() && !state.isEmpty())
						data.add(new Connection(protocol, localip, externip, state));

				}

			}

			//s.close();

			// x = new PrintWriter(s.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Thread getConnectivities(TableView<Connection> tab,ObservableList<Connection> data , String cmd){
		Task<Void> task = new Task<Void>() {

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

				PrintWriter x;
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String line;
				while ((line=in.readLine()) != null) {
					System.out.println("$" + in.readLine());
					if (!line.isEmpty()) {
						String[] output = line.split("\\s+");

						System.err.println(line);
						String state = "";
						String protocol = "";
						String localip = "";
						String externip = "";
						if (output.length == 5) {
							protocol = output[1];
							localip = output[2];
							;
							externip = output[3];
							state = output[4];

						} else if (output.length == 4) {
							protocol = output[1];
							localip = output[2];
							;
							externip = output[3];

							state = "-";
						}
						if (!protocol.isEmpty() && !localip.isEmpty() && !externip.isEmpty() && !state.isEmpty()){
							data.add(new Connection(protocol, localip, externip, state));
							System.out.println("il ya "+data.size()+" items");
						}
						tab.setItems(data);

					}

				}




				//s.close();

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
	    thread.setDaemon(true);

	    return thread;
	}
	public void loadConnections() {
		// taskListResult.getEngine().loadContent("");

		Process p;
		StringBuilder sb = new StringBuilder();

		try {
			p = Runtime.getRuntime().exec("netstat -an");

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {

				if (!line.isEmpty()) {
					String[] output = line.split("\\s+");
					System.out.println(output.length);
					System.err.println(line);
					String state = "";
					String protocol = "";
					String localip = "";
					String externip = "";
					if (output.length == 5) {
						protocol = output[1];
						localip = output[2];
						;
						externip = output[3];
						state = output[4];

					} else if (output.length == 4) {
						protocol = output[1];
						localip = output[2];
						;
						externip = output[3];

						state = "-";
					}
					if (!protocol.isEmpty() && !localip.isEmpty() && !externip.isEmpty() && !state.isEmpty())
						data.add(new Connection(protocol, localip, externip, state));

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connections.setItems(data);

	}
}
