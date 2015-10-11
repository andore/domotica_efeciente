package main;

import hbn.ControleHbn;

import org.apache.log4j.Logger;

import common.StrCadastraArduino;
import common.StructException;

public class DomServer {
	final static Logger logger = Logger.getLogger(DomServer.class);
	
	public static void main(String[] args) 
	{	
		logger.debug("Iniciando Servidor.");
		ControleServer controleServer = new ControleServer();
		controleServer.init();	
	}
	
}
