package main;

import common.Mensagem;
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
	}

}
