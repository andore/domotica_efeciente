package dao;

import org.hibernate.Session;

import common.Status;

public class AtuadorDao extends AbstractDao {

	public AtuadorDao() throws DbException {
		super();
	}
	
	public void insere(Atuador atuador) throws DbException
	{
		if(atuador != null)
		{
			if(atuador.getStatus()==null)
			{
				atuador.setStatus(Status.AUTO);
			}
			super.insert(atuador);
		}
		
	}

}
