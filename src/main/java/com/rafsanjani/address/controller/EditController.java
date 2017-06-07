package com.rafsanjani.address.controller;

import com.rafsanjani.address.PersonList;
import com.rafsanjani.address.entity.General;
import com.rafsanjani.address.entity.Phone;
import com.rafsanjani.address.entity.Web;
import com.rafsanjani.address.model.GeneralModel;
import com.rafsanjani.address.model.PhoneModel;
import com.rafsanjani.address.model.WebModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController implements Initializable, PersonList {

    @FXML
    private TextField titleField, firstNameField,
            lastNameField, organizationField, officialField,
            mobileField, emailField, websiteField, linkedinField, facebookField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private General general;
    private GeneralModel generalModel;
    private PhoneModel phoneModel;
    private WebModel webModel;
    private int personId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generalModel = new GeneralModel();
        phoneModel = new PhoneModel();
        webModel = new WebModel();
        resetValues();
    }

    public void setPerson(General general, int personId) {
        this.general = general;
        this.personId = personId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Phone phone = new Phone(
                    general.getPhone().getId(),
                    officialField.getText(),
                    mobileField.getText()
            );

            phoneModel.updatePhone(phone);
            
            Web web = new Web(
                    general.getWeb().getId(),
                    emailField.getText(),
                    websiteField.getText(),
                    linkedinField.getText(),
                    facebookField.getText()
            );

            webModel.updateWeb(web);

            General editedGeneral = new General(
                    general.getId(),
                    phone,
                    web,
                    titleField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    organizationField.getText(),
                    addressArea.getText(),
                    general.getPicture()
            );

            generalModel.updateGeneral(editedGeneral);

            PERSONLIST.set(personId, editedGeneral);

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Contact is modified");
            alert.setContentText("Contact is modified successfully");
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
        resetValues();
    }

    private void setData() {
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
    }

    private void resetValues() {
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
    }

    @FXML
    public void handleMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    public void handleExit(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
