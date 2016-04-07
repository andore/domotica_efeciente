package dao;

import org.hibernate.Session;

import common.Status;

public class AtuadorDao extends AbstractDao {

	public AtuadorDao(Session sessao) {
		super(sessao);
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
