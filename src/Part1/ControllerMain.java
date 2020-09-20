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

import java.sql.*;


public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbAgencyId;

    @FXML
    private Button btnAdd;

    // agency list in combo box
    ObservableList<Integer> agencies;

    // selected agency Id
    private Integer i;

    // Agency object
    private Agency agency;

    private String passwd = "";

    private EventHandler eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // get selected Agent Id
            i = cmbAgencyId.getSelectionModel().getSelectedItem();

            // retrieve agent
            try {
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "Geipelr", passwd);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");

                PreparedStatement stmt =
                        conn.prepareStatement("select AgncyAddress, AgncyCity, " +
                                "AgncyCountry, AgncyFax, AgncyPhone, " +
                                "AgncyPostal, AgncyProv " +
                                "from agencies where AgencyId = ?");


                // assign selected Agent Id to prepared statement
                stmt.setInt(1, i);
                // get agent from data base
                ResultSet rs = stmt.executeQuery();
                // agency object will be kept in sync with data base
                agency = new Agency();
                // process columns
                while (rs.next()) {
                    for (int j = 1; j <= 8; j++) {
                        switch (j) {
                            // Agency Address
                            case 1:
                                agency.setAgncyAddress(rs.getString(j));
                                break;
                            // Agency City
                            case 2:
                                agency.setAgncyCity(rs.getString(j));
                                break;
                            case 3:
                                // Agency Country
                                agency.setAgncyCountry(rs.getString(j));
                                break;
                            // Agency fax
                            case 4:
                                agency.setAgncyFax(rs.getString(j));
                                break;
                            // Agency phone
                            case 5:
                                agency.setAgncyPhone(rs.getString(j));
                                break;
                            case 6:
                                // Agency postal
                                agency.setAgncyPostal(rs.getString(j));
                                break;
                            // Agency province
                            case 7:
                                agency.setAgncyProv(rs.getString(j));
                                break;
                        }
                    }
                }
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // load fxml-file for modal dialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEditAgency.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // set controller
            ControllerAddEdit dialogController = fxmlLoader.<ControllerAddEdit>getController();
            dialogController.setAgency(agency);
            dialogController.setMode(false);
            dialogController.setFields();

            // set scene and stage and show
            Scene scene = new Scene(parent, 600, 400);
            MyStage myStage = new MyStage();
            myStage.initModality(Modality.APPLICATION_MODAL);
            myStage.setScene(scene);
            myStage.setTitle("Edit Agency");
            Agency agencyNew = myStage.showAndReturn(dialogController);

            if (agencyNew != null) {
                try {
                    //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "Geipelr", passwd);
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");

                    PreparedStatement stmt =
                            conn.prepareStatement("UPDATE agencies " +
                                    "SET AgncyAddress = ?, AgncyCity = ?, " +
                                    "AgncyCountry = ?, " +
                                    "AgncyFax = ?, AgncyPhone = ?, " +
                                    "AgncyPostal = ?, AgncyProv = ? " +
                                    "WHERE AgencyId = ?");


                    // assign selected Agent Id, first and last name to prepared statement
                    stmt.setString(1, agencyNew.getAgncyAddress());
                    stmt.setString(2, agencyNew.getAgncyCity());
                    stmt.setString(3, agencyNew.getAgncyCountry());
                    stmt.setString(4, agencyNew.getAgncyFax());
                    stmt.setString(5, agencyNew.getAgncyPhone());
                    stmt.setString(6, agencyNew.getAgncyPostal());
                    stmt.setString(7, agencyNew.getAgncyProv());
                    stmt.setInt(8, i);
                    // update record
                    boolean success = stmt.execute();
                    if (success) {
                        System.out.println("ERROR");
                    } else {
                        System.out.println("UPDATED");
                        agency = agencyNew;
                    }
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @FXML
    void initialize() {
        assert cmbAgencyId != null : "fx:id=\"cmbAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'mainAgency.fxml'.";

        // combo box list
        ArrayList<Integer> agencyList = new ArrayList<>(10);

        // read agent Ids from the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","harv",
                    "password" );
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","Geipelr",passwd );
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
        agencies = FXCollections.observableArrayList(agencyList);
        cmbAgencyId.setItems(agencies);

        // assign and implement event handler for combo box
        cmbAgencyId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // get selected Agent Id
                i = cmbAgencyId.getSelectionModel().getSelectedItem();

                // retrieve agent
                try {
                    //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "Geipelr", passwd );
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password" );


                    PreparedStatement stmt =
                            conn.prepareStatement("select AgncyAddress, AgncyCity, " +
                                    "AgncyCountry, AgncyFax, AgncyPhone, " +
                                    "AgncyPostal, AgncyProv " +
                                    "from agencies where AgencyId = ?");


                    // assign selected Agent Id to prepared statement
                    stmt.setInt(1, i);
                    // get agent from data base
                    ResultSet rs = stmt.executeQuery();
                    // agency object will be kept in sync with data base
                    agency = new Agency();
                    // process columns
                    while (rs.next())
                    {
                        for (int j = 1; j <= 8; j++) {
                            switch (j) {
                                // Agency Address
                                case 1:
                                    agency.setAgncyAddress(rs.getString(j));
                                    break;
                                // Agency City
                                case 2:
                                    agency.setAgncyCity(rs.getString(j));
                                    break;
                                case 3:
                                    // Agency Country
                                    agency.setAgncyCountry(rs.getString(j));
                                    break;
                                // Agency fax
                                case 4:
                                    agency.setAgncyFax(rs.getString(j));
                                    break;
                                // Agency phone
                                case 5:
                                    agency.setAgncyPhone(rs.getString(j));
                                    break;
                                case 6:
                                    // Agency postal
                                    agency.setAgncyPostal(rs.getString(j));
                                    break;
                                // Agency province
                                case 7:
                                    agency.setAgncyProv(rs.getString(j));
                                    break;
                            }
                        }
                    }
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // load fxml-file for modal dialog
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEditAgency.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // set controller
                ControllerAddEdit dialogController = fxmlLoader.<ControllerAddEdit>getController();
                dialogController.setAgency(agency);
                dialogController.setMode(false);
                dialogController.setFields();

                // set scene and stage and show
                Scene scene = new Scene(parent, 600, 400);
                MyStage myStage = new MyStage();
                myStage.initModality(Modality.APPLICATION_MODAL);
                myStage.setScene(scene);
                myStage.setTitle("Edit Agency");
                Agency agencyNew = myStage.showAndReturn(dialogController);

                if (agencyNew != null)
                {
                    try {
                        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "Geipelr", passwd );
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password" );

                        PreparedStatement stmt =
                                conn.prepareStatement("UPDATE agencies " +
                                        "SET AgncyAddress = ?, AgncyCity = ?, " +
                                        "AgncyCountry = ?, " +
                                        "AgncyFax = ?, AgncyPhone = ?, " +
                                        "AgncyPostal = ?, AgncyProv = ? " +
                                        "WHERE AgencyId = ?");


                        // assign selected Agent Id, first and last name to prepared statement
                        stmt.setString(1, agencyNew.getAgncyAddress());
                        stmt.setString(2, agencyNew.getAgncyCity());
                        stmt.setString(3, agencyNew.getAgncyCountry());
                        stmt.setString(4, agencyNew.getAgncyFax());
                        stmt.setString(5, agencyNew.getAgncyPhone());
                        stmt.setString(6, agencyNew.getAgncyPostal());
                        stmt.setString(7, agencyNew.getAgncyProv());
                        stmt.setInt(8, i);
                        // update record
                        boolean success = stmt.execute();
                        if (success)
                        {
                            System.out.println("ERROR");
                        }
                        else
                        {
                            System.out.println("UPDATED");
                            agency = agencyNew;
                        }
                        conn.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // load fxml-file for modal dialog
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEditAgency.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // set controller
                ControllerAddEdit dialogController = fxmlLoader.<ControllerAddEdit>getController();
                dialogController.setMode(true);

                // set scene and stage and show
                Scene scene = new Scene(parent, 600, 400);
                MyStage myStage = new MyStage();
                myStage.initModality(Modality.APPLICATION_MODAL);
                myStage.setScene(scene);
                myStage.setTitle("Add Agency");
                Agency agencyNew = myStage.showAndReturn(dialogController);

                if (agencyNew != null)
                {
                    try {
                        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "Geipelr", passwd );
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password" );

                        PreparedStatement stmt =
                                conn.prepareStatement("INSERT INTO `agencies` (`AgncyAddress`, `AgncyCity`, " +
                                                          "`AgncyCountry`, `AgncyFax`, `AgncyPhone`, " +
                                                          "`AgncyPostal`, `AgncyProv`) " +
                                                          "VALUES (?, ?, ?, " +
                                                          "? ,? ,? ,?)");


                        // assign selected Agent Id, first and last name to prepared statement
                        stmt.setString(1, agencyNew.getAgncyAddress());
                        stmt.setString(2, agencyNew.getAgncyCity());
                        stmt.setString(3, agencyNew.getAgncyCountry());
                        stmt.setString(4, agencyNew.getAgncyFax());
                        stmt.setString(5, agencyNew.getAgncyPhone());
                        stmt.setString(6, agencyNew.getAgncyPostal());
                        stmt.setString(7, agencyNew.getAgncyProv());
                        // update record
                        boolean success = stmt.execute();
                        if (success)
                        {
                            System.out.println("ERROR");
                        }
                        else
                        {
                            System.out.println("UPDATED");

                            ResultSet rs = stmt.executeQuery("select AgencyId from agencies");
                            agencyList.clear();
                            while (rs.next())
                            {
                                // build list in combo box - only one column from data base
                                agencyList.add(Integer.valueOf(rs.getString(1)));
                            }
                            // assign Ids to combo box for display
                        }
                        conn.close();
                        agencies = FXCollections.observableArrayList(agencyList);
                        cmbAgencyId.setOnAction(null);
                        cmbAgencyId.setItems(agencies);
                        cmbAgencyId.setOnAction(eventHandler);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
