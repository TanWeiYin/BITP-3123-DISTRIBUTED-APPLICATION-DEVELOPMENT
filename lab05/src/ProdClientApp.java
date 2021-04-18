

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Scanner;


public class ProdClientApp {

	public static void main(String[] args) 
	{
		DecimalFormat df = new DecimalFormat("0.00");
		 
		ItemProduct prod = new ItemProduct();
		
		Scanner writting = new Scanner(System.in);
		
		System.out.print("Item Name: ");
		String obj = writting.nextLine();
		
		System.out.print("Item Price: ");
		float price = writting.nextFloat();
		
		prod.setName(obj);
		prod.setIPrice(price);
		
		try
		{
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			Socket socket = new Socket(serverAddress, portNo);
			
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("Send object to server: " + prod);
			
			objectOS.writeObject(prod);
			objectOS.flush();
			
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			prod = (ItemProduct) objectIS.readObject();
			
			if(prod.getErrorTag() == true)
			{
				System.out.println("Object created");
				System.out.println("Product Name: " + prod.getName());
				System.out.println("ID Assigned: " + prod.getItemProductID());
				System.out.println("Product Price: " + df.format(prod.getPrice()));
			}
			else
			{
				System.out.println("Object failed to be created");
				System.out.println("Error: " + prod.getErrorMsg());
			}
			
			objectOS.close();
			objectIS.close();
			socket.close();
			
		}
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

	}

}
