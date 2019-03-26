package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.DataTable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DownloadScreen implements Initializable {
    @FXML
    private Button downloadButton;

    private DataTable dataTable;

    public void setDataTable(DataTable dataTable) {
        System.out.println(dataTable);
        this.dataTable = dataTable;
        System.out.println(this.dataTable);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void downloadButtonClick(ActionEvent actionEvent) {
        /*
            TODO
            1. we will need to create a datatable here and get all data to download
            2. trigger download method and return to table page
         */

        System.out.println(this.dataTable);
        this.dataTable.printToCsv();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/emptyTable.fxml"));
            Parent newRoot = loader.load();
            downloadButton.getScene().setRoot(newRoot);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
