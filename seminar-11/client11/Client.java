package client11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8080);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Подключение к серверу установлено.");
            System.out.println("Доступные команды:");
            System.out.println("  hello     - получить приветствие");
            System.out.println("  time      - получить текущее время");
            System.out.println("  count     - показать счётчик команд");
            System.out.println("  stress N  - отправить N пар команд (hello+time)");
            System.out.println("  exit      - выход");

            String userInput;
            while (!(userInput = scanner.nextLine()).equals("exit")) {
                if (userInput.startsWith("stress")) {
                    handleStressCommand(userInput);
                } else {
                    out.println(userInput);
                    System.out.println("Ответ от сервера: " + in.readLine());
                }
            }

            out.println("exit");
            System.out.println("Ответ от сервера: " + in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleStressCommand(String command) {
        try {
            int count = 100;
            String[] parts = command.split(" ");
            if (parts.length > 1) {
                try {
                    count = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат числа, использую 100");
                }
            }

            System.out.println("Отправка " + count + " пар команд (hello + time)...");

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < count; i++) {
                out.println("hello");
                in.readLine();

                out.println("time");
                in.readLine();

                if (i > 0 && i % (count / 10) == 0) {
                    System.out.println("Прогресс: " + (i * 100 / count) + "%");
                }
            }

            long endTime = System.currentTimeMillis();

            out.println("count");
            String countResponse = in.readLine();
            System.out.println("Финальный счётчик: " + countResponse);
            System.out.println("Время выполнения: " + (endTime - startTime) + " мс");

        } catch (IOException e) {
            System.out.println("Ошибка при стресс-тесте: " + e.getMessage());
        }
    }
}
