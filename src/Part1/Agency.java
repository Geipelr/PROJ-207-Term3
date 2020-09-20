package Part1;

public class Agency {
    private String agncyAddress;
    private String agncyCity;
    private String agncyCountry;
    private String agncyFax;
    private String agncyPhone;
    private String agncyPostal;
    private String agncyProv;

    public Agency(String agncyAddress, String agncyCity, String agncyCountry,
                  String agncyFax, String agncyPhone, String agncyPostal, String agncyProv) {
        this.agncyAddress = agncyAddress;
        this.agncyCity = agncyCity;
        this.agncyCountry = agncyCountry;
        this.agncyFax = agncyFax;
        this.agncyPhone = agncyPhone;
        this.agncyPostal = agncyPostal;
        this.agncyProv = agncyProv;
    }

    public Agency() {
    }

    public String getAgncyAddress() {
        return agncyAddress;
    }

    public void setAgncyAddress(String agncyAddress) {
        this.agncyAddress = agncyAddress;
    }

    public String getAgncyCity() {
        return agncyCity;
    }

    public void setAgncyCity(String agncyCity) {
        this.agncyCity = agncyCity;
    }

    public String getAgncyCountry() {
        return agncyCountry;
    }

    public void setAgncyCountry(String agncyCountry) {
        this.agncyCountry = agncyCountry;
    }

    public String getAgncyFax() {
        return agncyFax;
    }

    public void setAgncyFax(String agncyFax) {
        this.agncyFax = agncyFax;
    }

    public String getAgncyPhone() {
        return agncyPhone;
    }

    public void setAgncyPhone(String agncyPhone) {
        this.agncyPhone = agncyPhone;
    }

    public String getAgncyPostal() {
        return agncyPostal;
    }

    public void setAgncyPostal(String agncyPostal) {
        this.agncyPostal = agncyPostal;
    }

    public String getAgncyProv() {
        return agncyProv;
    }

    public void setAgncyProv(String agncyProv) {
        this.agncyProv = agncyProv;
    }
}
