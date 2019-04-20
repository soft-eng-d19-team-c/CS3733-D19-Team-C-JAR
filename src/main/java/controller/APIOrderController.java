package controller;

import javafx.scene.text.Text;
import model.APIPrescription;
import model.APIPatient;
import model.APIPrescription;
import base.PrescriptionRequestAPI;
import base.EnumScreenTypeAPI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class APIOrderController extends Controller implements Initializable {
    @FXML
    private JFXTextField patientID;
    @FXML
    private JFXTextArea prescriptionDescription;
    @FXML
    private JFXTextField drugTitle;
    @FXML
    Label error; // used to display errors
    @FXML
    JFXButton backButton;


    private ObservableList<APIPrescription> drugs;

    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientID.clear();
        prescriptionDescription.clear();
        drugTitle.clear();
//        drugs = APIPrescription.getCurrentPrescriptions();
//        drugType = new
//        drugType.setOnAction(null);
//        drugType.setItems(drugs);
    }

    public void submitButtonClick(ActionEvent actionEvent) {
        String patientIDText = patientID.getText();
        String title = drugTitle.getText();
        String instructions = prescriptionDescription.getText();
        error.setText("");
        

        if (patientIDText.equals("") || title.equals("") || instructions.equals("")){
            error.setText("Error: All fields must be entered");
            return;
        }

        if (APIPatient.exists(patientIDText)){
            APIPrescription.addPrescription(title, instructions, patientIDText);
            patientID.clear();
            prescriptionDescription.clear();
            drugTitle.clear();
        } else {
            error.setText("Error: " + patientIDText + " is not a valid ID");
        }


//        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.PrescriptionRequestAPI);

    }

    public void backAction(ActionEvent actionEvent){
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIMain);
    }





}