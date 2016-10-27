import java.io.*;
import java.net.*;

class UDPClient
{
    public static void main(String args[]) throws Exception
    {
      while(true){
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        System.out.print("Connected\n");
        System.out.print("Enter a message....");
        System.out.print("\n");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String DataS;
        String sentence = inFromUser.readLine();

          for(int i=0;i<sentence.length();i++){
              //breaks up the string to individual chars
              char data = sentence.charAt(i);
              DataS = sentence.valueOf(data);
              sendData = DataS.getBytes();
              //sends each char to the server
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
              clientSocket.send(sendPacket);
              //gets input back from the server
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String modifiedSentence = new String(receivePacket.getData());
              System.out.println("FROM SERVER:" + modifiedSentence);

            }
            clientSocket.close();
        }
    }
}
