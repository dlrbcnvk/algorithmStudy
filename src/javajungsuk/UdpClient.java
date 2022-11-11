package javajungsuk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpClient {

    public void start() throws IOException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

        // 데이터가 저장될 공간으로 byte배열 생성
        byte[] msg = new byte[100];
        DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 7878);
        DatagramPacket inPacket = new DatagramPacket(msg, msg.length);

        datagramSocket.send(outPacket); // DatagramPacket을 전송한다
        datagramSocket.receive(inPacket); // DatagramPacket을 수신한다

        System.out.println("current server time : " + new String(inPacket.getData()));
        datagramSocket.close();
    }

    public static void main(String[] args) {
        try {
            new UdpClient().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
