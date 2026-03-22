package server;

import server.store.HashMapStore;
import server.store.KeyValueStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class KeyValueServer {
    private static final KeyValueStore store = new HashMapStore();
    private static final int THREAD_POOL_SIZE = 4;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        BlockingQueue<Socket> queue = new LinkedBlockingQueue<>(10);

        // Запуск воркеров
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            pool.submit(() -> {
                while (true) {
                    try {
                        Socket socket = queue.take();
                        handleClient(socket);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }

        try (ServerSocket serverSocket = new ServerSocket(8080, 4)) {
            System.out.println("Key-Value сервер запущен на порту 8080...");
            System.out.println("Команды: set key value, get key, del key, list, count, stress N, exit");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getRemoteSocketAddress());
                queue.put(clientSocket);
            }
        } catch (IOException | InterruptedException e) {
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
                String[] parts = input.split(" ", 3);
                String command = parts[0].toLowerCase();

                switch (command) {
                    case "set":
                        handleSet(parts, out);
                        break;

                    case "get":
                        handleGet(parts, out);
                        break;

                    case "del":
                        handleDel(parts, out);
                        break;

                    case "list":
                        handleList(out);
                        break;

                    case "count":
                        out.println("Записей в хранилище: " + store.size());
                        break;

                    case "stress":
                        handleStress(parts, out);
                        break;

                    case "exit":
                        out.println("Соединение закрыто. Записей в хранилище: " + store.size());
                        return;

                    default:
                        out.println("Неизвестная команда: " + input);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
        }
    }

    private static void handleSet(String[] parts, PrintWriter out) {
        if (parts.length < 3) {
            out.println("Использование: set <key> <value>");
            return;
        }
        store.put(parts[1], parts[2]);
        out.println("OK");
    }

    private static void handleGet(String[] parts, PrintWriter out) {
        if (parts.length < 2) {
            out.println("Использование: get <key>");
            return;
        }
        String value = store.get(parts[1]);
        if (value != null) {
            out.println(value);
        } else {
            out.println("NOT_FOUND");
        }
    }

    private static void handleDel(String[] parts, PrintWriter out) {
        if (parts.length < 2) {
            out.println("Использование: del <key>");
            return;
        }
        String removed = store.delete(parts[1]);
        if (removed != null) {
            out.println("DELETED");
        } else {
            out.println("NOT_FOUND");
        }
    }

    private static void handleList(PrintWriter out) {
        var keys = store.keys();
        if (keys.isEmpty()) {
            out.println("(пусто)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(" = ").append(store.get(key)).append("; ");
        }
        out.println(sb.toString());
    }

    private static void handleStress(String[] parts, PrintWriter out) {
        int n = 1000;
        if (parts.length > 1) {
            try {
                n = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                out.println("Неверный формат числа, использую 1000");
            }
        }

        Random random = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            String key = "key-" + random.nextInt(100);
            String value = "value-" + i;

            // read-modify-write: читаем, изменяем, записываем
            String existing = store.get(key);
            if (existing != null) {
                store.put(key, existing + "," + value);
            } else {
                store.put(key, value);
            }
        }

        long endTime = System.currentTimeMillis();

        out.println("Стресс-тест завершён: " + n + " операций за " + (endTime - startTime) + " мс. Записей: " + store.size());
    }
}
