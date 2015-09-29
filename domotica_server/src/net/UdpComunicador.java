package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UdpComunicador extends Thread  {
	private DatagramSocket serverSocket;
	private DatagramPacket pacoteRecebido;
	private byte[] dados; 
	
	 public void run() 
	 {
		 init(9999);
		 //recebe();
	 }
	
	private void init(int porta)
	{	
		System.out.println("SERVER:Abrindo socket na porta:" + porta);
		while(true)
		{
			try {
				serverSocket = new DatagramSocket(porta);
				dados = new byte[1024];
				pacoteRecebido = new DatagramPacket(dados, dados.length);
				serverSocket.receive(pacoteRecebido);
				System.out.println("SERVER:Mensagem recebida de " + pacoteRecebido.getAddress().toString().substring(1) + " :" + new String(pacoteRecebido.getData()));
				
				
				
				serverSocket.close();
				
			} catch (SocketException e) {		
				Logger.getLogger(UdpComunicador.class.getName()).log(Level.SEVERE, null, e);
			} catch (IOException e) {
				Logger.getLogger(UdpComunicador.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
	}
	
}
