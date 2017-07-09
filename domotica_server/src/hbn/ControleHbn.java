package hbn;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.DbException;

public class ControleHbn {
	private static SessionFactory factory;
	final static Logger logger = Logger.getLogger(ControleHbn.class);
	private Session s;
	
	public ControleHbn() throws DbException
	{
		
	}
	
	public Session getSession()
	{		
		if(s==null)
		{
			//logger.debug("Abrindo Sessão no Banco.");
			if(factory == null)
			{
				factory = new Configuration().configure().buildSessionFactory();
			}
			s = factory.openSession();
		}
		else if(!s.isOpen())
		{
			logger.debug("Abrindo Sessão no Banco.");
			if(factory == null)
				factory = new Configuration().configure().buildSessionFactory();
			s.clear();
			s = factory.openSession();
		}
		
		return s;
	}
	
}
