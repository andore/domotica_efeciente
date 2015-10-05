package dao;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;

public abstract class AbstractDao 
{
	private final Session sessao;
	
	public AbstractDao(Session sessao)
	{
		this.sessao = sessao;
	}
	
	protected void insert(Object obj)
	{
		try {
			Transaction trans = (Transaction) this.sessao.beginTransaction();
			this.sessao.save(obj);
			trans.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
