package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao 
{
	protected final Session sessao;
	final static Logger logger = Logger.getLogger(AbstractDao.class);
	
	public AbstractDao(Session sessao)
	{
		this.sessao = sessao;
	}
	
	protected void insert(Object obj) throws DbException
	{
		
		try
		{
			Transaction trans = this.sessao.beginTransaction();
			this.sessao.save(obj);
			
			logger.debug("Inserindo no banco:" + obj.toString());
			trans.commit();
			sessao.close();
		}catch(Exception e)
		{
			sessao.close();
			throw new DbException ("Erro ao inserir no banco.",e.getMessage());
		}
		
	}
	

	@SuppressWarnings("rawtypes")
	protected List load(String nomeCalsse) throws DbException
	{
		try
		{
			Query query = sessao.createQuery("from " + nomeCalsse); 
			List list = query.list();
			return list;
		}
		catch (Exception e)
		{
			throw new DbException("Erro ao carregad dados do banco.", e.getMessage());
		}
		
	}
	
}
