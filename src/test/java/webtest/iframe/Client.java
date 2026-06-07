package webtest.iframe;

public class Client {
    private String clientId;
    private String clientName;
    private int clientAge;

    // Constructor
    public Client(String clientId, String clientName, int clientAge) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientAge = clientAge;
    }

    // Getters
    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public int getClientAge() {
        return clientAge;
    }

    // Setters
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientAge=" + clientAge +
                '}';
    }
}

