package main;

import hbn.ControleHbn;
import common.Arduino;
import common.Mensagem;
import dao.ArduinoDao;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener {
	
	private NetService net;
	private RoteadorOperacao roteador;
	private ControleHbn bd;
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
		bd = new ControleHbn();
	}
	
	public void init()
	{
		net.start();	
	}
	
	public void netRecebe(Mensagem msg) {
		roteador.getOperacao(msg);
		teste(msg);
	}
	
	
	void teste(Mensagem msg)
	{
		Arduino arduino = new Arduino();
		arduino.setId(1);
		arduino.setDescricao(msg.getMensagem());
		arduino.setIp(msg.getIp());;
		arduino.setQtdAtuador(0);
		arduino.setQtdAtuador(0);
		
		ArduinoDao perssist = new ArduinoDao(bd.getSession());
		perssist.InsereArduino(arduino);
		
	}

}
