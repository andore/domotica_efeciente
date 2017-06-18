package teste.simulador;

import interfaceGrafica.AbstractControleGui;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import common.EstMensagem;
import common.EstMensagemResp;
import common.EstMonitora;
import common.EstRespMonitora;
import common.EstruturaException;
import common.MensagemResp;
import dao.Arduino;
import dao.Atuador;
import dao.Sensor;
import teste.simulador.gui.ControleGuiSimuladorSensor;
import teste.simulador.gui.ListenerControleGuisimuladorSensor;

public class SimuladorSensoresAtuadores implements ListenerControleGuisimuladorSensor {
	private static final String IP_SERV = "localhost";
	private static final int PORTA_SERV = 9994;
	private JFrame j;
	private static final int OP_MONITORA = 2;
	private ControleGuiSimuladorSensor janelaSimulaSens;
	
	public static void main(String[] args) {
		SimuladorSensoresAtuadores s =  new SimuladorSensoresAtuadores();
		s.mostraJanelaSimuladorSensor();
	}

	private String enviaMsg(String msg) throws IOException {
		Socket clientSocket = null;
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		String resposta = null;

		try {
			System.out.println("Conectando com:" + IP_SERV + ":" + PORTA_SERV);
			clientSocket = new Socket(IP_SERV, PORTA_SERV);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			System.out.println("ENVIANDO MENSAGEM:[" + msg + "]");
			outToServer.writeBytes(msg + '\n');

			resposta = inFromServer.readLine();
			System.out.println("RESPOSTA SERVIDOR:[" + resposta + "]");
			trataResposta(resposta);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inFromServer.close();
			outToServer.close();
			clientSocket.close();
		}
		return resposta;
	}
	
	private void trataResposta(String msg) throws EstruturaException
	{
		EstRespMonitora resp = new EstRespMonitora(msg);
		janelaSimulaSens.atualizaTelaAtuadores(resp.getAtuadores());
	}
	
	private void mostraJanelaSimuladorSensor()
	{
		janelaSimulaSens = new ControleGuiSimuladorSensor(this);
		setJanela(janelaSimulaSens);	
	}
	
	public void setJanela(AbstractControleGui gui)
	{
		System.out.println("Setando Janela: " + gui.getJanela().getClass().getSimpleName());

		int posXAnt = 0;
		int posYAnt = 0;
		
		if(j!=null)
		{
			posXAnt = j.getX();
			posYAnt = j.getY();
			j.removeAll();
			j.dispose();
		}
		j = new JFrame();
		j.setBounds(posXAnt, posYAnt, 800, 600);
		j.add(gui.getJanela());
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void alteraValor(Arduino arduino) {
		EstMonitora msg = new EstMonitora();
		msg.setIdArduino(arduino.getId());
		msg.setOperacao(OP_MONITORA);
		msg.setQtdAtuador(arduino.getAtuadores().size());
		msg.setQtdSensor(arduino.getSensores().size());
		msg.setAtuadores(arduino.getAtuadores());
		msg.setSensores(arduino.getSensores());
		
		try
		{
			enviaMsg(msg.toText());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (EstruturaException e)
		{
			e.printStackTrace();
		}
	}

	public void alteraStatus(Arduino arduino) {
		EstMonitora msg = new EstMonitora(true);
		msg.setIdArduino(arduino.getId());
		msg.setOperacao(OP_MONITORA);
		msg.setQtdAtuador(arduino.getAtuadores().size());
		msg.setQtdSensor(arduino.getSensores().size());
		msg.setAtuadores(arduino.getAtuadores());
		msg.setSensores(arduino.getSensores());
		
		try
		{
			EstMensagem envia = new EstMensagem();
			envia.setOperacao(OP_MONITORA);
			envia.setIdArduino(arduino.getId());
			envia.setMsg(msg.toText());
			
			enviaMsg(envia.toText());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (EstruturaException e)
		{
			e.printStackTrace();
		}
		
	}
}
