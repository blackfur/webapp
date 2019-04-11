package practise;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class UDPTest {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Test
    void sample() throws SocketException,UnknownHostException,IOException{

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        DatagramSocket serverSocket = new DatagramSocket(9876);
        log.info("Server listening...");

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        String clientMsg = "Hello network.";
        sendData = clientMsg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        log.info("Client send: "+clientMsg);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String servReceiveStr= new String( receivePacket.getData());
        log.info("Server RECEIVED: " + servReceiveStr);

        InetAddress clientIPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        String capitalizedSentence = servReceiveStr.toUpperCase();
        sendData = capitalizedSentence.getBytes();
        DatagramPacket feedbackPack= new DatagramPacket(sendData, sendData.length, clientIPAddress, port);
        serverSocket.send(feedbackPack);
        log.info("Server feedback: Uppercase " +capitalizedSentence);

        serverSocket.close();
        log.info("server shutdown.");

        DatagramPacket clientReceivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(clientReceivePacket);
        String modifiedSentence = new String(clientReceivePacket.getData());
        log.info("receive FROM SERVER:" + modifiedSentence);

        clientSocket.close();
        log.info("Client shutdown.");
    }
}