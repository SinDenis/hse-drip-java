package httpClient.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Тестовый HTTP-сервер для проверки вашего HTTP-клиента.
 * Запустите этот сервер ПЕРЕД запуском Main.
 *
 * Обрабатывает:
 *   GET  /              -> "Добро пожаловать на тестовый сервер!"
 *   GET  /api/hello     -> "Привет, {name}!" (параметр name из query string)
 *   POST /api/echo      -> "Вы отправили: {тело запроса}"
 *   Любой другой путь   -> 404 "Ресурс не найден: {путь}"
 */
public class TestHttpServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/api/hello", new HelloHandler());
        server.createContext("/api/echo", new EchoHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Тестовый HTTP-сервер запущен на порту " + PORT);
        System.out.println("Для остановки нажмите Ctrl+C");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestURI().getPath().equals("/")) {
                String response = "Ресурс не найден: " + exchange.getRequestURI().getPath();
                sendResponse(exchange, 404, response);
                return;
            }
            sendResponse(exchange, 200, "Добро пожаловать на тестовый сервер!");
        }
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, String> params = parseQueryParams(exchange.getRequestURI().getQuery());
            String name = params.getOrDefault("name", "Мир");
            sendResponse(exchange, 200, "Привет, " + name + "!");
        }
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "Метод не поддерживается. Используйте POST.");
                return;
            }
            InputStream is = exchange.getRequestBody();
            String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            sendResponse(exchange, 200, "Вы отправили: " + requestBody);
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        exchange.getResponseHeaders().set("Connection", "close");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new LinkedHashMap<>();
        if (query == null || query.isEmpty()) {
            return params;
        }
        for (String param : query.split("&")) {
            String[] pair = param.split("=", 2);
            String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8);
            String value = pair.length > 1 ? URLDecoder.decode(pair[1], StandardCharsets.UTF_8) : "";
            params.put(key, value);
        }
        return params;
    }
}
