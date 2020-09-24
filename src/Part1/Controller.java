//Author: Gustavo Lourenco Moises
//Date 9/20/2020
//Thread Project
//Workshop 6

package Part1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

//-----------------------------------------------------------------------------------------
public class Controller {

    //Variable and Objects for Customer //By Suvanjan Shrestha
    int agentIndex;

    @FXML
    private ComboBox<Customer> cbCustomers;

    @FXML
    private Button btnAddCust;

    @FXML
    private Button btnEditCust;

    @FXML
    private Button btnSaveCust;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField tfCusId;

    @FXML
    private TextField tfCustFname;

    @FXML
    private TextField tfCustLname;

    @FXML
    private TextField tfCustAddress;

    @FXML
    private TextField tfCustCity;

    @FXML
    private TextField tfCustProv;

    @FXML
    private TextField tfCustPostal;

    @FXML
    private TextField tfCustCountry;

    @FXML
    private TextField tfCustHomePhone;

    @FXML
    private TextField tfCustBusPhone;

    @FXML
    private TextField tfCustEmail;

    @FXML
    private ComboBox<Agent> cbAgentId;

    // -------------------------------------------------------------------------------

    //Supplier Variables
    int indexSupp;
    int indexAffi;
    boolean newSupplier;
    boolean newContact;


    //Agency Variables
    // agency list in combo box
    ObservableList<Integer> agencies;

    // selected agency Id
    private Integer i;

    // Agency object
    private Agency agency;

    private String passwd = "Moni!a14";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Tabs
    @FXML
    private Tab tabAgency;

    @FXML
    private Tab tabAgent;

    @FXML
    private Tab tabSupplier;

    @FXML
    private Tab tabCustomer;

    //Agency Buttons and Textfields
    @FXML
    private ComboBox<Integer> cmbAgencyId;

    @FXML
    private Button btnAdd;



    //Supplier Buttons and TextFields
    @FXML
    private Button btnAddSupplier;

    @FXML
    private ComboBox<Supplier> cmbSupplier;

    @FXML
    private TextField tfSupplierId;

    @FXML
    private TextField tfCompany;

    @FXML
    private Button btnEditSupplier;

    @FXML
    private Button btnSaveSupplier;

    @FXML
    private Button btnAddContact;

    @FXML
    private ComboBox<SupplierContact> cmbContact;

    @FXML
    private TextField tfContactId;

    @FXML
    private TextField tfConFirstName;

    @FXML
    private TextField tfConLastName;

    @FXML
    private TextField tfConCompany;

    @FXML
    private TextField tfConAddress;

    @FXML
    private TextField tfConCity;

    @FXML
    private TextField tfConProvince;

    @FXML
    private TextField tfConPostal;

    @FXML
    private TextField tfConCountry;

    @FXML
    private ComboBox<Supplier> cmbConSupplier;

    @FXML
    private TextField tfConPhone;

    @FXML
    private TextField tfConFax;

    @FXML
    private TextField tfConEmail;

    @FXML
    private TextField tfConURL;

    @FXML
    private ComboBox<Affiliation> cmbAffiliation;

    @FXML
    private Button btnEditContact;

    @FXML
    private Button btnSaveContact;
//-------------------------------------------------------------------------------------------------------

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

//---------------------------------------------------------------------------------------------------------


