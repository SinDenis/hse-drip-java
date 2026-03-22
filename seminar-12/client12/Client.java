package client12;

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

            System.out.println("Подключение к Key-Value серверу установлено.");
            System.out.println("Доступные команды:");
            System.out.println("  set key value  - сохранить значение");
            System.out.println("  get key        - получить значение");
            System.out.println("  del key        - удалить запись");
            System.out.println("  list           - показать все записи");
            System.out.println("  count          - количество записей");
            System.out.println("  stress N       - стресс-тест (N операций set/get)");
            System.out.println("  exit           - выход");

            String userInput;
            while (!(userInput = scanner.nextLine()).equals("exit")) {
                if (userInput.startsWith("stress")) {
                    handleStressCommand(userInput);
                } else {
                    out.println(userInput);
                    System.out.println("Ответ: " + in.readLine());
                }
            }

            out.println("exit");
            System.out.println("Ответ: " + in.readLine());

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
            int count = 1000;
            String[] parts = command.split(" ");
            if (parts.length > 1) {
                try {
                    count = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат числа, использую 1000");
                }
            }

            System.out.println("Отправка stress " + count + " на сервер...");

            long startTime = System.currentTimeMillis();

            out.println("stress " + count);
            String response = in.readLine();

            long endTime = System.currentTimeMillis();

            System.out.println("Ответ сервера: " + response);
            System.out.println("Общее время (с сетью): " + (endTime - startTime) + " мс");

        } catch (IOException e) {
            System.out.println("Ошибка при стресс-тесте: " + e.getMessage());
        }
    }
}
