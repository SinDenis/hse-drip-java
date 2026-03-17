package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер запущен на порту 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Получено от клиента: " + input);

                    if ("exit".equalsIgnoreCase(input)) {
                        out.println("Соединение закрыто.");
                        break;
                    } else if ("hello".equalsIgnoreCase(input)) {
                        out.println("Hello, client!");
                    } else if ("time".equalsIgnoreCase(input)) {
                        out.println("Текущее время: " + LocalDateTime.now());
                    } else {
                        out.println("Неизвестная команда: " + input);
                    }
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
