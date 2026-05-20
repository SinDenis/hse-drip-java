package http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRatesClient implements RatesClient {

    private static final String DEFAULT_URL = "https://open.er-api.com/v6/latest/USD";

    private final String url;
    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public HttpRatesClient() {
        this(DEFAULT_URL);
    }

    public HttpRatesClient(String url) {
        this.url = url;
    }

    @Override
    public Map<String, BigDecimal> fetchRates() {
        try {
            HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            JsonNode rates = mapper.readTree(resp.body()).get("rates");
            Map<String, BigDecimal> result = new LinkedHashMap<>();
            Iterator<Map.Entry<String, JsonNode>> it = rates.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> e = it.next();
                result.put(e.getKey(), new BigDecimal(e.getValue().asText()));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
