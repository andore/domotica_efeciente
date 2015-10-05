package hbn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControleHbn {
	private final SessionFactory factory;
	
	public ControleHbn()
	{
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	public Session getSession()
	{
		return factory.openSession();
	}
	
}
