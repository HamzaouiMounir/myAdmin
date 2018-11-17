package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.ResourceBundle;

import application.Main.XCell;
import controllers.AddAdministrateurController;
import controllers.CpuController;
import controllers.MemoryController;
import controllers.MessagingController;
import controllers.NetworkCardInformationController;
import controllers.PortDetectionController;
import controllers.ProfileManagementController;
import controllers.ScreenShotController;
import controllers.TaskListController;
import controllers.UsersManagementController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Administrateur;
import models.ServerManager;

public class Controller implements Initializable {
	public static int adminId;
	ArrayList<String> test;
	ObservableList<Socket> data = FXCollections.observableArrayList();
	// ListView<Socket> listView = new ListView<Socket>();
	ServerManager manager;
	@FXML
	private MenuItem configServeur;
	private BorderPane main;
	@FXML
	private ListView<Socket> conHosts;
	@FXML
	private MenuItem close;
	@FXML
	public MenuItem maximize,myprofile;
	@FXML
	public MenuItem minimize , listGestionnaire,addGestionnaire,fullscreenSize,mediumSize,smallSize;
	@FXML
	public Button disconnect , execute,sysInfo,ramInformation,cpu,tasks,msg,storage,connectivity,interfaces,execCmd,screenshot,shuttdown,restart;
	@FXML
	public ProgressIndicator loadingHosts;
	Task copyWorker;
	@FXML
	public Label offHostCount, onHostCount,date,time,o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o12;
	TextFlow txt;
	@FXML
	public WebView cmdResult;
	@FXML
	public BorderPane mngmnt;
	@FXML
	public TextField cmdLine;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		final ToggleGroup tasksGroup = new ToggleGroup();
		System.out.println("Administration is loaded now ");
		// assert configServeur !=null : "id=\"serveur\" was not injected: check
		// your FXML file 'Administration.fxml'.";
		assert conHosts != null : "fx:id=\"conHosts\" was not injected: check your FXML file 'simple.fxml'.";
		;
		assert close != null : "";
		assert maximize != null : "";
		assert minimize != null : "";
		assert disconnect != null : "";
		assert offHostCount != null : "";
		assert onHostCount != null : "";
		assert date != null : "";
		assert time != null : "";
		assert cmdResult != null : "";
		System.err.println("Admin id="+adminId);
		//assert sysInfo != null : "";
		/*ImageView image = new ImageView(new Image("/res/memory.png",150,150,false,false));

		sysInfo.setGraphic(image);*/

