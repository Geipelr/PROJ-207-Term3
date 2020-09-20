//Gustavo Lourenco Moises
//Date: 9/16/2020
//CPRG 264 - Java Programming
//Lab6

package Part1;
//Author: Gustavo Lourenco Moises
//Date 9/20/2020
//Thread Project
//Workshop 6
public class Agent {
    private int agentId;
    private int agencyId;
    private String agtFirstName;
    private String agtMiddleInitial;
    private String agtLastName;
    private String agtBusPhone;
    private String agtEmail;
    private String agtPosition;

    //Constructor 0 arguments
    public Agent() {
    }
    //Constructor 1 argument
    public Agent(int agentId) {
        this.agentId = agentId;
    }
    // Constructor 8 arguments
    public Agent(int agentId, String agtFistName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition, int agencyId) {
        this.agentId = agentId;
        this.agencyId = agencyId;
        this.agtFirstName = agtFistName;
        this.agtMiddleInitial = agtMiddleInitial;
        this.agtLastName = agtLastName;
        this.agtBusPhone = agtBusPhone;
        this.agtEmail = agtEmail;
        this.agtPosition = agtPosition;
    }
    //Constructor 7 arguments - no Agency
    public Agent(int agentId, String agtFistName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition) {
        this.agentId = agentId;
        this.agtFirstName = agtFistName;
        this.agtMiddleInitial = agtMiddleInitial;
        this.agtLastName = agtLastName;
        this.agtBusPhone = agtBusPhone;
        this.agtEmail = agtEmail;
        this.agtPosition = agtPosition;
    }

    //Getter and Setter
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgtFirstName() {
        return agtFirstName;
    }

    public void setAgtFirstName(String agtFistName) {
        this.agtFirstName = agtFistName;
    }

    public String getAgtMiddleInitial() {
        return agtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return agtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.agtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    @Override
    public String toString() {
        return  agentId+" - "+ agtFirstName+" "+agtLastName;
    }
}
