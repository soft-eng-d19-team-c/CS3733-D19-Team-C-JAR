package controller;


import javafx.scene.input.KeyEvent;
import model.APIPrescription;
import model.APIPrescription;
import base.EnumScreenTypeAPI;
import base.PrescriptionRequestAPI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class APIProvideController extends Controller implements Initializable {

    @FXML
    JFXTextField getPatientID;
    @FXML
    JFXButton submit;
    @FXML
    JFXComboBox drugName;
    @FXML
    JFXTextArea drugInstructions;
    @FXML
    JFXButton backButton;
    @FXML
    JFXButton resolveButton;

    private ObservableList<APIPrescription> drugs;

    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    APIPrescription selectedPrescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drugInstructions.setEditable(false);
        getPatientID.clear();
        drugInstructions.clear();
        drugName.getSelectionModel().clearSelection();
        resolveButton.setDisable(true);
//        drugName.getItems.clear();

//        drugType = new
        drugName.setOnAction(null);
        drugName.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedPrescription = (APIPrescription) drugName.getValue();
                drugInstructions.setDisable(false);
                if (selectedPrescription != null) {
                    drugInstructions.setText(selectedPrescription.getDescription());
                    resolveButton.setDisable(false);
                }
            }
        });

    }

    public void disableResolve(KeyEvent keyEvent){
        resolveButton.setDisable(true);
    }

    public void checkPatient(KeyEvent keyEvent){
        updateComboBox();
    }

//    public void submitButton(ActionEvent actionEvent) {
//        drugInstructions.clear();
//        String patientID = getPatientID.getText();
//        drugs = APIPrescription.getCurrentPrescriptions(patientID);
//        if (drugs != null) {
//            drugName.setItems(drugs);
//        }
//    }

    public void backAction(ActionEvent actionEvent) {
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIMain);
    }

    public void resolveAction(ActionEvent actionEvent) {
        selectedPrescription.resolve();
        drugInstructions.clear();
//        getPatientID.clear();
        drugName.getSelectionModel().clearSelection();
        resolveButton.setDisable(true);
        updateComboBox();

    }

    public void updateComboBox(){
        drugInstructions.clear();
        String patientID = getPatientID.getText();
        drugs = APIPrescription.getCurrentPrescriptions(patientID);
        if (drugs != null) {
            drugName.setItems(drugs);
        }
    }


}