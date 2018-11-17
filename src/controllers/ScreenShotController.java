package controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

public class ScreenShotController implements Initializable{
	public static String ip;
	@FXML
	ImageView screen;
	@FXML
	BorderPane container;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

			loadScreenShot().start();
			}
	public Thread loadScreenShot() {

 		javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<Void>() {

 			@Override
 			protected Void call() throws Exception {
 				// TODO Auto-generated method stub
 				try {
 					Socket s = new Socket(ip, 60010);
 					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

 					// while (true) {
 					out.write("screenshot");
 					out.newLine();
 					out.flush();
 					BufferedImage bf=ImageIO.read(ImageIO.createImageInputStream(s.getInputStream()));
 					//System.out.println(bf);
 					 WritableImage wr = null;
 				        if (bf != null) {
 				            wr = new WritableImage(bf.getWidth(), bf.getHeight());
 				            PixelWriter pw = wr.getPixelWriter();
 				            System.err.println(bf.getWidth()+"x"+bf.getHeight());
 				            for (int x = 0; x < bf.getWidth(); x++) {
 				                for (int y = 0; y < bf.getHeight(); y++) {
 				                    pw.setArgb(x, y, bf.getRGB(x, y));
 				                }
 				            }
 				        }

 				       // ImageView imView = new ImageView(wr);
 				        screen.setImage(wr);
 				        screen.setFitHeight(720);
 				        screen.setFitWidth(1366);

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


