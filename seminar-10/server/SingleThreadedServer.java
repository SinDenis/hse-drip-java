package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class SingleThreadedServer {
    private static int totalCommands = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Однопоточный сервер запущен на порту 8080...");
            System.out.println("Поддерживаемые команды: hello, time, count, stress, exit");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getRemoteSocketAddress());

                handleClient(clientSocket);

                System.out.println("Клиент отключен: " + clientSocket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Получено от " + clientSocket.getRemoteSocketAddress() + ": " + input);

                // Увеличиваем счётчик команд
                totalCommands++;

                // Используем switch вместо множества if-else
                switch (input.toLowerCase()) {
                    case "exit":
                        out.println("Соединение закрыто. Всего команд обработано: " + totalCommands);
                        return; // Выходим из метода, закрывая соединение

                    case "hello":
                        out.println("Hello, client!");
                        break;

                    case "time":
                        out.println("Текущее время: " + LocalDateTime.now());
                        break;

                    case "count":
                        out.println("Всего команд обработано: " + totalCommands);
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
        int count = 100; // значение по умолчанию
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
            // Отправляем hello и считаем его
            totalCommands++;
            out.println("Hello, client! [стресс-тест " + (i + 1) + "]");

            // Отправляем time и считаем его
            totalCommands++;
            out.println("Текущее время: " + LocalDateTime.now() + " [стресс-тест " + (i + 1) + "]");
        }

        out.println("Стресс-тест завершён. Всего команд обработано: " + totalCommands);
    }
}
