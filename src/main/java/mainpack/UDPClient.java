package mainpack;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPClient {
    private  final int PORT = 55555;
    private  final int LENGHT = 1024;

    public void start() {

        try (DatagramSocket socket = new DatagramSocket()){

            // вводим ссобщение в консоли
            System.out.print("Введите сообщение: ");
            byte[] requestData = new byte[LENGHT];
            System.in.read(requestData);

            // отправляем запрос
            DatagramPacket requestPacket = new DatagramPacket(requestData, LENGHT, InetAddress.getByName("127.0.0.1"), PORT);
            socket.send(requestPacket);

            // получаем от сервера
            byte[] buffer = new byte[LENGHT];
            DatagramPacket responsePacket = new DatagramPacket(buffer, LENGHT);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
