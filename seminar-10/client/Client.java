package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Подключение к серверу установлено.");

            String userInput;
            while (!(userInput = scanner.nextLine()).equals("exit")) {
                out.println(userInput);
                System.out.println("Ответ от сервера: " + in.readLine());
            }

            // Отправляем exit и завершаем
            out.println("exit");
            System.out.println("Ответ от сервера: " + in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
