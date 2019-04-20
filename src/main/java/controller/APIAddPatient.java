package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
//    Text text;



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

    public void submitAction(ActionEvent actionEvent){
        String ID = patientIDText.getText();
        String name = patientNameText.getText();
        int age = Integer.parseInt(patientAgeText.getText());
        String sex = patientSexText.getText();

        // check if id already exists
        if (!APIPatient.exists(ID)){
            APIPatient.addPatient(ID, name, age, sex);
        }


    }





}
