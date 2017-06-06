package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import hbn.ControleHbn;

public abstract class AbstractDao 
{
	public final Session sessao;
	final static Logger logger = Logger.getLogger(AbstractDao.class);
	private ControleHbn hbn;
	
	public AbstractDao() throws DbException
	{
		hbn = new ControleHbn();
		this.sessao = hbn.getSession();
	}
	
	protected void insert(Object obj) throws DbException
	{
		
		try
		{
			Transaction trans = this.hbn.getSession().beginTransaction();
			this.hbn.getSession().save(obj);
			
			logger.debug("Inserindo no banco:" + obj.toString());
			trans.commit();
		}
		catch(ConstraintViolationException e)
		{
			throw new DbException ("Erro ao inserir no banco.",e.getMessage(), e.getSQLState());
		}
		catch(Exception e)
		{
			logger.error(e);
			throw new DbException ("Erro ao inserir no banco.",e.getMessage());
		}
		
	}
	
	public void update(Object obj) throws DbException
	{
		
		try
		{
			Transaction trans = this.hbn.getSession().beginTransaction();
			this.hbn.getSession().update(obj);
			
			logger.debug("Atualizando registro no banco:" + obj.toString());
			trans.commit();
			
		}
		catch(ConstraintViolationException e)
		{
			throw new DbException ("Erro ao tentar atualizar registro no banco.",e.getMessage(), e.getSQLState());
		}
		catch(Exception e)
		{
			throw new DbException ("Erro ao tentar atualizar registro no banco.",e.getMessage());
		}
		
	}
	

	@SuppressWarnings("rawtypes")
	public List load(String nomeCalsse) throws DbException
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
