package hbn;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.DbException;
import teste.TesteLog;

public class ControleHbn {
	private final SessionFactory factory;
	final static Logger logger = Logger.getLogger(ControleHbn.class);
	private Session s;
	
	public ControleHbn() throws DbException
	{
		try
		{
			factory = new Configuration().configure().buildSessionFactory();
			s = factory.openSession();
		}
		catch(Exception e)
		{
			throw new DbException ("Erro ao inserir no banco.",e.getMessage());
		}
		
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
