package main;

import common.StrCadastraArduino;
import common.StructException;

public class DomServer {

	public static void main(String[] args) 
	{	
		ControleServer controleServer = new ControleServer();
		controleServer.init();	
	}
	
}
