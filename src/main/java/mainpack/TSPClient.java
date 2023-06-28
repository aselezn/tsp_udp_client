package mainpack;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TSPClient {
    private final int PORT = 8888;
    private final String HOST = "127.0.0.1";
    public void start() {

        // вводим сообщение через консоль
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сообщение: ");
        String message = scanner.nextLine();

        try (Socket socket = new Socket(HOST, PORT)) {
            // конструкция доя отправления сообещния
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); //возвращает поток вывода (OutputStream), связанный с сокетом
            writer.println(message);

            // конструкция для получения сообщения от сервера
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            System.out.println("Время приема сообщения на сервере: " + response);

            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
