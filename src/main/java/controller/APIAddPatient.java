package controller;

import base.EnumScreenTypeAPI;
import base.PrescriptionRequestAPI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.APIPatient;

import java.net.URL;
import java.util.ResourceBundle;

public class APIAddPatient extends Controller implements Initializable {

    @FXML
    JFXTextField patientIDText;
    @FXML
    JFXTextField patientNameText;
    @FXML
    JFXTextField patientAgeText;
    @FXML
    JFXTextField patientSexText;
    @FXML
    JFXButton submitButton;
    @FXML
    Label error;


    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientAgeText.clear();
        patientIDText.clear();
        patientNameText.clear();
        patientSexText.clear();
    }

    public void submitAction(ActionEvent actionEvent) {
        error.setText("");
        String ID = patientIDText.getText();
        String name = patientNameText.getText();
        int age = -1;

        String sex = patientSexText.getText();

        if (ID.equals("")) {
            error.setText("ID can't be null");
            return;
        }

        // check if id already exists
        if (APIPatient.exists(ID)) {
            error.setText("ID already exists");
            return;
        }

        if (name.equals("")) {
            error.setText("Name can't be null");
            return;
        }

        try {
            age = Integer.parseInt(patientAgeText.getText());
        } catch (NumberFormatException e) {
            error.setText("Age must be a valid integer");
            return;
        }
        if (age < 0) {
            error.setText("Age must be a positive integer");
            return;
        }

        if (sex.equals("")) {
            error.setText("Sex can't be null");
            return;
        }


        APIPatient.addPatient(ID, name, age, sex);
        patientAgeText.clear();
        patientIDText.clear();
        patientNameText.clear();
        patientSexText.clear();


    }

    public void backAction(ActionEvent actionEvent) {
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIMain);
    }


}
