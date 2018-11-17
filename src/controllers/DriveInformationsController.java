package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class DriveInformationsController implements Initializable {
	@FXML
	private Label name, details;
	@FXML
	private ProgressBar level;
	@FXML
	ImageView driveType;

	String nameText;
	double levelValue;
	String imagePath;
	/*public void init(String name, String details, double levelValue, String imagePath){
		this.name.setText("Disque Local C:");
	}*/
	public DriveInformationsController(){

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	public Label getName() {
		return name;
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public Label getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details.setText(details);
	}

	public double getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(double levelValue) {

		this.level.setProgress(levelValue);
		if(levelValue==1)
			this.level.setStyle("-fx-accent: red;");
		else if(levelValue >=0.8F&& levelValue<1)
			this.level.setStyle("-fx-accent: orange;");
		else
			this.level.setStyle("-fx-accent: green;");
	}

	public ImageView getDriveType() {
		return driveType;
	}

	public void setDriveType(String path) {
		Image img = new Image(path);
		this.driveType.setImage(img);
	}

}
