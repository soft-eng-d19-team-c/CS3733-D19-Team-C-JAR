package api;


import base.EnumScreenType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class APIProvideController extends Controller implements Initializable {

    @FXML
    JFXTextArea getPatientID;
    @FXML
    JFXButton submit;
    @FXML
    JFXComboBox drugName;
    @FXML
    JFXTextArea drugDescription;




    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submitButtonClick(ActionEvent actionEvent){
        String patientID = getPatientID.getText();
        // @Ryan Query database for all of the patients open prescriptions
        // populate combobox with all of the different open prescriptions
    }

    public void getDrugDesciption(ActionEvent actionEvent){
       // get drug name from combobx
        // populate drugDescription with the drug information
    }

}