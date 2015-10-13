package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

import main.RoteadorOperacao;
import common.Mensagem;
import common.MensagemException;
import common.MensagemResp;

public class NetService extends Thread  
{
	final static Logger logger = Logger.getLogger(NetService.class);
	private DatagramSocket serverSocket;
	private DatagramPacket pacoteRecebido;
	private byte[] dados; 
	private NetListener listener;
	private int porta = 9999;
	
	public NetService (NetListener listener)
	{
		logger.debug("Iniciando Serviço de Comunicação.");
		this.listener = listener;
	}
	
	
	 public void run() 
	 {
		 //todo passar porta como parametro
		 recebe(porta);
	 }
	
	private void recebe(int porta)
	{	
		logger.debug("Abrindo socket na porta:" + porta);
		while(true)
		{
			try 
			{
				serverSocket = new DatagramSocket(porta);
				dados = new byte[1024];
				pacoteRecebido = new DatagramPacket(dados, dados.length);
				serverSocket.receive(pacoteRecebido);
				//serverSocket.close();
				logger.debug("Mensagem recebida de " + pacoteRecebido.getAddress().toString().substring(1) + " :[" + new String(pacoteRecebido.getData())+"]");
				Mensagem msg = new Mensagem(new String(pacoteRecebido.getData()), pacoteRecebido.getAddress().toString().substring(1));
				
				logger.debug("\n"
						+"idArduino:[" + msg.getIdArduino() + "]\n"
						+ "operacao:[" + msg.getOperacao() + "]\n"
						+ "mensagem:[" + msg.getMensagem() + "]\n"
				);
				
				listener.netRecebe(msg);
				
			} 
			catch (MensagemException e)
			{
				logger.error(e);
			}
			catch (SocketException e) 
			{		
				logger.error(e);
			} 
			catch (IOException e) 
			{
				logger.error(e);
			}
			finally
			{
				serverSocket.close();
			}
		}
		
	}
	
	public void envia(MensagemResp resp)
	{
		try 
		{
			if(serverSocket == null)
			{
				serverSocket = new DatagramSocket(porta);
			}
			else
			{
				InetAddress IPAddress = pacoteRecebido.getAddress();
				int port = pacoteRecebido.getPort();	
				byte [] data = resp.getPacote().getBytes() ;
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, port);                   
				serverSocket.send(sendPacket);
				
			}
			
		} 
		catch (SocketException e) 
		{
			logger.error("Falha ao enviar mensagem.", e);
		}
		catch (IOException e)
		{
			logger.error("Falha ao enviar mensagem.", e);
		} 
		
	}
	
	
}