	   /* final WebEngine webEngine = cmdResult.getEngine();

	     Process p;
	     StringBuilder sb = new StringBuilder();

	 	try {
	 		p = Runtime.getRuntime().exec("systeminfo");
	 		  BufferedReader reader = new BufferedReader(new InputStreamReader(
	 		    	     p.getInputStream()));
	 		    	     String line;

	 		    	    while ((line = reader.readLine()) != null) {

	 		    	    	sb.append("<pre>"+line+"</pre>");



	 		    	   // System.out.println(line);
	 		    	       }
	 	} catch (IOException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	//cmdResult.setHtmlText(sb.toString());
	 	//testarea.setText(sb.toString());
	 	webEngine.loadContent(sb.toString());*/
		conHosts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Socket>() {

			@Override
			public void changed(ObservableValue<? extends Socket> observable, Socket oldValue, Socket newValue) {
				// TODO Auto-generated method stub

				System.out.println(newValue.getInetAddress().getHostAddress());

					if(newValue.isClosed()){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Suppression");
						alert.setHeaderText("Confirmation");
						alert.setContentText("Voulez vous vraiment supprimer cette administrateur");
						alert.showAndWait().ifPresent(rs -> {
						    if (rs == ButtonType.OK) {

						    }
						});
						alert.show();
					}


				try {
			 		Socket s = new Socket(conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress(), 60010);

			 		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

					// while (true) {
					out.write("sysinfo");
					out.newLine();
					out.flush();


					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			 		    	     String line;

			 		    	    if ((line = in.readLine()) != null) {

			 		    	    	String [] output = line.split("\\s+");
			 		    	    	String osname = output[0]+" "+output[1];
			 		    	    	o1.setText(osname);
									   			String osversion= output[2];
									   o2.setText(osversion);
									   String username= output[3];
									   o3.setText(username);
									   String country= output[4];
									   o4.setText(country);
									   String language= output[5];
									   o5.setText(language);
									   String osarch= output[5];
									   String suncpuisalist= output[6];
									   String scSize= output[7];
									   String rslution= output[8];
									   o6.setText(rslution);
									   String userhome= output[11];
									   o7.setText(userhome);
									   String javahome= output[12];
									   o8.setText(javahome);
									   String java= output[13];
									   o9.setText(java);
									   String javavendorurl= output[15];
									   o10.setText(javavendorurl);


			 		    	    	System.err.println(line);



			 		    	   // System.out.println(line);
			 		    	       }
			 		    	   mngmnt.setDisable(false);

			 	} catch (IOException e) {
			 		// TODO Auto-generated catch block
			 		e.printStackTrace();
			 	}

			}
		});
		conHosts.setCellFactory(new Callback<ListView<Socket>, ListCell<Socket>>() {
			@Override
			public ListCell<Socket> call(ListView<Socket> arg0) {

				// TODO Auto-generated method stub
				return new XCell();
			}
		});

		// initializeServer();

		// configureServer.setStyle(" -fx-background-color: #E91E63;
		// -fx-text-fill: #FFFFFF; -fx-background-radius: 4;");
		configServeur.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Configuration Serveur");

			}

		});
		execCmd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if(!cmdLine.getText().isEmpty()){
					WebEngine webEngine = cmdResult.getEngine();
					webEngine.loadContent("<b style='align:center;color:red;'>Invite de Commande myAdmin 1.0 </b>");

				     StringBuilder sb = new StringBuilder();
				     String cmd = cmdLine.getText().toString();
					try {
				 		Socket s = new Socket(conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress(), 60010);
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

						// while (true) {
						out.write(cmd);
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

		});
		shuttdown.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alerte");
				alert.setHeaderText("Voulez vous vraiment arrêter cette machine ?");

				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				    	try {
					 		Socket s = new Socket(conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress(), 60010);
							BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

							// while (true) {
							out.write("shutdown /s");
							out.newLine();
							out.flush();


							BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));


					 	} catch (IOException e) {
					 		// TODO Auto-generated catch block
					 		e.printStackTrace();
					 	}
					 	//rsxInfo.setHtmlText(sb.toString());
				    }
				});


			}
		});
		restart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alerte");
				alert.setHeaderText("Voulez vous vraiment redémarrer cette machine ?");

				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				    	try {
					 		Socket s = new Socket(conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress(), 60010);
							BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

							// while (true) {
							out.write("shutdown /r");
							out.newLine();
							out.flush();


							BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));


					 	} catch (IOException e) {
					 		// TODO Auto-generated catch block
					 		e.printStackTrace();
					 	}
					 	//rsxInfo.setHtmlText(sb.toString());
				    }
				});


			}
		});




		ramInformation.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Ram Information");
				MemoryInformation memory = new MemoryInformation();
				MemoryController controler=memory.controller;
				controler.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();

				memory.start(new Stage());


			}

		});
		myprofile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Ram Information");
				ProfileManagementFrame profile = new ProfileManagementFrame();
				ProfileManagementController controler=profile.controller;
				controler.adminID=adminId;
					try {
						profile.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



			}

		});
		addGestionnaire.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Ram Information");
				AddAdminFrame newAdmin = new AddAdminFrame();
				AddAdministrateurController controler=newAdmin.controller;

					try {
						newAdmin.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



			}

		});
		listGestionnaire.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Ram Information");
				UsersManagementFrame usersList = new UsersManagementFrame();
				UsersManagementController controler=usersList.controller;

					try {
						usersList.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



			}

		});
		cpu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("cpu Information");
				CpuInformation processor = new CpuInformation();
				CpuController controller=processor.controller;
				controller.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				processor.start(new Stage());

			}

		});
		screenshot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				ScreenShotFrame print = new ScreenShotFrame();
				ScreenShotController controler=print.controller;
				controler.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();

				print.start(new Stage());


			}

		});
		tasks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("cpu Information");
				TaskListFrame tlist = new TaskListFrame();
				TaskListController c = tlist.controller;
				c.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				tlist.start(new Stage());

			}

		});
		msg.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("cpu Information");
				MessagingFrame msgs = new MessagingFrame();
				MessagingController c = msgs.controller;
				c.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				msgs.start(new Stage());


			}

		});
		storage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				DriversFreame frame = new DriversFreame();
				frame.ip= conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				frame.start(new Stage());

			}

		});
		connectivity.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
		//	runCmd("netstat -an");

				ConnectionsInformation frame = new ConnectionsInformation();
				PortDetectionController c = frame.controller;
				c.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				frame.start(new Stage());


			}

		});
		close.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("close");
				Platform.exit();
				System.exit(0);
				manager.setServerState(false);

			}

		});
		interfaces.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				NetworkCardInformation networkCardInformation = new NetworkCardInformation();
				NetworkCardInformationController c = networkCardInformation.controller;
				networkCardInformation.ip=conHosts.getSelectionModel().getSelectedItem().getInetAddress().getHostAddress();
				networkCardInformation.start(new Stage());

			}

		});
		onHostCount.setText("0");
		offHostCount.setText("0");

	}

	public void initializeServer() {
		try {
			manager = new ServerManager(getLocalAddress(), 2999, 200, data, conHosts, onHostCount, offHostCount);
			System.out.println(manager.isServerState());
			//manager.setDaemon(true);
			manager.start();
			//new Rtc(conHosts, onHostCount, offHostCount,date,time).start();

		} catch (Exception e) {

		}
	}

	public ListView<Socket> getConHosts() {
		return conHosts;
	}

	public void setConHosts(ListView<Socket> conHosts) {
		this.conHosts = conHosts;
	}

	public Label getOffHostCount() {
		return offHostCount;
	}

	public void setOffHostCount(Label offHostCount) {
		this.offHostCount = offHostCount;
	}

	public Label getOnHostCount() {
		return onHostCount;
	}

	public void setOnHostCount(Label onHostCount) {
		this.onHostCount = onHostCount;
	}

	public void setLabels(int onH, int offH) {
		// TODO Auto-generated method stub
		onHostCount.setText("ON");
		offHostCount.setText(String.valueOf(offH));
	}

	public void runCmd(String cmd ) {

                try {
                    Socket s = new Socket("172.16.1.73", 60010);
                    BufferedWriter out = new BufferedWriter(
                            new OutputStreamWriter(s.getOutputStream()));

                    //while (true) {
                        out.write(cmd);
                        out.newLine();
                        out.flush();

              	      PrintWriter x;
                       BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           	         while(in.readLine()!=null){
           	        	 System.out.println("$"+in.readLine());

           	         }

           	         s.close();

           	      //   x = new PrintWriter(s.getOutputStream());


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


    }
	private void executeCmd(String cmd){
		cmdResult.getEngine().loadContent("");
		final WebEngine webEngine = cmdResult.getEngine();
		 Process p;
	     StringBuilder sb = new StringBuilder();

	 	try {
	 		p = Runtime.getRuntime().exec(cmd);
	 		  BufferedReader reader = new BufferedReader(new InputStreamReader(
	 		    	     p.getInputStream()));
	 		    	     String line;

	 		    	    while ((line = reader.readLine()) != null) {

	 		    	    	sb.append("<pre>"+line+"</pre>");



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
	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(500);
					updateMessage("2000 milliseconds");
					updateProgress(i + 1, 10);
				}
				return true;
			}
		};
	}

	class Rtc extends Thread {
		ListView<Socket> conHosts;
		Label onH, offH,d,time;

		public Rtc(ListView<Socket> conHosts, Label onH, Label offH, Label date, Label time) {
			this.conHosts = conHosts;
			this.onH = onH;
			this.offH = offH;
			this.d=date;

			this.time=time;
		}

		public void run() {
			while (true) {

				if (conHosts.getItems().size() > 0) {

					conHosts.setCellFactory(new Callback<ListView<Socket>, ListCell<Socket>>() {
						@Override
						public ListCell<Socket> call(ListView<Socket> arg0) {
							int off = 0, on = 0;
							for (int i = 0; i < arg0.getItems().size(); i++) {
								Socket s = arg0.getItems().get(i);
								if (s.isClosed()) {
									off++;
									if (on > 0)
										on--;
									else
										on = 0;
								} else {
									on++;
									if (off == 0)
										off = 0;
									else
										off--;

								}

							}
							onH.setText(String.valueOf(on));
							offH.setText(String.valueOf(off));
							XCell cell = new XCell();
							return cell;
						}
					});
					conHosts.refresh();

				}


			}
		}
	}
	private  String getLocalAddress(){
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while( b.hasMoreElements()){
                for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses())
                    if ( f.getAddress().isSiteLocalAddress()){
                    	String ip=f.getAddress().toString();
                    	ip=ip.substring(1, ip.length());
                    System.out.println(ip);
                    return ip;
                    }

            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
	public Thread getTimeTask(){
		Task <Void> task = new Task<Void>() {
		      @Override public Void call() throws InterruptedException {
		        // "message2" time consuming method (this message will be seen).
		        while(true){
		        	String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		        	updateMessage(timeStamp);
		        	 Thread.sleep(1000);
		        }
		      }
		    };

		    this.time.textProperty().bind(task.messageProperty());
		    Thread thread = new Thread(task);
		    thread.setDaemon(true);
		    return thread;
	}
	public Thread getDateTask(){
		Task <Void> task = new Task<Void>() {
		      @Override public Void call() throws InterruptedException {
		        // "message2" time consuming method (this message will be seen).
		        while(true){
		        	String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		        	updateMessage(timeStamp);
		        	 Thread.sleep(1000);
		        }
		      }
		    };

		    this.date.textProperty().bind(task.messageProperty());
		    Thread thread = new Thread(task);
		    thread.setDaemon(true);

		    return thread;
	}
	public Thread getRealTimeConnectedHosts(ListView<Socket> conHosts, Label onH, Label offH, Label date, Label time){
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				while(true){
				if (conHosts.getItems().size() > 0) {
					conHosts.setCellFactory(new Callback<ListView<Socket>, ListCell<Socket>>() {
						@Override
						public ListCell<Socket> call(ListView<Socket> arg0) {
							int off = 0, on = 0;
							for (int i = 0; i < arg0.getItems().size(); i++) {
								Socket s = arg0.getItems().get(i);
								if (s.isClosed()) {
									off++;
									if (on > 0)
										on--;
									else
										on = 0;
								} else {
									on++;
									if (off == 0)
										off = 0;
									else
										off--;

								}

							}
							onH.setText(String.valueOf(on));
							offH.setText(String.valueOf(off));
							XCell cell = new XCell();
							return cell;
						}
					});
				}
				}

			}
		};
		Thread thread = new Thread(task);
	    thread.setDaemon(true);

	    return thread;
	}


}
