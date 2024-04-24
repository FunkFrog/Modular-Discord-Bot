package warframeapi.responses.openworldstates;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VallisStatus {
    private String id;
    private String expiry;
    private String timeLeft;
    private boolean isWarm;
    private String state;
    private String activation;
    private String shortString;

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

    @JsonProperty("timeLeft")
    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    @JsonProperty("isWarm")
    public boolean isWarm() {
        return isWarm;
    }

    public void setIsWarm(boolean warm) {
        isWarm = warm;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("activation")
    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    @JsonProperty("shortString")
    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "expiry: " + expiry + "\n" +
                "timeLeft: " + timeLeft + "\n" +
                "isWarm: " + isWarm + "\n" +
                "state: " + state + "\n" +
                "activation: " + activation + "\n" +
                "shortString: " + shortString;
    }
}