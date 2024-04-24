package warframeapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import warframeapi.responses.openworldstates.CambionDriftStatus;
import warframeapi.responses.openworldstates.CetusStatus;
import warframeapi.responses.openworldstates.VallisStatus;

import java.io.IOException;

public class Warframe {
    public static void main(String[] args) {

    }

    private static CetusStatus cetusStatus() {
        return (CetusStatus) getAPIJsonResponse("pc", "cetusCycle", "en", CetusStatus.class);
    }

    private static CambionDriftStatus cambionDriftStatus() {
        return (CambionDriftStatus) getAPIJsonResponse("pc", "cambionCycle", "en", CambionDriftStatus.class);
    }

    private static VallisStatus vallisStatus() {
        return (VallisStatus) getAPIJsonResponse("pc", "vallisCycle", "en", VallisStatus.class);
    }

    private static Object getAPIJsonResponse(String platform, String endpoint, String language, Class<?> type) {
        HttpGet httpGet = new HttpGet("https://api.warframestat.us/" + platform + "/" + endpoint + "?language=" + language);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseBody = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.readValue(responseBody, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
