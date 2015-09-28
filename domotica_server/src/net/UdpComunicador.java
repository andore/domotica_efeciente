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
		 recebe();
	 }
	
	private void init(int porta)
	{
		try {
			serverSocket = new DatagramSocket(porta);
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.INFO, null, 
					"Abrindo socket na porta:" + porta);
			
			dados = new byte[1024];
			pacoteRecebido = new DatagramPacket(dados, dados.length);
			serverSocket.receive(pacoteRecebido);
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.INFO, null, 
					pacoteRecebido.getData());
			
		} catch (SocketException e) {		
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public String recebe()
	{
		dados = new byte[1024];
		pacoteRecebido = new DatagramPacket(dados, dados.length);
		
		try {
			serverSocket.receive(pacoteRecebido);
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.INFO, null, 
			"Pacote recebido:" + pacoteRecebido.getData());
			return pacoteRecebido.getData().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(UdpComunicador.class.getName()).log(Level.SEVERE, null, e);
			return "";
		}
		
	}
	
}
