package dao;

import org.hibernate.Session;

public class AtuadorDao extends AbstractDao {

	public AtuadorDao(Session sessao) {
		super(sessao);
	}
	
	public void insereArduino(Atuador atuador)
	{
		if(atuador != null)
		{
			super.insert(atuador);
		}
		
	}

}
