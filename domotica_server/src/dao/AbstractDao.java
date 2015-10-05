package dao;



import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao 
{
	private final Session sessao;
	
	public AbstractDao(Session sessao)
	{
		this.sessao = sessao;
	}
	
	protected void insert(Object obj)
	{
		Transaction trans = this.sessao.beginTransaction();
		this.sessao.save(obj);
		trans.commit();
	}
}
