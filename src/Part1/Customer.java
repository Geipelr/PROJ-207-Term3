//Author: Gustavo Lourenco Moises
//Date 9/20/2020
//Thread Project
//Workshop 6

package Part1;

public class Customer {
    private int CustomerId;
    private String CustFirstName;
    private String CustLastName;
    private String CustAddress;
    private String CustCity;
    private String CustProv;
    private String CustPostal;
    private String Country;
    private String CustHomePhone;
    private String CustBusPhone;
    private String CustEmail;
    private int AgentID;

    public Customer(int customerId, String custFirstName, String custLastName, String custAddress, String custCity, String custProv, String custPostal, String country, String custHomePhone, String custBusPhone, String custEmail, int agentID) {
        CustomerId = customerId;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustAddress = custAddress;
        CustCity = custCity;
        CustProv = custProv;
        CustPostal = custPostal;
        Country = country;
        CustHomePhone = custHomePhone;
        CustBusPhone = custBusPhone;
        CustEmail = custEmail;
        AgentID = agentID;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustFirstName() {
        return CustFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        CustFirstName = custFirstName;
    }

    public String getCustLastName() {
        return CustLastName;
    }

    public void setCustLastName(String custLastName) {
        CustLastName = custLastName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCustCity() {
        return CustCity;
    }

    public void setCustCity(String custCity) {
        CustCity = custCity;
    }

    public String getCustProv() {
        return CustProv;
    }

    public void setCustProv(String custProv) {
        CustProv = custProv;
    }

    public String getCustPostal() {
        return CustPostal;
    }

    public void setCustPostal(String custPostal) {
        CustPostal = custPostal;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCustHomePhone() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        CustHomePhone = custHomePhone;
    }

    public String getCustBusPhone() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        CustBusPhone = custBusPhone;
    }

    public String getCustEmail() {
        return CustEmail;
    }

    public void setCustEmail(String custEmail) {
        CustEmail = custEmail;
    }

    public int getAgentID() {
        return AgentID;
    }

    public void setAgentID(int agentID) {
        AgentID = agentID;
    }

    @Override
    public String toString() {
        return  CustFirstName + CustLastName;
    }
}

