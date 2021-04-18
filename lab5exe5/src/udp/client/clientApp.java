package udp.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class clientApp {

	public static void main(String[] args) 
	{
		// The server port to which the client socket is going to connect
		final int SERVERPORT = 50001;
				
		int bufferSize = 1024;
				
		Scanner writting = new Scanner(System.in);
				
		System.out.println("Client-Side Application for UDP demo\n");
				
		try 
		{
			System.out.println("Text: ");		
			String sentence = writting.nextLine();

			// Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();

			// Get the IP address of the server
			InetAddress serverAddress = InetAddress.getByName("localhost");

			// Create buffer to send data
			byte sendingDataBuffer[] = new byte[bufferSize];

			// Convert data to bytes and store data in the buffer
			sendingDataBuffer = sentence.getBytes();

			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,sendingDataBuffer.length, serverAddress, SERVERPORT);

			// Sending UDP packet to the server
			clientSocket.send(sendingPacket);
					
			// Create buffer to receive data
			byte receivingDataBuffer [] = new byte[bufferSize];
					
			// Receive data packet from server
			DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,receivingDataBuffer.length);
			clientSocket.receive(receivingPacket);
				    
			// Unpack packet
			String wordsCounted = new String(receivingPacket.getData());
			System.out.println("Words Counted: " + wordsCounted);

			// Closing the socket connection with the server
			clientSocket.close();
					
		} 
		catch (Exception ex) 
		{

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
				
		System.out.println("\nProgram at client-side ends");

	}

}

