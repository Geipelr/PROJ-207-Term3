package Part1;

/*
    Author: Robert Geipel
    Date: 9/19/2020
    Purpose: Controller for modal dialog to add and edit agencies
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerAddEdit  {

    @FXML
    private GridPane gp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblFax;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblPostal;

    @FXML
    private Label lblProv;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfFax;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfPostal;

    @FXML
    private TextField tfProv;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnCancel;

    // Agency
    private Agency agency;

    // used to set Id from main window
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    // stores user action (cancelled/confirmed)
    private boolean cancelled = true;

    // mode (edit/add) ... dialog window is used for editing and adding ... false: edit mode
    private boolean mode;

    public void setMode(boolean mode) {
        this.mode = mode;
    }



    @FXML
    void initialize() {
        assert lblAddress != null : "fx:id=\"lblAddress\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblCity != null : "fx:id=\"lblCity\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblCountry != null : "fx:id=\"lblCountry\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblFax != null : "fx:id=\"lblFax\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblPhone != null : "fx:id=\"lblPhone\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblPostal != null : "fx:id=\"lblPostal\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert lblProv != null : "fx:id=\"lblProv\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfCity != null : "fx:id=\"tfCity\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfCountry != null : "fx:id=\"tfCountry\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfFax != null : "fx:id=\"tfFax\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfPhone != null : "fx:id=\"tfPhone\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfPostal != null : "fx:id=\"tfPostal\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert tfProv != null : "fx:id=\"tfProv\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'addEditAgency.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'addEditAgency.fxml'.";


        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // exit without saving
                Node source = (Node)  mouseEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                // save user action
                cancelled = true;
                stage.close();
            }
        });

        btnConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // exit with saving
                Node source = (Node)  mouseEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                // save user action
                cancelled = false;
                // in adding mode
                if (agency == null)
                    agency = new Agency();
                // Agency Address
                agency.setAgncyAddress(tfAddress.getText());
                // Agency City
                agency.setAgncyCity(tfCity.getText());
                // Agency Country
                agency.setAgncyCountry(tfCountry.getText());
                // Agency fax
                agency.setAgncyFax(tfFax.getText());
                // Agency phone
                agency.setAgncyPhone(tfPhone.getText());
                // Agency postal
                agency.setAgncyPostal(tfPostal.getText());
                // Agency province
                agency.setAgncyProv(tfProv.getText());

                stage.close();
            }
        });

    }

    public void setFields() {
        // only if in edit mode
        if (!mode && agency !=  null)
        {
        // Agency Address
            tfAddress.setText(agency.getAgncyAddress());
        // Agency City
            tfCity.setText(agency.getAgncyCity());
        // Agency Country
            tfCountry.setText(agency.getAgncyCountry());
        // Agency fax
            tfFax.setText(agency.getAgncyFax());
        // Agency phone
            tfPhone.setText(agency.getAgncyPhone());
        // Agency postal
            tfPostal.setText(agency.getAgncyPostal());
        // Agency province
            tfProv.setText(agency.getAgncyProv());
        }
    }


    public Agency getReturn() {
        if (!cancelled) {
            return agency;
        } else return null;
    }
}