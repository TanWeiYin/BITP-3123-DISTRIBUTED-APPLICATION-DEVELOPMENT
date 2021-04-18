package udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class serverApp {

	public static void main(String[] args) 
	{
		
		try {
			
			// Instantiate a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(50001);
		    
		    // Create buffers to hold receiving data.
		    byte receivingDataBuffer[] = new byte[1024];
		    
		    // Instantiate a UDP packet to store the client data using the buffer for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
		    System.out.println("Waiting for a client to connect...");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String receivedData = new String(inputPacket.getData());
		    System.out.println("Sent from the client: " + receivedData);
		    
		    // Process data - convert to upper case
		    StringTokenizer tokens = new StringTokenizer(receivedData);
		    int words = tokens.countTokens();
		    System.out.println("Counting Words...");
		    System.out.println("Words Counted, Sending Back Data...");
		    
		    // Creating corresponding buffer to send data	
		    String result = Integer.toString(words);
		    byte sendingDataBuffer[] = result.getBytes();

		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		    // Create new UDP packet with data to send to the client
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
		    serverSocket.send(outputPacket);
		    
		    
		    // Close the socket connection
		    serverSocket.close();
		      
		} 
		catch (Exception ex) 
		{
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
				
		System.out.println("\nProgram at server-side ends");

	}

}


