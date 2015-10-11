package dao;



import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao 
{
	protected final Session sessao;
	
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
			trans.commit();
		}catch(Exception e)
		{
			throw new DbException ("Erro ao inserir no banco.",e.getMessage());
		}
		
	}
}
