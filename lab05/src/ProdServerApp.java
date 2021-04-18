

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProdServerApp {

	public static void main(String[] args) throws IOException
	{
		try
		{
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
				
			System.out.println("waiting connections");
			int totalRequest = 0;
			
			while(true)
			{
				System.out.println("connected");
				Socket clientSocket = serverSocket.accept();
				
				ObjectInputStream objectIS = new ObjectInputStream(clientSocket.getInputStream());
				
				ItemProduct prod = (ItemProduct) objectIS.readObject();
				
				Pattern patternValid = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
				Matcher matchingString = patternValid.matcher(prod.getName());
				boolean ContainSpecialChar = matchingString.find();

				boolean priceIsPossitive = (prod.getPrice() > 0);
				
				boolean noError = (!ContainSpecialChar && priceIsPossitive);
				
				System.out.println("Data processed");
				
				if(noError == true)
				{
					prod.setErrorTag(true);
					
					int randomNumber = ( int )( Math.random() * 9999 );

					if( randomNumber <= 1000 ) 
					{
					    randomNumber = randomNumber + 1000;
					    
					}
					prod.setErrorTag(true);
					
					prod.setItemProductId(randomNumber);
					
					prod.setIPrice((float)(Math.round(((double)prod.getPrice()) * 100.0)/100.0));
				}
				else
				{
					prod.setErrorTag(false);
					
					if(ContainSpecialChar == true && priceIsPossitive == false)
					{
						prod.setErrorMsg("Product name contains special characters. \nPrice is in negative numbers"); 
					}
					else if(ContainSpecialChar == true)
					{
						prod.setErrorMsg("Product name contains special characters.");
					}
					else
					{
						prod.setErrorMsg("Price is in negative numbers.");
					}
				}
				
				ObjectOutputStream objectOS = new ObjectOutputStream(clientSocket.getOutputStream());
				objectOS.writeObject(prod);
				
				objectIS.close();
				objectOS.close();
				System.out.println("Prossesed...sending back results");
			}
		}
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		

	}

}
