package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.ProfileManagementFrame;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Administrateur;
import models.GestionnaireAdministrateur;
import models.Task;

public class UsersManagementController implements Initializable {
	@FXML
	public Button add,modify,delete;
	@FXML
	public TableView<Administrateur> adminTable;
	private final ObservableList<Administrateur> data = FXCollections.observableArrayList();
	private final ObservableList<String> comboData = FXCollections.observableArrayList();
	@FXML
	private TableColumn nomCol, prenomCol, loginCol, passwordCol;
	@FXML
	private TextField search;
	@FXML
	ComboBox<String> searchBy;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nomCol.setCellValueFactory(new PropertyValueFactory<Administrateur, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Administrateur, String>("prenom"));
		loginCol.setCellValueFactory(new PropertyValueFactory<Administrateur, String>("login"));
		passwordCol.setCellValueFactory(new PropertyValueFactory<Administrateur, String>("password"));
		GestionnaireAdministrateur managerAdministrateur = new GestionnaireAdministrateur();
		for(Administrateur admin :managerAdministrateur.getAllAdministrateur()){
			data.add(admin);
		}
		//adminTable.setItems(data);
		FilteredList<Administrateur> filteredData = new FilteredList<>(data, d -> true);
		filterdata(filteredData);
		comboData.add("Nom");
		comboData.add("Prénom");
		comboData.add("Login");
		searchBy.setItems(comboData);
		searchBy.getSelectionModel().select(0);
		adminTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Administrateur>() {

			@Override
			public void changed(ObservableValue<? extends Administrateur> observable, Administrateur oldValue, Administrateur newValue) {
				// TODO Auto-generated method stub
				delete.setDisable(false);
				modify.setDisable(false);
			}
		});
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (adminTable.getSelectionModel().getSelectedItem() != null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Suppression");
					alert.setHeaderText("Confirmation");
					alert.setContentText("Voulez vous vraiment supprimer cette administrateur");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Pressed OK.");
					        Administrateur a = adminTable.getSelectionModel().getSelectedItem();
							int index = adminTable.getSelectionModel().getSelectedIndex();

							managerAdministrateur.deleteAdministrateur(a.getId());
							// Process p = Runtime.getRuntime().exec("taskkill /pid " +
							// task.getPid());
							data.remove(index);
							adminTable.setItems(data);
							search.clear();
							FilteredList<Administrateur> filteredData2 = new FilteredList<>(data, d -> true);
							filterdata(filteredData2);
					    }
					});

					//System.out.println(task.getImageName());
				}

			}

		});


	}
	private void filterdata(FilteredList<Administrateur> filteredData) {

		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(admin -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();
				switch(searchBy.getSelectionModel().getSelectedItem()){
				case "Nom":
					if (admin.getNom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					}
					break;
				case "Prénom":
					if (admin.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					}
					break;
				case "Login":
					if (admin.getLogin().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					}
					break;


				}

				return false; // Does not match.
			});
		});
		SortedList<Administrateur> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(adminTable.comparatorProperty());
		adminTable.setItems(sortedData);
	}

}
