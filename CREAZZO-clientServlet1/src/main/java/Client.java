import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private static final String BASE_URL = "http://localhost:8080/SERVLET1/";
    private final HttpClient client;

    public Client() {
        this.client = HttpClient.newHttpClient();
    }

    private String sendRequest(String url) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAllPokemon() throws IOException, InterruptedException {
        return sendRequest(BASE_URL);
    }

    public String getPokemonByName(String name) throws IOException, InterruptedException {
        return sendRequest(BASE_URL + "name/" + name);
    }

    public String getPokemonById(int id) throws IOException, InterruptedException {
        return sendRequest(BASE_URL + "id/" + id);
    }
}