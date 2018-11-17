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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Connection;
import models.Task;

public class TaskListController implements Initializable {

	@FXML
	public TableView<Task> tasklistTable;
	private final ObservableList<Task> data = FXCollections.observableArrayList();
	@FXML
	private TableColumn imgNameCol, pidCol, sessionNameCol, sessionNumberCol, memUsageCol, statusCol, usernameCol;
	@FXML
	private TextField filterField;
	@FXML
	private Button stopTask;
	public static String ip;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		imgNameCol.setCellValueFactory(new PropertyValueFactory<Task, String>("imageName"));
		pidCol.setCellValueFactory(new PropertyValueFactory<Task, String>("pid"));
		sessionNameCol.setCellValueFactory(new PropertyValueFactory<Task, String>("sessionName"));
		sessionNumberCol.setCellValueFactory(new PropertyValueFactory<Task, String>("sessionNumber"));
		memUsageCol.setCellValueFactory(new PropertyValueFactory<Task, String>("memUsage"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<Task, String>("username"));

		// loadTasklist();
		getRTTasks(tasklistTable, data, "tasklist /V").start();
/*
		try {
			Socket s = new Socket(ip, 60010);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			// while (true) {
			out.write("tasklist /V");
			out.newLine();
			out.flush();

			PrintWriter x;
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line;
			int i = 0;
			while ((line = in.readLine()) != null) {
				System.out.println("$" + in.readLine());
				i++;

				/* LOADING TABLE
				if (i > 3) {
					if (!line.isEmpty()) {
						String[] output = line.split("\\s+");
						String iName = "";
						String pid = "";
						String sName = "";
						String sNumber = "";
						String memUsage = "";
						String status = "";
						String username = "";

						if (!isNumber(output[1])) {

							if (!isNumber(output[2])) {
								System.out.println("o1 number " + output.length);
								iName = output[0] + " " + output[1] + " " + output[2];
								pid = output[3];
								sName = output[4];
								sNumber = output[5];
								memUsage = output[6] + output[7];
								status = output[8];
								if (output.length > 9) {
									if (i == 4)
										username = output[9] + " " + output[10];
									else
										username = output[9];
									// wTtile = output[12];
									System.out.println("here");

								}

							} else {

								iName = output[0] + " " + output[1];
								pid = output[2];
								sName = output[3];
								sNumber = output[4];
								memUsage = output[5] + output[6];
								status = output[7];
								// username =output[8];
								if (output.length > 8) {
									username = output[8];

								}
							}
						} else {
							iName = output[0];
							pid = output[1];
							sName = output[2];
							sNumber = output[3];
							memUsage = output[4] + output[5];
							status = output[6];
							// username =output[7];
							if (output.length > 7) {
								username = output[7];
							}
						}

						data.add(new Task(iName, pid, sName, sNumber, memUsage, status, username));

						System.err.println(username);
					}
					tasklistTable.setItems(data);

				}

			}

			// s.close();

			// x = new PrintWriter(s.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FilteredList<Task> filteredData = new FilteredList<>(data, d -> true);
		filterdata(filteredData);*/
		tasklistTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {

			@Override
			public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
				// TODO Auto-generated method stub
				stopTask.setDisable(false);
			}
		});

		stopTask.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (tasklistTable.getSelectionModel().getSelectedItem() != null) {
					Task task = tasklistTable.getSelectionModel().getSelectedItem();
					int index = tasklistTable.getSelectionModel().getSelectedIndex();

					getRTTasks(tasklistTable, data, "taskkill /pid " + task.getPid()).start();
					// Process p = Runtime.getRuntime().exec("taskkill /pid " +
					// task.getPid());
					data.remove(index);
					tasklistTable.setItems(data);
					filterField.clear();
					FilteredList<Task> filteredData2 = new FilteredList<>(data, d -> true);
					filterdata(filteredData2);
					System.out.println(task.getImageName());
				}

			}

		});
	}

	private void filterdata(FilteredList<Task> filteredData) {

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(task -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (task.getImageName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});
		SortedList<Task> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tasklistTable.comparatorProperty());
		tasklistTable.setItems(sortedData);
	}


	private boolean isNumber(String field) {
		boolean number = false;
		try {
			Integer fieldToInt = Integer.parseInt(field);
			if (field != null) {
				number = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return number;
	}

	public Thread getRTTasks(TableView<Task> tab, ObservableList<Task> data, String cmd) {
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

					PrintWriter x;
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line;
					int i = 0;
					while ((line = in.readLine()) != null) {
						System.out.println("$" + in.readLine());
						i++;

						/* LOADING TABLE **/
						if (i > 3) {
							if (!line.isEmpty()) {
								String[] output = line.split("\\s+");
								String iName = "";
								String pid = "";
								String sName = "";
								String sNumber = "";
								String memUsage = "";
								String status = "";
								String username = "";

								if (!isNumber(output[1])) {

									if (!isNumber(output[2])) {
										System.out.println("o1 number " + output.length);
										iName = output[0] + " " + output[1] + " " + output[2];
										pid = output[3];
										sName = output[4];
										sNumber = output[5];
										memUsage = output[6] + output[7];
										status = output[8];
										if (output.length > 9) {
											if (i == 4)
												username = output[9] + " " + output[10];
											else
												username = output[9];
											// wTtile = output[12];
											System.out.println("here");

										}

									} else {

										iName = output[0] + " " + output[1];
										pid = output[2];
										sName = output[3];
										sNumber = output[4];
										memUsage = output[5] + output[6];
										status = output[7];
										// username =output[8];
										if (output.length > 8) {
											username = output[8];

										}
									}
								} else {
									iName = output[0];
									pid = output[1];
									sName = output[2];
									sNumber = output[3];
									memUsage = output[4] + output[5];
									status = output[6];
									// username =output[7];
									if (output.length > 7) {
										username = output[7];
									}
								}

								data.add(new Task(iName, pid, sName, sNumber, memUsage, status, username));

								System.err.println(username);
							}
							tab.setItems(data);

						}

					}

					// s.close();

					// x = new PrintWriter(s.getOutputStream());

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FilteredList<Task> filteredData = new FilteredList<>(data, d -> true);
				filterdata(filteredData);
				return null;

			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);

		return thread;
	}

}