    @FXML
    void initialize() {
        //Supplier
        assert tabAgency != null : "fx:id=\"tabAgency\" was not injected: check your FXML file 'sample.fxml'.";
        assert tabAgent != null : "fx:id=\"tabAgent\" was not injected: check your FXML file 'sample.fxml'.";
        assert tabSupplier != null : "fx:id=\"tabSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAddSupplier != null : "fx:id=\"btnAddSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert cmbSupplier != null : "fx:id=\"cmbSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAddContact != null : "fx:id=\"btnAddContact\" was not injected: check your FXML file 'sample.fxml'.";
        assert cmbContact != null : "fx:id=\"cmbContact\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfSupplierId != null : "fx:id=\"tfSupplierId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfCompany != null : "fx:id=\"tfCompany\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEditSupplier != null : "fx:id=\"btnEditSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSaveSupplier != null : "fx:id=\"btnSaveSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfContactId != null : "fx:id=\"tfContactId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConFirstName != null : "fx:id=\"tfConFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConLastName != null : "fx:id=\"tfConLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConAddress != null : "fx:id=\"tfConAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConCity != null : "fx:id=\"tfConCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConProvince != null : "fx:id=\"tfConProvince\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConPostal != null : "fx:id=\"tfConPostal\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConCountry != null : "fx:id=\"tfConCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert cmbConSupplier != null : "fx:id=\"cmbConSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConCompany != null : "fx:id=\"tfConCompany\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConPhone != null : "fx:id=\"tfConPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConFax != null : "fx:id=\"tfConFax\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConEmail != null : "fx:id=\"tfConEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfConURL != null : "fx:id=\"tfConURL\" was not injected: check your FXML file 'sample.fxml'.";
        assert cmbAffiliation != null : "fx:id=\"cmbAffiliation\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEditContact != null : "fx:id=\"btnEditContact\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSaveContact != null : "fx:id=\"btnSaveContact\" was not injected: check your FXML file 'sample.fxml'.";
        assert tabCustomer != null : "fx:id=\"tabCustomer\" was not injected: check your FXML file 'sample.fxml'.";

        //agency
        assert cmbAgencyId != null : "fx:id=\"cmbAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'mainAgency.fxml'.";

        //Customer  //By Suvanjan Shrestha
        assert cbCustomers != null : "fx:id=\"cbCustomers\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert btnAddCust != null : "fx:id=\"btnAddCust\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert btnEditCust != null : "fx:id=\"btnEditCust\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert btnSaveCust != null : "fx:id=\"btnSaveCust\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert btnDone != null : "fx:id=\"btnDone\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCusId != null : "fx:id=\"tfCusId\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustFname != null : "fx:id=\"tfCustFname\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustLname != null : "fx:id=\"tfCustLname\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustAddress != null : "fx:id=\"tfCustAddress\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustCity != null : "fx:id=\"tfCustCity\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustProv != null : "fx:id=\"tfCustProv\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustPostal != null : "fx:id=\"tfCustPostal\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustCountry != null : "fx:id=\"tfCustCountry\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustHomePhone != null : "fx:id=\"tfCustHomePhone\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustBusPhone != null : "fx:id=\"tfCustBusPhone\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert tfCustEmail != null : "fx:id=\"tfCustEmail\" was not injected: check your FXML file 'custdisplay.fxml'.";
        assert cbAgentId != null : "fx:id=\"cbAgentId\" was not injected: check your FXML file 'custdisplay.fxml'.";

        // ---------------------------------------------------------------------------------------------------
        //Buttons Visibility
        btnSaveSupplier.setVisible(false);
        btnEditSupplier.setVisible(false);
        btnSaveContact.setVisible(false);
        btnEditContact.setVisible(false);

        //Do not edit fields
        //Supplier
        tfSupplierId.setDisable(true);
        tfCompany.setDisable(true);
        //Contact
        tfContactId.setDisable(true);
        disableContactTextFields(true);

        //Supplier Query
        Connection conn = connectDB();
        ObservableList<Supplier> supplierOList = FXCollections.observableArrayList();
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.add(new Supplier(0,""));
        try {
            Statement stmt = conn.createStatement();
            String sql= "select * from suppliers";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                supplierList.add(new Supplier(rs.getInt(1),rs.getString(2)));
                supplierOList.add(new Supplier(rs.getInt(1),rs.getString(2)));
            }
            cmbSupplier.setItems(supplierOList);
            cmbConSupplier.setItems(supplierList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Supplier Contacts Query
        ObservableList<SupplierContact> contactList = FXCollections.observableArrayList();
        try {
            Statement stmt = conn.createStatement();
            String sql= "select * from suppliercontacts";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                contactList.add(new SupplierContact(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15)));
            }
            cmbContact.setItems(contactList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Affiliation Query
        ObservableList<Affiliation> affiliationList = FXCollections.observableArrayList();
        affiliationList.add(new Affiliation("","",""));
        try {
            Statement stmt = conn.createStatement();
            String sql= "select * from affiliations";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                affiliationList.add(new Affiliation(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            cmbAffiliation.setItems(affiliationList);
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Supplier Selection
        cmbSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
                //Not a new Supplier
                newSupplier=false;
                //Show Supplier Data
                tfSupplierId.setText(t1.getSupplierId() + "");
                tfCompany.setText(t1.getSupplierName());

                //Buttons Configuration
                btnSaveSupplier.setDisable(true);
                btnEditSupplier.setDisable(false);
                btnSaveSupplier.setVisible(true);
                btnEditSupplier.setVisible(true);

                //Do not edit Supplier field
                tfCompany.setDisable(true);
            }
        });

        //Contact Selection
        cmbContact.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierContact>() {
            @Override
            public void changed(ObservableValue<? extends SupplierContact> observableValue, SupplierContact supplierContact, SupplierContact t1) {
                //Not a new Supplier Contact
                newContact=false;
                //Show Supplier Contact Data
                tfContactId.setText(t1.getSupConId()+"");
                indexSupp=0;
                for (Supplier s : supplierList)
                {
                    if (s.getSupplierId()==t1.getSupConSupplierId()) break;
                    else indexSupp++;
                }
                if (indexSupp==supplierList.size())
                {
                    cmbConSupplier.getSelectionModel().select(0);
                }
                else cmbConSupplier.getSelectionModel().select(indexSupp);
                tfConFirstName.setText(t1.getSupConFirstName());
                tfConLastName.setText(t1.getSupConLastName());
                tfConAddress.setText(t1.getSupConAddress());
                tfConCity.setText(t1.getSupConCity());
                tfConProvince.setText(t1.getSupConProv());
                tfConPostal.setText(t1.getSupConPostal());
                tfConCountry.setText(t1.getSupConCountry());
                tfConCompany.setText(t1.getSupConCompany());
                tfConPhone.setText(t1.getSupConPhone());
                tfConFax.setText(t1.getSupConFax());
                tfConEmail.setText(t1.getSupConEmail());
                tfConURL.setText(t1.getSupConURL());
                indexAffi=0;
                for (Affiliation a : affiliationList)
                {
                    if (a.getAffiliationId().equals(t1.getSupConAffiliation())) break;
                    else indexAffi++;
                }
                if (indexAffi==affiliationList.size())
                {
                    cmbAffiliation.getSelectionModel().select(0);
                }
                else cmbAffiliation.getSelectionModel().select(indexAffi);

                //Buttons Configuration
                btnSaveContact.setDisable(true);
                btnEditContact.setDisable(false);
                btnSaveContact.setVisible(true);
                btnEditContact.setVisible(true);

                //Do not edit Contact fields
                disableContactTextFields(true);
            }
        });
        //Edit Supplier
        btnEditSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                    //Buttons Configuration
                    btnSaveSupplier.setDisable(false);
                    btnEditSupplier.setDisable(true);

                    //Edit Supplier fields
                    tfCompany.setDisable(false);
            }
        });

        //Edit Contact
        btnEditContact.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Buttons Configuration
                btnSaveContact.setDisable(false);
                btnEditContact.setDisable(true);

                //Edit Supplier Contact fields
                disableContactTextFields(false);
            }
        });

        //Save Supplier
        btnSaveSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Supplier newSup = new Supplier();
                Connection conn = connectDB();
                String sql;
                if (newSupplier)
                {

                    sql = "INSERT INTO `suppliers` (`SupName`,`SupplierId`) VALUES (?,?)";
                }

                else
                    sql = "UPDATE `suppliers` SET `SupName`=? WHERE `SupplierId`=?";

                try {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1,tfCompany.getText());
                    if (!newSupplier)
                        stmt.setInt(2,Integer.parseInt(tfSupplierId.getText()));
                    else
                    {
                        Connection conn1 = connectDB();
                        try {
                            //Define next Supplier ID
                            Statement stmt1 = conn1.createStatement();
                            String sql1= "SELECT `SupplierId` FROM `suppliers` order by (`SupplierId`) DESC LIMIT 1";
                            ResultSet rs1 = stmt1.executeQuery(sql1);
                            while (rs1.next())
                            {
                                Random rand = new Random();
                                newSup = new Supplier(rs1.getInt(1)+rand.nextInt(10) ,tfCompany.getText());
                            }
                            stmt.setInt(2,newSup.getSupplierId());

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        conn1.close();
                    }
                    int numRows =stmt.executeUpdate();
                    if (numRows==0)
                    {
                        if (newSupplier)
                        {
                            System.out.println("Supplier's Insertion Failed!!!!");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier's Insertion Failed!!!!");
                        }
                        else
                        {
                            System.out.println("Supplier's Update Failed!!!!");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier's Update Failed!!!!");
                        }

                    }
                    else
                    {
                        if (newSupplier)
                        {
                            System.out.println("Supplier Successfully Inserted.");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier Successfully Inserted.");
                            newSupplier=false;
                            supplierList.add(newSup);
                            supplierOList.add(newSup);
                            tfCompany.setText(newSup.getSupplierName());
                            tfSupplierId.setText(newSup.getSupplierId()+"");

                        }
                        else
                            {
                                System.out.println("Supplier Successfully Updated");
                                JOptionPane.showMessageDialog(new JFrame(),"Supplier Successfully Updated.");
                                //Update Supplier List
                                for (Supplier s : supplierOList)
                                {
                                    if (s.getSupplierId()==Integer.parseInt(tfSupplierId.getText()))
                                        s.setSupplierName(tfCompany.getText());
                                }
                                for (Supplier s : supplierList)
                                {
                                    if (s.getSupplierId()==Integer.parseInt(tfSupplierId.getText()))
                                        s.setSupplierName(tfCompany.getText());
                                }
                            }

                    }
                    conn.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Buttons Configuration
                btnSaveSupplier.setDisable(true);
                btnEditSupplier.setDisable(false);

                //Do not edit Agent's fields
                tfCompany.setDisable(true);
            }
        });

        //Save Supplier
        btnSaveContact.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Connection conn = connectDB();
                String sql;
                if (newContact)
                    sql = "INSERT INTO  `suppliercontacts` (`SupConFirstName`,`SupConLastName`,`SupConCompany`,`SupConAddress`,`SupConCity`,`SupConProv`,`SupConPostal`,`SupConCountry`,`SupConBusPhone`,`SupConFax`,`SupConEmail`, `SupConURL`,`AffiliationId`,`SupplierId`,`SupplierContactId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                else
                    sql = "UPDATE `suppliercontacts` SET `SupConFirstName`=?,`SupConLastName`=?,`SupConCompany`=?,`SupConAddress`=?,`SupConCity`=?,`SupConProv`=?,`SupConPostal`=?,`SupConCountry`=?,`SupConBusPhone`=?,`SupConFax`=?,`SupConEmail`=?, `SupConURL`=?,`AffiliationId`=?,`SupplierId`=? WHERE `SupplierContactId`=?";


                try {
                    SupplierContact newSupplierContact = new SupplierContact();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1,tfConFirstName.getText());
                    newSupplierContact.setSupConFirstName(tfConFirstName.getText());
                    stmt.setString(2,tfConLastName.getText());
                    newSupplierContact.setSupConLastName(tfConLastName.getText());
                    stmt.setString(3,tfConCompany.getText());
                    newSupplierContact.setSupConCompany(tfConCompany.getText());
                    stmt.setString(4,tfConAddress.getText());
                    newSupplierContact.setSupConAddress(tfConAddress.getText());
                    stmt.setString(5,tfConCity.getText());
                    newSupplierContact.setSupConCity(tfConCity.getText());
                    stmt.setString(6,tfConProvince.getText());
                    newSupplierContact.setSupConProv(tfConProvince.getText());
                    stmt.setString(7,tfConPostal.getText());
                    newSupplierContact.setSupConPostal(tfConPostal.getText());
                    stmt.setString(8,tfConCountry.getText());
                    newSupplierContact.setSupConCountry(tfConCountry.getText());
                    stmt.setString(9,tfConPhone.getText());
                    newSupplierContact.setSupConPhone(tfConPhone.getText());
                    stmt.setString(10,tfConFax.getText());
                    newSupplierContact.setSupConFax(tfConFax.getText());
                    stmt.setString(11,tfConEmail.getText());
                    newSupplierContact.setSupConEmail(tfConEmail.getText());
                    stmt.setString(12,tfConURL.getText());
                    newSupplierContact.setSupConURL(tfConURL.getText());

                    if (cmbAffiliation.getSelectionModel().getSelectedIndex() >=0)
                        if (cmbAffiliation.getSelectionModel().getSelectedIndex() ==0)
                        {
                            stmt.setString(13,null);
                            newSupplierContact.setSupConAffiliation(null);
                        }
                        else
                        {
                            stmt.setString(13,cmbAffiliation.getSelectionModel().getSelectedItem().getAffiliationId());
                            newSupplierContact.setSupConAffiliation(cmbAffiliation.getSelectionModel().getSelectedItem().getAffiliationId());
                        }
                    else
                    {
                        stmt.setString(13,null);
                        newSupplierContact.setSupConAffiliation(null);
                    }


                    if (cmbConSupplier.getSelectionModel().getSelectedIndex()>=0)
                        if (cmbConSupplier.getSelectionModel().getSelectedIndex()==0)
                        {
                            stmt.setString(14,null);
                            newSupplierContact.setSupConSupplierId(0);
                        }

                        else
                        {
                            stmt.setInt(14,cmbConSupplier.getSelectionModel().getSelectedItem().getSupplierId());
                            newSupplierContact.setSupConSupplierId(cmbConSupplier.getSelectionModel().getSelectedItem().getSupplierId());
                        }

                    else
                    {
                        stmt.setString(14,null);
                        newSupplierContact.setSupConSupplierId(0);
                    }

                    if (!newContact)
                    {
                        stmt.setInt(15,Integer.parseInt(tfContactId.getText()));
                    }
                    else
                    {
                        //Find next Supplier Contact ID
                        Connection conn1 = connectDB();
                        try {
                            Statement stmt1 = conn1.createStatement();
                            String sql1= "SELECT `SupplierContactId` FROM `suppliercontacts` ORDER BY `SupplierContactId`DESC LIMIT 1";
                            ResultSet rs1 = stmt1.executeQuery(sql1);
                            while (rs1.next())
                            {
                                int test =rs1.getInt(1)+1;
                                newSupplierContact.setSupConId(rs1.getInt(1)+1);
                                stmt.setInt(15,newSupplierContact.getSupConId());
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        conn1.close();
                    }

                    int numRows =stmt.executeUpdate();
                    if (numRows==0)
                    {
                        if (newContact)
                        {
                            System.out.println("Supplier Contact's Insertion Failed!!!!");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier Contact's Insertion Failed!!!!");
                        }
                        else
                        {
                            System.out.println("Supplier Contact's Update Failed!!!!");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier Contact's Update Failed!!!!");
                        }
                    }
                    else
                    {
                        if (newContact)
                        {
                            System.out.println("Inserted");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier Contact Successfully Inserted.");
                            newContact=false;
                            contactList.add(newSupplierContact);
                            tfContactId.setText(newSupplierContact.getSupConId()+"");
                        }
                        else
                        {
                            System.out.println("Updated");
                            JOptionPane.showMessageDialog(new JFrame(),"Supplier Contact Successfully Updated.");
                            //Update contact List
                            for (SupplierContact c : contactList)
                            {
                                if (c.getSupConId()==Integer.parseInt(tfContactId.getText()))
                                {
                                    c.setSupConFirstName(tfConFirstName.getText());
                                    c.setSupConLastName(tfConLastName.getText());
                                    c.setSupConAddress(tfConAddress.getText());
                                    c.setSupConCity(tfConCity.getText());
                                    c.setSupConProv(tfConProvince.getText());
                                    c.setSupConPostal(tfConPostal.getText());
                                    c.setSupConCountry(tfConCountry.getText());
                                    c.setSupConCompany(tfConCompany.getText());
                                    c.setSupConPhone(tfConPhone.getText());
                                    c.setSupConFax(tfConFax.getText());
                                    c.setSupConEmail(tfConEmail.getText());
                                    c.setSupConURL(tfConURL.getText());
                                    if (cmbAffiliation.getSelectionModel().getSelectedIndex() >=0)
                                        if (cmbAffiliation.getSelectionModel().getSelectedIndex() ==0)
                                            c.setSupConAffiliation(null);
                                        else c.setSupConAffiliation(cmbAffiliation.getSelectionModel().getSelectedItem().getAffiliationId());
                                    else
                                        c.setSupConAffiliation(null);

                                    if (cmbConSupplier.getSelectionModel().getSelectedIndex()>=0)
                                        if (cmbConSupplier.getSelectionModel().getSelectedIndex()==0)
                                            c.setSupConSupplierId(0);
                                        else c.setSupConSupplierId(cmbConSupplier.getSelectionModel().getSelectedItem().getSupplierId());
                                    else c.setSupConSupplierId(0);
                                }

                            }
                        }
                    }
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Buttons Configuration
                btnSaveContact.setDisable(true);
                btnEditContact.setDisable(false);

                //Do not edit Agent's fields
                disableContactTextFields(true);
            }
        });
        //Add Supplier
        btnAddSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //New Supplier
                newSupplier=true;
                tfSupplierId.setText("*");
                tfCompany.setText("");

                //Buttons Configuration
                btnSaveSupplier.setDisable(false);
                btnEditSupplier.setDisable(true);
                btnSaveSupplier.setVisible(true);
                btnEditSupplier.setVisible(true);

                //Do not edit Supplier fields
                tfCompany.setDisable(false);

            }
        });
        btnAddContact.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //New Contact
                newContact=true;
                tfContactId.setText("*");
                cmbConSupplier.getSelectionModel().select(0);
                tfConFirstName.setText("");
                tfConLastName.setText("");
                tfConAddress.setText("");
                tfConCity.setText("");
                tfConProvince.setText("");
                tfConPostal.setText("");
                tfConCountry.setText("");
                tfConCompany.setText("");
                tfConPhone.setText("");
                tfConFax.setText("");
                tfConEmail.setText("");
                tfConURL.setText("");
                cmbAffiliation.getSelectionModel().select(0);


                //Buttons Configuration
                btnSaveContact.setDisable(false);
                btnEditContact.setDisable(true);
                btnSaveContact.setVisible(true);
                btnEditContact.setVisible(true);

                //Do not edit Agent's fields
                disableContactTextFields(false);
            }
        });

        //Agency
        // combo box list
        ArrayList<Integer> agencyList = new ArrayList<>(10);

        // read agent Ids from the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","harv",
                    "password" );
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","Geipelr",passwd );
            Statement stmt = conn2.createStatement();
            ResultSet rs = stmt.executeQuery("select AgencyId from agencies");
            while (rs.next())
            {
                // build list in combo box - only one column from data base
                agencyList.add(Integer.valueOf(rs.getString(1)));
            }
            conn2.close();

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

        // ------------------------------------------------------
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

        // -----------------------------------------------------------------------------------------
        //Code for Customer //By Suvanjan Shrestha
        //set visibility and disable text field
        disableTextfield();
        btnDone.setVisible(false);
        btnSaveCust.setVisible(false);
        btnEditCust.setVisible(false);
        btnCancel.setVisible(false);

        //Creating connection and list of customers     //By Suvanjan Shrestha
        Connection cusConnection = connectDB();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        try {
            Statement statement = cusConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from customers order by CustFirstName");
            while (rs.next()){
                list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            cbCustomers.setItems(list);
            cusConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //---------------------------------------------------------

        //Populate AgentsId combobox (Customer) //By Suvanjan Shrestha
        Connection agentConn = connectDB();
        ObservableList<Agent> agentsList = FXCollections.observableArrayList();
        try {
            Statement statement = agentConn.createStatement();
            String sql = "SELECT * FROM `agents`";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                agentsList.add(new Agent(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            cbAgentId.setItems(agentsList);
            agentConn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //---------------------------------------------------------

        //Populate the form (Customer)  //By Suvanjan Shrestha
        cbCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) -> {

            if (cbCustomers.getSelectionModel().getSelectedIndex()>=0) {
                tfCusId.setText(t1.getCustomerId() + "");
                tfCustFname.setText(t1.getCustFirstName());
                tfCustLname.setText(t1.getCustLastName());
                tfCustAddress.setText(t1.getCustAddress());
                tfCustCity.setText(t1.getCustCity());
                tfCustProv.setText(t1.getCustProv());
                tfCustPostal.setText(t1.getCustPostal());
                tfCustCountry.setText(t1.getCustCountry());
                tfCustHomePhone.setText(t1.getCustHomePhone());
                tfCustBusPhone.setText(t1.getCustBusPhone());
                tfCustEmail.setText(t1.getCustEmail());

                agentIndex = 0;
                for (Agent agent : agentsList){
                    if (agent.getAgentId() == t1.getAgentId()) break;
                    agentIndex++;
                }
                cbAgentId.getSelectionModel().select(agentIndex);

                btnEditCust.setVisible(true);
            } else {
                clearTexts();
            }
        });
        //---------------------------------------------------------

        //Edit button function (Customer)   //By Suvanjan Shrestha
        btnEditCust.setOnMouseClicked(mouseEvent -> {
            enableTextfield();
            btnSaveCust.setVisible(true);
            btnEditCust.setDisable(true);
            btnAddCust.setDisable(false);
        });
        //---------------------------------------------------------

        //Add button function (Customer)    //By Suvanjan Shrestha
        btnAddCust.setOnMouseClicked(mouseEvent -> {
            btnDone.setVisible(true);
            btnSaveCust.setVisible(false);
            btnEditCust.setVisible(false);
            btnCancel.setVisible(true);
            cbCustomers.setDisable(true);
            enableTextfield();
            clearTexts();
            tfCustFname.requestFocus();
        });

        //---------------------------------------------------------

        // Done button function for saving new customer     //By Suvanjan Shrestha
        btnDone.setOnMouseClicked(mouseEvent -> {
            Connection insertConn = connectDB();
            String sql = "INSERT INTO `customers`(`CustFirstName`, `CustLastName`, `CustAddress`, `CustCity`, `CustProv`, `CustPostal`, `CustCountry`, `CustHomePhone`, `CustBusPhone`, `CustEmail`, `AgentId`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement statement = insertConn.prepareStatement(sql);

                statement.setString(1, tfCustFname.getText());
                statement.setString(2, tfCustLname.getText());
                statement.setString(3, tfCustAddress.getText());
                statement.setString(4, tfCustCity.getText());
                statement.setString(5, tfCustProv.getText());
                statement.setString(6, tfCustPostal.getText());
                statement.setString(7, tfCustCountry.getText());
                statement.setString(8, tfCustHomePhone.getText());
                statement.setString(9, tfCustBusPhone.getText());
                statement.setString(10, tfCustEmail.getText());
                statement.setInt(11, cbAgentId.getSelectionModel().getSelectedItem().getAgentId());

                if (tfCustFname.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Firstname!");
                else if (tfCustLname.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Lastname!");
                else if (tfCustAddress.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Address!");
                else if (tfCustCity.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's City!");
                else if (tfCustProv.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Province!");
                else if (tfCustPostal.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Postal code!");
                else if (tfCustBusPhone.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Business Phone number!");
                else if (tfCustEmail.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,"Please enter the customer's Email");
                else {
                    boolean insertData = statement.execute();

                    if (insertData) {
                        //if update fails
                        System.out.println("Operation failed!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!!");
                        alert.setHeaderText(null);
                        alert.setContentText("Ooops, there was an error adding the customer!");
                        alert.showAndWait();
                    } else {
                        //if update success
                        System.out.println("Customer successfully added!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Customer Added");
                        alert.setHeaderText(null);
                        alert.setContentText("Operation Successful!");
                        alert.showAndWait();

                        disableTextfield();
                        btnDone.setVisible(false);
                        btnSaveCust.setVisible(false);
                        btnEditCust.setVisible(true);
                        btnCancel.setVisible(false);
                        cbCustomers.setDisable(false);
                    }
                }
                insertConn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        //---------------------------------------------------------

        //Save button function (Customer)   //By Suvanjan Shrestha
        btnSaveCust.setOnMouseClicked(mouseEvent -> {
            Connection updateConn = connectDB();
            String sql = "UPDATE `customers` SET `CustFirstName`=?,`CustLastName`=?,`CustAddress`=?,`CustCity`=?,`CustProv`=?,`CustPostal`=?,`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=?,`AgentId`=? WHERE CustomerId=?";

            try {
                PreparedStatement statement = updateConn.prepareStatement(sql);

                statement.setString(1, tfCustFname.getText());
                statement.setString(2, tfCustLname.getText());
                statement.setString(3, tfCustAddress.getText());
                statement.setString(4, tfCustCity.getText());
                statement.setString(5, tfCustProv.getText());
                statement.setString(6, tfCustPostal.getText());
                statement.setString(7, tfCustCountry.getText());
                statement.setString(8, tfCustHomePhone.getText());
                statement.setString(9, tfCustBusPhone.getText());
                statement.setString(10, tfCustEmail.getText());
                statement.setInt(11, cbAgentId.getSelectionModel().getSelectedItem().getAgentId());
                statement.setInt(12, Integer.parseInt(tfCusId.getText()));

                int numRows = statement.executeUpdate();
                if (numRows == 0) {
                    //if update fails
                    System.out.println("Update failed!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ooops, there was an error while updating!");
                    alert.showAndWait();
                } else {
                    //if update success
                    System.out.println("Update successful!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Successful!");
                    alert.showAndWait();

                    btnEditCust.setDisable(false);
                    disableTextfield();
                    initialize();
                }
                updateConn.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //Cancel button function    //By Suvanjan Shrestha
        btnCancel.setOnMouseClicked(mouseEvent -> {
            initialize();
            cbCustomers.setDisable(false);
        });

    }
    //---------------------------------------------------------

    //Connection to the database
    private Connection connectDB() {
        Connection c=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","harv" ,"password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    //---------------------------------------------------------

    //method to disable text fields from Contact
    private void disableContactTextFields(boolean b)
    {
        cmbConSupplier.setDisable(b);
        cmbAffiliation.setDisable(b);
        tfConFirstName.setDisable(b);
        tfConLastName.setDisable(b);
        tfConAddress.setDisable(b);
        tfConCity.setDisable(b);
        tfConProvince.setDisable(b);
        tfConPostal.setDisable(b);
        tfConCountry.setDisable(b);
        tfConPhone.setDisable(b);
        tfConFax.setDisable(b);
        tfConEmail.setDisable(b);
        tfConCompany.setDisable(b);
        tfConURL.setDisable(b);
    }

    //---------------------------------------------------------

    //Function to disable text fields in Customer form  //By Suvanjan Shrestha
    private void disableTextfield(){
        tfCusId.setDisable(true);
        tfCustFname.setDisable(true);
        tfCustLname.setDisable(true);
        tfCustAddress.setDisable(true);
        tfCustCity.setDisable(true);
        tfCustProv.setDisable(true);
        tfCustPostal.setDisable(true);
        tfCustCountry.setDisable(true);
        tfCustHomePhone.setDisable(true);
        tfCustBusPhone.setDisable(true);
        tfCustEmail.setDisable(true);
        cbAgentId.setDisable(true);
    }
    //---------------------------------------------------------

    //Function to enable text fields in Customer form   //By Suvanjan Shrestha
    private void enableTextfield(){
        tfCustFname.setDisable(false);
        tfCustLname.setDisable(false);
        tfCustAddress.setDisable(false);
        tfCustCity.setDisable(false);
        tfCustProv.setDisable(false);
        tfCustPostal.setDisable(false);
        tfCustCountry.setDisable(false);
        tfCustHomePhone.setDisable(false);
        tfCustBusPhone.setDisable(false);
        tfCustEmail.setDisable(false);
        cbAgentId.setDisable(false);
    }
    //---------------------------------------------------------
    //Function to default text fields and combobox in Customer form //By Suvanjan Shrestha
    private void clearTexts(){
        tfCusId.clear();
        tfCustFname.clear();
        tfCustLname.clear();
        tfCustAddress.clear();
        tfCustCity.clear();
        tfCustProv.clear();
        tfCustPostal.clear();
        tfCustCountry.clear();
        tfCustHomePhone.clear();
        tfCustBusPhone.clear();
        tfCustEmail.clear();
        cbAgentId.getSelectionModel().select(0);
    }
}