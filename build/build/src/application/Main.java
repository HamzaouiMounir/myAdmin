package application;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.ServerManager;

public class Main extends Application {

	ServerManager manager;
	ArrayList<String> test;
	ObservableList<Socket> data = FXCollections.observableArrayList();
	ListView<Socket> listView = new ListView<Socket>();
	Image img;
	TitledPane conHosts;
	static int off=0;
	static int on=0;
	 public Controller controller;
	@Override
	public void start(Stage primaryStage) {
		try {


			final FXMLLoader loader = new FXMLLoader(
				      Main.class.getResource(
				        "/views/Administration.fxml"
				      )
				    );
			BorderPane root = (BorderPane) loader.load();
			 controller = loader.getController();

			primaryStage.setTitle("myAdmin 1.0");
			//primaryStage.initStyle(StageStyle.UNDECORATED);

			primaryStage.setScene(new Scene(root));
			setUserAgentStylesheet(STYLESHEET_CASPIAN);
			//primaryStage.setResizable(false);
			primaryStage.show();
			controller.initializeServer();
			controller.getTimeTask().start();
			controller.getDateTask().start();

			controller.maximize.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Configuration Serveur");
					primaryStage.setMaximized(true);
				}

			});
			primaryStage.setMaximized(true);
			controller.minimize.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Fenêtre minimisée");
						primaryStage.setIconified(true);
				}

			});
			controller.getRealTimeConnectedHosts(controller.getConHosts(), controller.getOnHostCount(), controller.getOffHostCount(),null,null).start();;


		} catch (Exception e) {
			e.printStackTrace();
		}
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		/*try {
			System.out.println(Inet4Address.getLocalHost().getAddress()[1]);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}


	static class XCell extends ListCell<Socket> {
		HBox hbox = new HBox();
		Label label = new Label("(empty)");
		ImageView v = new ImageView();
		Image pc =new Image("/res/pcon.png");
		ImageView v2 = new ImageView(pc);
		// Image img = new Image("/res/pc.png");

		Pane pane = new Pane();

		Button button = new Button("Détails");
		String lastItem;
		Pane space1 = new Pane();
		Pane space2 = new Pane();
		public XCell() {
			super();
			Separator separator1 = new Separator();
			separator1.setOrientation(Orientation.VERTICAL);
			button.setPrefSize(20, 20);
			space1.setPrefWidth(20);
			space2.setPrefWidth(10);
			label.setPadding(new Insets(5, 0, 0, 0));

			hbox.getChildren().addAll(v2,space1, label,space2, pane, v);

			HBox.setHgrow(pane, Priority.ALWAYS);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println(lastItem + " : " + event);
				}
			});

		}

		@Override
		protected void updateItem(Socket item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty) {
				lastItem = null;
				setGraphic(null);
			} else {
				lastItem = item.getInetAddress().getCanonicalHostName();

				try {
					label.setText(item.getInetAddress().getCanonicalHostName()  != null
							? item.getInetAddress().getCanonicalHostName(): "<null>");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println(item.getInetAddress().getCanonicalHostName());

				if (item.isClosed()) {
					this.setHbox(new Image("/res/offline2.png"));

				} else {
					this.setHbox(new Image("/res/online.png"));
				}
				setGraphic(hbox);

			}
		}

		public HBox getHBox() {
			return this.hbox;
		}

		public void setHbox(Image img) {
			v.setImage(img);
			// this.hbox.getChildren().addAll(v, label, pane, button);

			// setGraphic(hbox);
		}
	}


	/*public static void main(String[] args) {
		launch(args);
	}*/


}
