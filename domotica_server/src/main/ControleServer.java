package main;

import java.util.ArrayList;
import java.util.List;

import teste.TesteDB;
import hbn.ControleHbn;
import common.Mensagem;
import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.Sensor;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener {
	
	private NetService net;
	private RoteadorOperacao roteador;
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
	}
	
	public void init()
	{
		net.start();	
	}
	
	public void netRecebe(Mensagem msg) {
		roteador.getOperacao(msg);
		//new TesteDB().testeInsere(msg);
	}

}
