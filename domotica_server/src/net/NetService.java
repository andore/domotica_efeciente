package net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.Timer;

import org.apache.log4j.Logger;

import common.EstMensagem;
import common.EstMensagemResp;
import common.EstruturaException;
import common.MensagemResp;

public class NetService extends Thread  
{
	final static Logger logger = Logger.getLogger(NetService.class); 
	private NetListener listener;
	private int porta = 9994;
	private Scanner s = null;
	private PrintStream p = null;
	private ServerSocket servidor = null;
	private Socket cliente = null;
	private int tempoEspera;
	private Timer time;
	private boolean timeOut;
	
	
	public NetService (NetListener listener)
	{
		logger.debug("Iniciando Serviço de Comunicação.");
		timeOut = false;
		tempoEspera = 10000;
		this.listener = listener;
		try 
		{
			servidor = new ServerSocket(porta);
		} 
		catch (IOException e)
		{
			logger.debug("ERRO ao abrir socket.");
		}
		
	}
	
	
	private void tcpService()
	{
		String msgStr;
		
		try 
		{
			if(servidor == null || servidor.isClosed())
			{
				logger.debug("Recuperando conexao...");
				servidor = new ServerSocket(porta);
			}
			timeOut = false;
			
			logger.debug("Abrindo Socket Cliente e Aguardando conexao");
			cliente = servidor.accept();
			logger.debug("Conectado com:" + cliente.getInetAddress().getHostAddress());
			
			timeOut();
			
			s = new Scanner(cliente.getInputStream());
			p = new PrintStream(cliente.getOutputStream());
		    
			boolean isRequisicaoHtml = false;
			
			while (s.hasNextLine() && !timeOut) 
		    {
				time.restart();
				msgStr = s.nextLine();		    	
		    	logger.debug("Mensagem recebida de " + cliente.getInetAddress().getHostAddress() + " :[" + msgStr + "]");
		    	
		    	if(msgStr.contains("GET / HTTP/1.1") || msgStr.contains("GET /favicon.ico HTTP/1.1") )
				{
		    		isRequisicaoHtml = true;
		    		logger.info("Requisicao HTML");
				}
		    	else if(msgStr.equals("") && isRequisicaoHtml)
		    	{
		    		listener.netRecebe(msgStr);
					p.close();
					time.stop();
					break;
		    	}
				else if(!isRequisicaoHtml)
				{
					logger.info("Requisicao ARDUINO");
					
					EstMensagem msg = new EstMensagem(msgStr);
					msg.setIp(cliente.getInetAddress().getHostAddress());
					
					//Mensagem msg = new Mensagem(msgStr, cliente.getInetAddress().getHostAddress());
			    	logger.debug("\n"
							+"idArduino:[" + msg.getIdArduino() + "]\n"
							+ "operacao:[" + msg.getOperacao() + "]\n"
							//+ "mensagem:[" + msg.getMensagem() + "]\n"
					);
			    	time.stop();
					listener.netRecebe(msg);
				}
				else
				{
					logger.debug("Mensagem nao tratada");
				}
		    }
		}
		catch (IOException e)
		{
			logger.error("Erro Socket TCP", e);
		}
		catch (EstruturaException e)
		{
			MensagemResp resp =  null;
			resp = new MensagemResp();
			resp.setOperacao(0);
			resp.setIp(cliente.getInetAddress().getHostAddress());
			resp.setMensagem("001");
			
			p.println(resp.getMensagem());
			logger.error("Erro ao provessar mensagem:", e);
			
			logger.debug("Fechando Socket");
			s.reset();
			s.close();
		    try 
		    {
				servidor.close();
				cliente.close();
			} 
		    catch (IOException x)
		    {
				x.printStackTrace();
			}    
		}
		finally
		{
			time.stop();
			
//			try
//			{
//				time.stop();
//				logger.debug("Fechando Socket Cliente");
//				//cliente.close();
//			}
//			catch (IOException x)
//		    {
//				logger.warn("Erro fechando socket");
//			}    
//			catch(Exception e)
//			{
//				logger.debug("Erro timeout");
//			}
			
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
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				
			}
		}
		
	}
	
	public void envia(EstMensagemResp resp) throws EstruturaException
	{
		p.println(resp.toText());
		//logger.debug("Enviando mensagem para " + resp.getIp() + ":[" + resp.toText() + "]");
		/*while(true)
		{
			try 
			{
				Thread.sleep(1000);
				p.println(resp.getMensagem());
				logger.debug("Enviando mensagem para " + resp.getIp() + ":[" + resp.getMensagem() + "]");
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}*/
	}
	
	public void enviaHtml(MensagemResp resp) {
		p.println(resp.getMensagem());
		logger.debug("Enviando mensagem para " + resp.getIp() + ":[" + resp.getMensagem()+ "]");
	}
	
	public void timeOut()
	{
		ActionListener taskPerformer = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				logger.warn("Time-Out na Conexao");
				logger.debug("Encerrando Conexao com cliente:" + cliente.getInetAddress());
				
				s.reset();
				p.close();
				timeOut = true;
			}
		};
		time = new Timer(tempoEspera, taskPerformer);
		time.start();	
	}

	
	
}
