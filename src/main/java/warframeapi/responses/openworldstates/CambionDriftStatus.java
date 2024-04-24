package warframeapi.responses.openworldstates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CambionDriftStatus {
    private String id;
    private String expiry;
    private String activation;
    private String state;
    private String active;
    private String timeLeft;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("expiry")
    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @JsonProperty("activation")
    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("active")
    @JsonDeserialize(as = String.class)
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @JsonProperty("timeLeft")
    public String getTimeLeft() {
        return timeLeft;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "expiry: " + expiry + "\n" +
                "activation: " + activation + "\n" +
                "state: " + state + "\n" +
                "active: " + active + "\n" +
                "timeLeft: " + timeLeft;
    }
}
