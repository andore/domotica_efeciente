package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.Mensagem;
import common.MensagemException;

public class NetService extends Thread  
{
	private DatagramSocket serverSocket;
	private DatagramPacket pacoteRecebido;
	private byte[] dados; 
	private NetListener listener;
	
	public NetService (NetListener listener)
	{
		System.out.println("Iniciando Serviço de Comunicação.");
		this.listener = listener;
	}
	
	
	 public void run() 
	 {
		 //todo passar porta como parametro
		 recebe(9999);
	 }
	
	private void recebe(int porta)
	{	
		System.out.println("Abrindo socket na porta:" + porta);
		while(true)
		{
			try 
			{
				serverSocket = new DatagramSocket(porta);
				dados = new byte[1024];
				pacoteRecebido = new DatagramPacket(dados, dados.length);
				serverSocket.receive(pacoteRecebido);
				serverSocket.close();
				System.out.println("Mensagem recebida de " + pacoteRecebido.getAddress().toString().substring(1) + " :[" + new String(pacoteRecebido.getData())+"]");
				Mensagem msg = new Mensagem(new String(pacoteRecebido.getData()), pacoteRecebido.getAddress().toString().substring(1));
				
				System.out.println("\n"
						+"idArduino:[" + msg.getIdArduino() + "]\n"
						+ "operacao:[" + msg.getOperacao() + "]\n"
						+ "mensagem:[" + msg.getMensagem() + "]\n"
				);
				
				listener.netRecebe(msg);
				
			} 
			catch (MensagemException e)
			{
				System.out.println(e.getMessage());
			}
			catch (SocketException e) 
			{		
				Logger.getLogger(NetService.class.getName()).log(Level.SEVERE, null, e);
			} 
			catch (IOException e) 
			{
				Logger.getLogger(NetService.class.getName()).log(Level.SEVERE, null, e);
			}
			finally
			{
				serverSocket.close();
			}
		}
		
	}
	
	
}
