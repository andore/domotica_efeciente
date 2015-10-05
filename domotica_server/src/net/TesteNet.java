package net;

import common.Mensagem;

public class TesteNet implements NetListener{
	
	public static void main(String[] args) {
		System.out.println("Ola Mundo!");
		TesteNet x = new TesteNet();
		x.testNet();
	}
	
	void testNet()
	{
		NetService comunicador = new NetService(this);
		comunicador.start();
	}

	public void netRecebe(Mensagem msg) {
		
		System.out.println("TesteNet:Mensagem recebida de ["+ msg.getIp() +"]:\n" 
				+ "idArduino:[" + msg.getIdArduino() + "]\n"
				+ "operacao:[" + msg.getOperacao() + "]\n"
				+ "mensagem:[" + msg.getMensagem() + "]\n"
		);
		
	}

}
