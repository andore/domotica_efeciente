package hbn;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import teste.TesteLog;

public class ControleHbn {
	private final SessionFactory factory;
	final static Logger logger = Logger.getLogger(ControleHbn.class);
	
	
	public ControleHbn()
	{
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	public Session getSession()
	{		
		
		logger.debug("Abrindo Sessão no Banco.");
		return factory.openSession();
	}
	
}
