package Part1;

/*
    Author: Robert Geipel
    Date: 9/19/2020
    Purpose: Controller for main window to manage agencies
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;


public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbAgencyId;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

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
    private Button btnEdit;

    @FXML
    private Button btnSave;

    private Integer i;

    @FXML
    void initialize() {
        assert cmbAgencyId != null : "fx:id=\"cmbAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblAddress != null : "fx:id=\"lblAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCity != null : "fx:id=\"lblCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCountry != null : "fx:id=\"lblCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPhone != null : "fx:id=\"lblPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPostal != null : "fx:id=\"lblPostal\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblProv != null : "fx:id=\"lblProv\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfCity != null : "fx:id=\"tfCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfCountry != null : "fx:id=\"tfCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfFax != null : "fx:id=\"tfFax\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfPhone != null : "fx:id=\"tfPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfPostal != null : "fx:id=\"tfPostal\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfProv != null : "fx:id=\"tfProv\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";

        // combo box list
        ArrayList<Integer> agencyList = new ArrayList<>(10);

        // read agent Ids from the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","Geipelr", "" );
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select AgencyId from agencies");
            while (rs.next())
            {
                // build list in combo box - only one column from data base
                agencyList.add(Integer.valueOf(rs.getString(1)));
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // assign Ids to combo box for display
        ObservableList<Integer> agencies = FXCollections.observableArrayList(agencyList);
        cmbAgencyId.setItems(agencies);

        // assign and implement event handler for combo box
        cmbAgencyId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // get selected Agent Id
                i = cmbAgencyId.getSelectionModel().getSelectedItem();

                // set modal dialog
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEditAgency.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ControllerAddEdit dialogController = fxmlLoader.<ControllerAddEdit>getController();
                dialogController.setI(i);
                dialogController.setMode(false);

                Scene scene = new Scene(parent, 600, 400);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                // sets scene and stage - title will be set in modal dialog controller

                // retrieve agent
                /*try {
                    Connection conn =
                            DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts",
                                    "Geipelr", "Moni!a14" );
                    PreparedStatement stmt =
                            conn.prepareStatement("select AgncyAddress, AgncyCity, " +
                                    "AgncyCity, AgncyCountry, AgncyFax, AgncyPhone, " +
                                    "AgncyPostal, AgncyProv " +
                                    "from agencies where AgencyId = ?");


                    // assign selected Agent Id to prepared statement
                    stmt.setInt(1, i);
                    // get agent from data base
                    ResultSet rs = stmt.executeQuery();
                    // process columns
                    while (rs.next())
                    {
                        for (int j = 1; j <= 8; j++) {
                            switch (j) {
                                // AgtFirstName
                                case 1:
                                    tfAddress.setText(rs.getString(j));
                                    break;
                                // AgtLastName
                                case 2:
                                    tfCity.setText(rs.getString(j));
                                    break;
                                case 3:
                                    // Agent Id
                                    tfCountry.setText(rs.getString(j));
                                    break;
                                // Middle Initial
                                case 4:
                                    tfFax.setText(rs.getString(j));
                                    break;
                                // Email
                                case 5:
                                    tfPhone.setText(rs.getString(j));
                                    break;
                                case 6:
                                    // Business Phone
                                    tfPostal.setText(rs.getString(j));
                                    break;
                                // Agency Id
                                case 7:
                                    tfProv.setText(rs.getString(j));
                                    break;
                            }
                        }
                    }
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
            }
        });

        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // check if agent id selected
                if (i > 0) {
                    // make text fields editable
                    tfAddress.setEditable(true);
                    tfCity.setEditable(true);
                    tfProv.setEditable(true);
                    tfCountry.setEditable(true);
                    tfFax.setEditable(true);
                    tfPhone.setEditable(true);
                    tfPostal.setEditable(true);
                    // enable save-button
                    btnSave.setDisable(false);
                    // disable edit-button
                    btnEdit.setDisable(true);
                }
            }
        });

        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // check if agent id selected
                if (i > 0) {
                    // make text fields editable
                    tfAddress.setEditable(false);
                    tfCity.setEditable(false);
                    tfCountry.setEditable(false);
                    tfProv.setEditable(false);
                    tfPhone.setEditable(false);
                    tfFax.setEditable(false);
                    tfProv.setEditable(false);
                    // disable save-button
                    btnSave.setDisable(true);
                    // enable edit-button
                    btnEdit.setDisable(false);

                    try {
                        Connection conn =
                                DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts",
                                        "Geipelr", "Moni!a14" );
                        PreparedStatement stmt =
                                conn.prepareStatement("UPDATE agencies " +
                                        "SET AgncyAddress = ?, AgncyCity = ?, " +
                                        "AgncyCountry = ?, " +
                                        "AgncyFax = ?, AgncyPhone = ?, " +
                                        "AgncyPostal = ? " +
                                        "WHERE AgencyId = ?");


                        // assign selected Agent Id, first and last name to prepared statement
                        stmt.setString(1, tfAddress.getText());
                        stmt.setString(2, tfCity.getText());
                        stmt.setString(3, tfCountry.getText());
                        stmt.setString(4, tfFax.getText());
                        stmt.setString(5, tfPhone.getText());
                        stmt.setString(6, tfPostal.getText());
                        stmt.setInt(7, i);
                        // update record
                        boolean success = stmt.execute();
                        if (success)
                        {
                            System.out.println("ERROR");
                        }
                        else
                        {
                            System.out.println("UPDATED");
                        }
                        conn.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
