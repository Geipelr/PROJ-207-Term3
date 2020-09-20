//Author: Gustavo Lourenco Moises
//Date 9/20/2020
//Thread Project
//Workshop 6
package Part1;

public class SupplierContact {
    private int SupConId;
    private String SupConFirstName;
    private String SupConLastName;
    private String SupConCompany;
    private String SupConAddress;
    private String SupConCity;
    private String SupConProv;
    private String SupConPostal;
    private String SupConCountry;
    private String SupConPhone;
    private String SupConFax;
    private String SupConEmail;
    private String SupConURL;
    private String SupConAffiliation;
    private int SupConSupplierId;

    public SupplierContact() {
    }

    public SupplierContact(int supConId, String supConFirstName, String supConLastName, String supConCompany, String supConAddress, String supConCity, String supConProv, String supConPostal, String supConCountry, String supConPhone, String supConFax, String supConMail, String supConURL, String supConAffiliation, int supConSupplierId) {
        SupConId = supConId;
        SupConFirstName = supConFirstName;
        SupConLastName = supConLastName;
        SupConCompany = supConCompany;
        SupConAddress = supConAddress;
        SupConCity = supConCity;
        SupConProv = supConProv;
        SupConPostal = supConPostal;
        SupConCountry = supConCountry;
        SupConPhone = supConPhone;
        SupConFax = supConFax;
        SupConEmail = supConMail;
        SupConURL = supConURL;
        SupConAffiliation = supConAffiliation;
        SupConSupplierId = supConSupplierId;
    }
    // No affiliations
    public SupplierContact(int supConId, String supConFirstName, String supConLastName, String supConCompany, String supConAddress, String supConCity, String supConProv, String supConPostal, String supConCountry, String supConPhone, String supConFax, String supConMail, String supConURL, int supConSupplierId) {
        SupConId = supConId;
        SupConFirstName = supConFirstName;
        SupConLastName = supConLastName;
        SupConCompany = supConCompany;
        SupConAddress = supConAddress;
        SupConCity = supConCity;
        SupConProv = supConProv;
        SupConPostal = supConPostal;
        SupConCountry = supConCountry;
        SupConPhone = supConPhone;
        SupConFax = supConFax;
        SupConEmail = supConMail;
        SupConURL = supConURL;
        SupConSupplierId = supConSupplierId;
    }

    public int getSupConId() {
        return SupConId;
    }

    public void setSupConId(int supConId) {
        SupConId = supConId;
    }

    public String getSupConFirstName() {
        return SupConFirstName;
    }

    public void setSupConFirstName(String supConFirstName) {
        SupConFirstName = supConFirstName;
    }

    public String getSupConLastName() {
        return SupConLastName;
    }

    public void setSupConLastName(String supConLastName) {
        SupConLastName = supConLastName;
    }

    public String getSupConCompany() {
        return SupConCompany;
    }

    public void setSupConCompany(String supConCompany) {
        SupConCompany = supConCompany;
    }

    public String getSupConAddress() {
        return SupConAddress;
    }

    public void setSupConAddress(String supConAddress) {
        SupConAddress = supConAddress;
    }

    public String getSupConCity() {
        return SupConCity;
    }

    public void setSupConCity(String supConCity) {
        SupConCity = supConCity;
    }

    public String getSupConProv() {
        return SupConProv;
    }

    public void setSupConProv(String supConProv) {
        SupConProv = supConProv;
    }

    public String getSupConPostal() {
        return SupConPostal;
    }

    public void setSupConPostal(String supConPostal) {
        SupConPostal = supConPostal;
    }

    public String getSupConCountry() {
        return SupConCountry;
    }

    public void setSupConCountry(String supConCountry) {
        SupConCountry = supConCountry;
    }

    public String getSupConPhone() {
        return SupConPhone;
    }

    public void setSupConPhone(String supConPhone) {
        SupConPhone = supConPhone;
    }

    public String getSupConFax() {
        return SupConFax;
    }

    public void setSupConFax(String supConFax) {
        SupConFax = supConFax;
    }

    public String getSupConEmail() {
        return SupConEmail;
    }

    public void setSupConEmail(String supConMail) {
        SupConEmail = supConMail;
    }

    public String getSupConURL() {
        return SupConURL;
    }

    public void setSupConURL(String supConURL) {
        SupConURL = supConURL;
    }

    public String getSupConAffiliation() {
        return SupConAffiliation;
    }

    public void setSupConAffiliation(String supConAffiliation) {
        SupConAffiliation = supConAffiliation;
    }

    public int getSupConSupplierId() {
        return SupConSupplierId;
    }

    public void setSupConSupplierId(int supConSupplierId) {
        SupConSupplierId = supConSupplierId;
    }

    @Override
    public String toString() {
        return SupConFirstName + " " + SupConLastName +" - " + SupConCompany;
    }

}
