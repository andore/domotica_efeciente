package teste;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.CodAtuador;
import common.CodigoSensores;
import common.EstCadastra;
import common.EstruturaException;
import dao.Atuador;
import dao.Sensor;

class TCPClient
{
	
	   private static int contSensor = 0;
	   private static int contAtuador = 0;
	   private static int contArduino = 0;
	   private static final int PORTA = 9994;
	   private static final int OP_CADASTRA = 1;
	
	public static void main(String argv[]) throws Exception
	{	
		TCPClient teste = new TCPClient();
		String msg = "-1";
		
		while(true)
		{
			msg = teste.criaMensagemCadastra();
	
			if(msg!=null && msg != "")
				teste.enviaMsg(msg);
			else
				break;
		}
 	}
	
	
	public String enviaMsg(String msg) throws IOException
	{
		Socket clientSocket = null;
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		String resposta = "";
		
		try
		{
			System.out.println("Conectando PORTA:" + PORTA);
			clientSocket = new Socket("localhost", PORTA);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
			outToServer.writeBytes(msg + '\n');
			resposta = inFromServer.readLine();
			JOptionPane.showMessageDialog(new JFrame("RESPOSTA"), "RESPOSTA:[" + resposta + "]");
			
		}
		catch(Exception e)
		{
			System.out.println("ERRO: " + e.getMessage());
		}
		finally
		{
			inFromServer.close();
			outToServer.close();
			clientSocket.close();
		}
		return resposta;
	}
	
	
	public String criaMensagemCadastra()
	{	   
		 EstCadastra msgCadastra = new EstCadastra();
		 msgCadastra.setOperacao(OP_CADASTRA);
		 
		 try 
		 {	
			 msgCadastra.setIdArduino(Integer.parseInt(JOptionPane.showInputDialog("ID Arduino","1")));
			 msgCadastra.setDescricaoArduino(JOptionPane.showInputDialog("Descricao Arduino","DescricaoArduino"));
			 msgCadastra.setQtdSensor(Integer.parseInt(JOptionPane.showInputDialog("Qtd Sensor","1")));
		 
			 for(int i=0; i<msgCadastra.getQtdSensor(); i++ )
			 {
				 if(msgCadastra.getSensores() == null)
				 {
					 List<Sensor> sensores = new ArrayList<Sensor>();
					 msgCadastra.setSensores(sensores);
				 }
				 
				 Sensor s = new Sensor();
				 s.setId(Integer.parseInt(JOptionPane.showInputDialog("Sensor " + (i+1) + " de " + msgCadastra.getQtdSensor() + " \n\n ID Sensor","1")));
				 s.setCod(Integer.parseInt(JOptionPane.showInputDialog("Sensor " + (i+1) + " de " + msgCadastra.getQtdSensor() + " \n\n Codigo Tipo de Sensor:\n" + getDescricaoSensor(CodigoSensores.class) + '\n',"1")));
				 s.setDescricao(JOptionPane.showInputDialog("Sensor " + (i+1) + " de " + msgCadastra.getQtdSensor() + " \n\n Descricao Sensor","DescricaoSensor"));
				 msgCadastra.getSensores().add(s);
			 } 
			 msgCadastra.setQtdAtuador(Integer.parseInt(JOptionPane.showInputDialog("Qtd Atuador","1")));
			 
			 for(int i=0; i<msgCadastra.getQtdAtuador(); i++ )
			 {
				 if(msgCadastra.getAtuadores() == null)
				 {
					 List<Atuador> a = new ArrayList<Atuador>();
					 msgCadastra.setAtuadores(a);
				 }
				 
				 Atuador a = new Atuador();
				 a.setId(Integer.parseInt(JOptionPane.showInputDialog("Atuador " + (i+1) + " de " + msgCadastra.getQtdAtuador() + " \n\n ID Atuador","1")));
				 a.setCod(Integer.parseInt(JOptionPane.showInputDialog("Atuador " + (i+1) + " de " + msgCadastra.getQtdAtuador() + " \n\n Codigo Tipo de Atuador:\n" + getDescricaoSensor(CodAtuador.class) + '\n',"1")));
				 a.setDescricao(JOptionPane.showInputDialog("Atuador " + (i+1) + " de " + msgCadastra.getQtdAtuador() + " \n\n Descricao Atuador","DescricaoAtuador"));
				 msgCadastra.getAtuadores().add(a);
			 }
			 StringBuffer print = new StringBuffer();			 
			 print.append("OPERACAO"  + msgCadastra.getOperacao() + '\n');
			 print.append("ID Aduino:"  + msgCadastra.getIdArduino() + '\n');
			 print.append("Descrico Arduino:"  + msgCadastra.getDescricaoArduino() + '\n');
			 print.append("Qtd Sensor:"  + msgCadastra.getQtdSensor() + '\n');
			 
			 for(Sensor s: msgCadastra.getSensores())
			 {
				 print.append("ID Sensor:"  + s.getId() + '\n');
				 print.append("Codigo Tipo Sensor:"  + s.getCod() + '\n');
				 print.append("Descricao Sensor:"  + s.getDescricao() + '\n');
			 }
			 print.append("Qtd Atuador:"  + msgCadastra.getQtdAtuador() + '\n');
			 
			 for(Atuador a: msgCadastra.getAtuadores())
			 {
				 print.append("ID Atuador:"  + a.getId() + '\n');
				 print.append("Codigo Tipo Atuador:"  + a.getCod() + '\n');
				 print.append("Descricao Atuador:"  + a.getDescricao() + '\n');
			 }
			 
			 JOptionPane.showMessageDialog(new JFrame("MENSAGEM CRIADA"), print.toString() + "MENSAGEM CRIADA:[" + msgCadastra.toText() + "]");
			 return msgCadastra.toText();
		 }
		 catch(Exception e)
		 {
			 
			 System.err.println("SAIR\n" + e.getMessage());
			 return "";
		 }
	}
	
	
	private String getDescricaoSensor(Class classe)
	{
		
		StringBuffer s = new StringBuffer();
		
		for(int i=0; i<classe.getDeclaredFields().length; i++)
		{
			try {
				s.append(classe.getDeclaredFields()[i].getName() + ':');
				s.append(classe.getDeclaredFields()[i].getInt(null) + "\n");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
		
		return s.toString();
		
	}
}