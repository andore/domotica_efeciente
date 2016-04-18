package net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

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
	private int porta = 9991;
	private Scanner s = null;
	private PrintStream p = null;
	
	public NetService (NetListener listener)
	{
		logger.debug("Iniciando Serviço de Comunicação.");
		this.listener = listener;
	}
	
	
	private void tcpService()
	{
		ServerSocket servidor = null;
		Socket cliente = null;
		String msgStr;
		
		try 
		{
			servidor = new ServerSocket(porta);
			logger.debug("Aguardando conexao");
			cliente = servidor.accept();
			logger.debug("Conectado com:" + cliente.getInetAddress().getHostAddress());
			cliente.getInetAddress().getHostAddress();
			s = new Scanner(cliente.getInputStream());
			p = new PrintStream(cliente.getOutputStream());
		    
			while (s.hasNextLine()) 
		    {
		    	msgStr = s.nextLine();		    	
		    	logger.debug("Mensagem recebida de " + cliente.getInetAddress().getHostAddress() + " :[" + msgStr + "]");
		    	
		    	Mensagem msg = new Mensagem(msgStr, cliente.getInetAddress().getHostAddress());
		    	logger.debug("\n"
						+"idArduino:[" + msg.getIdArduino() + "]\n"
						+ "operacao:[" + msg.getOperacao() + "]\n"
						+ "mensagem:[" + msg.getMensagem() + "]\n"
				);
		    	listener.netRecebe(msg);
		       //logger.info(s.nextLine());
		       //p.println("Surdo eh a voh =P");
		       //logger.debug("Enviado");
		    }
		} 
		catch (IOException e)
		{
			logger.error("Erro Socket TCP", e);
		}
		catch (MensagemException e)
		{
			MensagemResp resp =  null;
			resp = new MensagemResp();
			resp.setOperacao(0);
			resp.setIp(cliente.getInetAddress().getHostAddress());
			resp.setMensagem("001");
			p.println(resp.getMensagem());
			logger.error("Erro ao provessar mensagem:", e);
			//envia(resp);
		}
		finally
		{
			logger.debug("Fechando Socket");
			s.close();
		    try 
		    {
				servidor.close();
				cliente.close();
			} 
		    catch (IOException e)
		    {
			
				e.printStackTrace();
			}    
		}
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
			tcpService();
			
			/*
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
				while(true)
				{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MensagemResp resp =  null;
					resp = new MensagemResp();
					resp.setOperacao(0);
					resp.setIp("192.168.10.59");
					resp.setMensagem(" OlA Mundo");
					//logger.error("Erro ao provessar mensagem:", e);
					envia(resp);
				}
				
				
				
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
			}*/
		}
		
	}
	
	public void envia(MensagemResp resp)
	{
		p.println(resp.getMensagem());
		logger.debug("Enviando mensagem para " + resp.getIp() + ":[" + resp.getMensagem() + "]");
	}
	
	
}
