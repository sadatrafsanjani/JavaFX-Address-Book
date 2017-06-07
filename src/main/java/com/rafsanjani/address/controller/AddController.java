package com.rafsanjani.address.controller;

import com.rafsanjani.address.PersonList;
import com.rafsanjani.address.entity.General;
import com.rafsanjani.address.entity.Phone;
import com.rafsanjani.address.entity.Web;
import com.rafsanjani.address.model.GeneralModel;
import com.rafsanjani.address.model.PhoneModel;
import com.rafsanjani.address.model.WebModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddController implements Initializable, PersonList {

    @FXML
    private TextField titleField, firstNameField,
            lastNameField, organizationField, officialField,
            mobileField, emailField, websiteField, linkedinField, facebookField;
    @FXML
    private TextArea addressArea;
    @FXML
    private ImageView pictureView;
    @FXML
    private Button saveButton;
    private GeneralModel generalModel;
    private PhoneModel phoneModel;
    private WebModel webModel;
    private FileChooser fileChooser;
    private File selectedFile;
    private byte[] bFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generalModel = new GeneralModel();
        phoneModel = new PhoneModel();
        webModel = new WebModel();
    }

    private void fileChoosing() {

        fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        fileChooser.setTitle("Choose Picture");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
    }

    @FXML
    public void handleBrowse(ActionEvent event) {

        fileChoosing();

        bFile = new byte[(int) selectedFile.length()];
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            fis.read(bFile);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        pictureView.setImage(new Image(selectedFile.toURI().toString()));
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Phone phone = new Phone(
                    officialField.getText().trim(),
                    mobileField.getText().trim()
            );
            
            phoneModel.savePhone(phone);

            Web web = new Web(
                    emailField.getText().trim(),
                    websiteField.getText().trim(),
                    linkedinField.getText().trim(),
                    facebookField.getText().trim()
            );
            
            webModel.saveWeb(web);

            General general = new General(
                    phone,
                    web,
                    titleField.getText().trim(),
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    organizationField.getText().trim(),
                    addressArea.getText().trim(),
                    bFile
            );

            generalModel.saveGeneral(general);

            PERSONLIST.add(general);
            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Contact is added");
            alert.setContentText("Contact is added successfully");
            alert.showAndWait();
        }
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No email id!\n";
        }

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }

        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (organizationField.getText() == null || organizationField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }

        if (addressArea.getText() == null || addressArea.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }

        if (officialField.getText() == null || officialField.getText().length() == 0) {
            errorMessage += "No valid office address!\n";
        }

        if (mobileField.getText() == null || mobileField.getText().length() == 0) {
            errorMessage += "No valid office address!\n";
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No email id!\n";
        }

        if (websiteField.getText() == null || websiteField.getText().length() == 0) {
            errorMessage += "No email id!\n";
        }

        if (facebookField.getText() == null || facebookField.getText().length() == 0) {
            errorMessage += "No facebook id!\n";
        }

        if (linkedinField.getText() == null || linkedinField.getText().length() == 0) {
            errorMessage += "No valid linkedin address!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
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

    @FXML
    public void handleMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
