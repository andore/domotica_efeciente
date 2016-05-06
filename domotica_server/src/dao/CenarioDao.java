package dao;

import java.util.List;
import org.hibernate.Session;

import hbn.ControleHbn;

public class CenarioDao extends AbstractDao {

	public CenarioDao() {
		super();
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
