package hbn;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import teste.TesteLog;

public class ControleHbn {
	private final SessionFactory factory;
	final static Logger logger = Logger.getLogger(ControleHbn.class);
	private Session s;
	
	public ControleHbn()
	{
		factory = new Configuration().configure().buildSessionFactory();
		s = factory.openSession();
	}
	
	public Session getSession()
	{		
		if(s==null)
		{
			s = factory.openSession();
		}
		else if(!s.isOpen())
		{
			s.clear();
			s = factory.openSession();
		}
		logger.debug("Abrindo Sessão no Banco.");
		return s;
	}
	
}
