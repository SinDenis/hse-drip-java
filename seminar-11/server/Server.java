package server;

import server.counter.CommandCounter;
import server.counter.SimpleCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    private static final CommandCounter counter = new SimpleCounter();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080, 4)) {
            System.out.println("Сервер запущен на порту 8080...");
            System.out.println("Поддерживаемые команды: hello, time, count, stress, exit");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getRemoteSocketAddress());
                Thread.ofPlatform().start(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void handleClient(Socket clientSocket) {
        try (
                Socket socket = clientSocket;
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Получено от " + socket.getRemoteSocketAddress() + ": " + input);

                counter.increment();

                switch (input.toLowerCase()) {
                    case "exit":
                        out.println("Соединение закрыто. Всего команд обработано: " + counter.get());
                        return;

                    case "hello":
                        out.println("Hello, client!");
                        break;

                    case "time":
                        out.println("Текущее время: " + LocalDateTime.now());
                        break;

                    case "count":
                        out.println("Всего команд обработано: " + counter.get());
                        break;

                    default:
                        if (input.startsWith("stress")) {
                            handleStressCommand(input, out);
                        } else {
                            out.println("Неизвестная команда: " + input);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
        }
    }

    private static void handleStressCommand(String command, PrintWriter out) {
        int count = 100;
        String[] parts = command.split(" ");
        if (parts.length > 1) {
            try {
                count = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                out.println("Неверный формат числа, использую 100");
            }
        }

        out.println("Начинаю стресс-тест: отправка " + count + " пар команд...");

        for (int i = 0; i < count; i++) {
            counter.increment();
            out.println("Hello, client! [стресс-тест " + (i + 1) + "]");

            counter.increment();
            out.println("Текущее время: " + LocalDateTime.now() + " [стресс-тест " + (i + 1) + "]");
        }

        out.println("Стресс-тест завершён. Всего команд обработано: " + counter.get());
    }
}
