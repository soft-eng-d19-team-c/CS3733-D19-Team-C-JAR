package api;

import base.APIMain;
import base.EnumScreenType;
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
        APIMain.screenController.setScreen(EnumScreenType.APIOrder);
    }

    public void provideAction(ActionEvent actionEvent){
        APIMain.screenController.setScreen(EnumScreenType.APIProvide);

    }

}
