package api;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controller.Controller;
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

    private ObservableList<APIDrug> drugs;

    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientID.clear();
        prescriptionDescription.clear();
//        drugs = APIDrug.getAllDrugs();
//        drugType = new
//        drugType.setOnAction(null);
//        drugType.setItems(drugs);
    }

    public void submitButtonClick(ActionEvent actionEvent) {
        String patientName = patientID.getText();
        String title = drugTitle.getText();
        String instructions = prescriptionDescription.getText();




//        floorsMenu.setItems(differentFloors);
//        floorsMenu.setValue(currentFloor);
//        floorsMenu.setOnAction((event) -> {
//            nodeCircles.clear();
//            currentFloor = floorsMenu.getSelectionModel().getSelectedItem();
//            if (currentFloor.equals("G")) {
//                mapImg.setImage(new Image(String.valueOf(getClass().getResource("/img/00_thegroundfloor.png"))));
//            } else {
//                mapImg.setImage(new Image(String.valueOf(getClass().getResource("/img/" + currentFloor + "_NoIcons.png"))));
//            }
//            nodes = Node.getNodesByFloor(currentFloor);
//            edges = Edge.getEdgesByFloor(currentFloor);
//            drawNodes();
//            if (addEdgeHandler_on) {
//                addPathButtonClick(new ActionEvent());
//            }
//        });


        if (APIPatient.exists(patientName)){
            APIPrescription.addPrescription(title, instructions, patientName);
        } else {
            error.setText("Error: " + patientName + " is not a patient");
        }


//        APIMain.screenController.setScreen(EnumScreenType.APIMain);

    }

}