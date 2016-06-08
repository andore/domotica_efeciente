package dao;

import org.hibernate.Session;

import common.Status;

public class AtuadorDao extends AbstractDao {

	public AtuadorDao() throws DbException {
		super();
	}
	
	public void insere(Atuador atuador) throws DbException
	{
		super.insert(atuador);	
	}
	
	public void update(Atuador atuador) throws DbException
	{
		super.update(atuador);	
	}
}
