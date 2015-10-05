package main;

import roteador.RoteadorOperacao;
import common.Mensagem;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener {
	
	private NetService net;
	private RoteadorOperacao roteador;
	
	public void init()
	{
		net = new NetService(this);
		net.start();
		roteador = new RoteadorOperacao();
	}
	
	public void netRecebe(Mensagem msg) {
		roteador.getOperacao(msg);
	}

}
