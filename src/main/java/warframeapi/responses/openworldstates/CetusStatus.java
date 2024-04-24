package warframeapi.responses.openworldstates;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CetusStatus {
    private String id;
    private Instant activation;
    private Instant expiry;
    private String startString;
    private boolean active;
    private boolean isDay;
    private String state;
    private String timeLeft;
    private boolean isCetus;
    private String shortString;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("activation")
    public Instant getActivation() {
        return activation;
    }

    @JsonProperty("expiry")
    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    @JsonProperty("startString")
    public String getStartString() {
        return startString;
    }

    @JsonProperty("active")
    public boolean isActive() {
        return active;
    }

    @JsonProperty("isDay")
    public boolean isDay() {
        return isDay;
    }

    public void setIsDay(boolean day) {
        isDay = day;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("timeLeft")
    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    @JsonProperty("isCetus")
    public boolean isCetus() {
        return isCetus;
    }

    public void setIsCetus(boolean cetus) {
        isCetus = cetus;
    }

    @JsonProperty("shortString")
    public String getShortString() {
        return shortString;
    }

    public String toString() {
        return "id: " + id + "\n" +
                "activation: " + activation + "\n" +
                "expiry: " + expiry + "\n" +
                "startString: " + startString + "\n" +
                "active: " + active + "\n" +
                "isDay: " + isDay + "\n" +
                "state: " + state + "\n" +
                "timeLeft: " + timeLeft + "\n" +
                "isCetus: " + isCetus + "\n" +
                "shortString: " + shortString;
    }
}
