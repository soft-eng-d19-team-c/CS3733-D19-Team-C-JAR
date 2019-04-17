package controller;


import api.APIDrug;
import api.APIPrescription;
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

    private ObservableList<APIDrug> drugs;

    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drugInstructions.setEditable(false);
        getPatientID.clear();
        drugInstructions.clear();
        drugName.getSelectionModel().clearSelection();
//        drugName.getItems.clear();

//        drugType = new
        drugName.setOnAction(null);
        drugName.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                APIDrug drug = (APIDrug) drugName.getValue();
               drugInstructions.setText(drug.getDescription());
                drugInstructions.setDisable(false);
            }
        });

    }

    public void submitButton(ActionEvent actionEvent){
        String patientID = getPatientID.getText();
        drugs = APIPrescription.getAllDrugs(patientID);
        drugName.setItems(drugs);
    }

    public void backAction(ActionEvent actionEvent){
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIMain);
    }


}