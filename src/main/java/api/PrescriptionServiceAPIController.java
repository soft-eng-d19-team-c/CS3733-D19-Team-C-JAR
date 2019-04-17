package api;

import base.PrescriptionRequestAPI;
import base.EnumScreenTypeAPI;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionServiceAPIController extends Controller implements Initializable {

    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void orderAction(ActionEvent actionEvent){
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIOrder);
    }

    public void provideAction(ActionEvent actionEvent){
        PrescriptionRequestAPI.screenController.setScreen(EnumScreenTypeAPI.APIProvide);

    }

}
