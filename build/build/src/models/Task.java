package models;

import javafx.beans.property.SimpleStringProperty;

public class Task {
	private final SimpleStringProperty imageName ;
	private final SimpleStringProperty pid ;
	private final SimpleStringProperty sessionName ;
	private final SimpleStringProperty sessionNumber;
	private final SimpleStringProperty memUsage ;
	private final SimpleStringProperty status ;
	private final SimpleStringProperty username;

	public Task(String imageName, String pid, String sessionName,
			String sessionNumber , String memUsage ,String status, String username ) {

		this.imageName = new SimpleStringProperty(imageName);
		this.pid = new SimpleStringProperty(pid);
		this.sessionName = new SimpleStringProperty(sessionName);
		this.sessionNumber = new SimpleStringProperty(sessionNumber);

		this.memUsage = new SimpleStringProperty(memUsage);
		this.status = new SimpleStringProperty(status);
		this.username = new SimpleStringProperty(username);


	}
	public String getImageName() {
		return imageName.get();
	}
	public String getPid() {
		return pid.get();
	}
	public String getSessionName() {
		return sessionName.get();
	}
	public String getSessionNumber() {
		return sessionNumber.get();
	}
	public String getMemUsage() {
		return memUsage.get();
	}
	public String getStatus() {
		return status.get();
	}
	public String getUsername() {
		return username.get();
	}



}
