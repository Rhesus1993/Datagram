import java.io.*;
import java.net.*;

public class UDPServer{

    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        System.out.println("Waiting connections......");
        System.out.println("\n");
		
		
        while(true)
        {
			//recieves the packet from the client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//opens the packet
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
			//prints the contents of the packet to the server
            System.out.println("RECEIVED: " + sentence);
			//creates the contents of the packet to send back to the client, and send the packet back to the client
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            String dataS;
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
