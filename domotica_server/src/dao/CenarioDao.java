package dao;

import java.util.List;
import org.hibernate.Session;

public class CenarioDao extends AbstractDao {

	public CenarioDao(Session sessao) {
		super(sessao);
		
	}
	
	public void insere(Cenario cenario) throws DbException
	{
		if(cenario != null)
		{
			super.insert(cenario);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cenario> loadCenario() throws DbException
	{
		return super.load("Cenario");		
	}

}
