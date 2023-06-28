package mainpack;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private  final int PORT = 55555;
    private  final int LENGHT = 1024;

    public void start() {

        try (DatagramSocket socket = new DatagramSocket()){

            // вводим ссобщение через консоль
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите сообщение: ");
            String message = scanner.nextLine();

            // формируем запрос и отправляем серверу
            byte[] requestData = message.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getByName("127.0.0.1"), PORT);
            socket.send(requestPacket);

            // получаем ответ от сервера
            byte[] buffer = new byte[LENGHT];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            // извлекаем ответ от сервера и выводим в консоль
            String response = new String(responsePacket.getData(), responsePacket.getOffset(), responsePacket.getLength());
            System.out.println(response);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
