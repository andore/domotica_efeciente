package main;

import hbn.ControleHbn;

import org.apache.log4j.Logger;

import teste.TesteDB;

public class DomServer {
	final static Logger logger = Logger.getLogger(DomServer.class);
	
	public static void main(String[] args) 
	{	
		logger.debug("Iniciando Servidor.");
		ControleHbn db = new ControleHbn();
		ControleServer controleServer = new ControleServer(db.getSession());
		controleServer.init();	
		
		//TesteDB testedb = new TesteDB();
		//testedb.TesteCarregaDados();
	}
	
}
