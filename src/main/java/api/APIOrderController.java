package api;

import base.EnumScreenType;
import com.jfoenix.controls.JFXTextArea;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class APIOrderController extends Controller implements Initializable {
    @FXML
    private JFXTextArea patientID;
    @FXML private JFXTextArea prescriptionDescription;

    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientID.clear();
        prescriptionDescription.clear();
    }

    public void submitButtonClick(ActionEvent actionEvent) {
        // should also check permissions in the future
//        PrescriptionService prescriptionService = new PrescriptionService(patientID.getText(), Main.user.getUsername(), prescriptionDescription.getText());
//        prescriptionService.insert();
        APIMain.screenController.setScreen(EnumScreenType.APIMain);

        // @Ryan
        // database magic

    }


}