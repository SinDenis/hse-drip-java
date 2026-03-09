package httpClient;

import httpClient.client.MyHttpClient;
import httpClient.model.HttpMethod;
import httpClient.model.HttpResponse;

import java.io.IOException;
import java.util.Map;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        // ВАЖНО: перед запуском убедитесь, что TestHttpServer запущен!
        // Нужно нажать Run в файле server/TestHttpServer
        MyHttpClient client = new MyHttpClient();

        // --- Часть 1: GET-запросы ---

        System.out.println("=== Тест GET / ===");
        try {
            HttpResponse response = client.executeGet(HOST, PORT, "/");
            System.out.println("Статус: " + response.getStatusCode());
            System.out.println("Тело: " + response.getBody());
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Тест GET /api/hello?name=Java ===");
        try {
            HttpResponse response = client.executeGet(HOST, PORT, "/api/hello?name=Java");
            System.out.println("Статус: " + response.getStatusCode());
            System.out.println("Тело: " + response.getBody());
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // --- Часть 2: POST-запрос ---

        System.out.println();
        System.out.println("=== Тест POST /api/echo ===");
        try {
            HttpResponse response = client.executePost(HOST, PORT, "/api/echo", "Привет от клиента!");
            System.out.println("Статус: " + response.getStatusCode());
            System.out.println("Тело: " + response.getBody());
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // --- Часть 3: универсальный метод ---

        System.out.println();
        System.out.println("=== Тест execute: POST /api/echo ===");
        try {
            HttpResponse response = client.execute(
                HttpMethod.POST, HOST, PORT, "/api/echo",
                Map.of("Content-Type", "text/plain"),
                "Привет через execute!"
            );
            System.out.println("Статус: " + response.getStatusCode());
            System.out.println("Тело: " + response.getBody());
        } catch (UnsupportedOperationException e) {
            System.out.println("(Часть 4 не реализована — пропускаем)");
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
