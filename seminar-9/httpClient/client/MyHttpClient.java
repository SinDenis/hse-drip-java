package httpClient.client;

import httpClient.model.HttpMethod;
import httpClient.model.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Используйте ТОЛЬКО java.net.Socket — никаких HttpClient, HttpURLConnection и т.д.
 */
public class MyHttpClient {

    // Вспомогательный метод — читает весь InputStream в строку
    protected String readAllFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8);
    }

    // Часть 1. GET-запрос через TCP-сокет.
    public HttpResponse executeGet(String host, int port, String path) throws IOException {
        // TODO Открыть TCP-сокет: new Socket(host, port)
        // TODO Сформировать строку запроса, которая должна выглядеть так:
        //   GET /api/hello?name=Java HTTP/1.1\r\n
        //   Host: localhost\r\n
        //   Connection: close\r\n
        //   \r\n
        // TODO Отправить запрос: socket.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8))
        // TODO Не забыть вызвать flush()
        // TODO Прочитать ответ: String raw = readAllFromStream(socket.getInputStream())
        // TODO Закрыть сокет: socket.close()
        // TODO Вернуть parseResponse(raw)

        throw new UnsupportedOperationException("Метод executeGet не реализован");
    }

    // Часть 2. POST-запрос через TCP-сокет.
    public HttpResponse executePost(String host, int port, String path, String body) throws IOException {
        // TODO Открыть TCP-сокет: new Socket(host, port)
        // TODO Посчитать длину тела в байтах: body.getBytes(StandardCharsets.UTF_8).length (не body.length()!)
        // TODO Сформировать строку запроса, которая должна выглядеть так:
        //   POST /api/echo HTTP/1.1\r\n
        //   Host: localhost\r\n
        //   Content-Type: text/plain\r\n
        //   Content-Length: 36\r\n
        //   Connection: close\r\n
        //   \r\n
        //   Привет от клиента!
        // TODO Отправить: socket.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8))
        // TODO flush(), прочитать ответ через readAllFromStream(), закрыть сокет
        // TODO Вернуть parseResponse(raw)

        throw new UnsupportedOperationException("Метод executePost не реализован");
    }

    // Часть 3. Парсинг сырого HTTP-ответа.
    // На вход приходит строка вида:
    //   HTTP/1.1 200 OK\r\n
    //   Content-Type: text/plain; charset=utf-8\r\n
    //   Content-Length: 53\r\n
    //   \r\n
    //   Добро пожаловать на тестовый сервер!
    private HttpResponse parseResponse(String rawResponse) {
        // TODO Разделить rawResponse на заголовки и тело: rawResponse.split("\r\n\r\n", 2)
        // TODO Разбить заголовочную часть на строки: headerPart.split("\r\n")
        // TODO Из первой строки извлечь статус-код: "HTTP/1.1 200 OK" -> split(" ") -> Integer.parseInt(parts[1])
        // TODO Остальные строки разобрать в Map: "Content-Type: text/plain" -> split(": ", 2)
        // TODO Вернуть new HttpResponse(statusCode, headers, body)

        throw new UnsupportedOperationException("Метод parseResponse не реализован");
    }

    // Часть 4. Универсальный метод: произвольный HTTP-метод + свои заголовки.
    public HttpResponse execute(HttpMethod method, String host, int port, String path,
                                Map<String, String> headers, String body) throws IOException {
        // TODO Открыть TCP-сокет: new Socket(host, port)
        // TODO Первая строка запроса: method.name() + " " + path + " HTTP/1.1\r\n"
        // TODO Добавить заголовок Host: host + "\r\n"
        // TODO Добавить все заголовки из Map headers: key + ": " + value + "\r\n"
        // TODO Если body != null — добавить Content-Length и после "\r\n" дописать тело
        // TODO Добавить Connection: close\r\n и завершить заголовки "\r\n"
        // TODO Отправить, прочитать, вернуть parseResponse(raw)

        throw new UnsupportedOperationException("Метод execute не реализован");
    }
}
