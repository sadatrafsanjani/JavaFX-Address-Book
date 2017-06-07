package com.rafsanjani.address.controller;

import com.rafsanjani.address.PersonList;
import com.rafsanjani.address.entity.General;
import com.rafsanjani.address.model.GeneralModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.IOUtils;

public class MainController implements Initializable, PersonList {

    @FXML
    private TableView<General> tableView;
    @FXML
    private Button editButton, deleteButton;
    @FXML
    private TableColumn<General, String> firstColumn, lastColumn;

    @FXML
    private TextField searchField, titleField, firstNameField,
            lastNameField, organizationField, officialField,
            mobileField, emailField, websiteField, linkedinField, facebookField;
    @FXML
    private TextArea addressArea;
    @FXML
    private ImageView pictureView;
    private GeneralModel model;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new GeneralModel();

        setData();

        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showDetails(newValue);
                });
        tableView.setItems(PERSONLIST);

        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));

        filterData();
    }

    private void setData() {
        if (PERSONLIST.isEmpty()) {
            PERSONLIST.addAll(model.getGenerals());
        }
    }

    private void filterData() {
        FilteredList<General> searchedData = new FilteredList<>(PERSONLIST, e -> true);

        searchField.setOnKeyPressed(e -> {
            tableView.getSelectionModel().clearSelection();
        });
        
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<General> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);
        });
    }

    private File streamToFile(ByteArrayInputStream in) {

        File tempFile = null;
        try {
            tempFile = File.createTempFile("picture", ".jpg");
            tempFile.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(in, out);
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return tempFile;
    }

    private Image renderPicture(byte[] picture) {
        
        ByteArrayInputStream in = new ByteArrayInputStream(picture);
        File file = streamToFile(in);
        IOUtils.closeQuietly(in);
        return new Image(file.toURI().toString());
    }

    private void showDetails(General general) {
        if (general != null) {
            titleField.setText(general.getTitle());
            firstNameField.setText(general.getFirstName());
            lastNameField.setText(general.getLastName());
            organizationField.setText(general.getOrganization());
            addressArea.setText(general.getAddress());
            officialField.setText(general.getPhone().getOfficial());
            mobileField.setText(general.getPhone().getMobile());
            emailField.setText(general.getWeb().getEmail());
            websiteField.setText(general.getWeb().getWebsite());
            linkedinField.setText(general.getWeb().getLinkedin());
            facebookField.setText(general.getWeb().getFacebook());
            pictureView.setImage(renderPicture(general.getPicture()));
        } else {
            titleField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            organizationField.setText("");
            addressArea.setText("");
            officialField.setText("");
            mobileField.setText("");
            emailField.setText("");
            websiteField.setText("");
            linkedinField.setText("");
            facebookField.setText("");
            pictureView.setImage(null);
        }
    }

    @FXML
    public void handleNew(ActionEvent event) throws Exception {

        tableView.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Add.fxml"));
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        stage.setTitle("Add New");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleEdit(ActionEvent event) throws Exception {

        General selectedGeneral = tableView.getSelectionModel().getSelectedItem();
        int selectedPersonId = tableView.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Edit.fxml")));
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });

        stage.setTitle("Edit Person Details");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.setScene(scene);
        stage.show();
        controller.setPerson(selectedGeneral, selectedPersonId);
    }

    @FXML
    public void handleDelete(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Remove Contact from list");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            General selectedGeneral = tableView.getSelectionModel().getSelectedItem();

            model.deleteGeneral(selectedGeneral);
            PERSONLIST.remove(selectedGeneral);
        }

        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void handleMaximize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Platform.exit();
    }
}
