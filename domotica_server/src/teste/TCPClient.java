package teste;

import java.io.*;
import java.net.*;

class TCPClient
{
	public static void main(String argv[]) throws Exception
	{
		BufferedReader inFromUser = null;
		Socket clientSocket = null;
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		
		while(true)
		{
			try
			{
				System.out.println("Conectando.....");
				String sentence;
				String modifiedSentence;
				inFromUser = new BufferedReader( new InputStreamReader(System.in));
				clientSocket = new Socket("localhost", 9994);
				outToServer = new DataOutputStream(clientSocket.getOutputStream());
				inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				System.out.println("DIGITE UMA MEBSAGEM:");
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);
			}
			catch(Exception e)
			{
				
			}
			finally
			{
				inFromServer.close();
				inFromUser.close();
				outToServer.close();
				clientSocket.close();
			}
		}
 	}
}