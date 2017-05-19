package dao;

import org.hibernate.SQLQuery;

public class HistoricoDao extends AbstractDao{

	public HistoricoDao() throws DbException {
		super();
	}
	
	public void insere(Historico hist) throws DbException {
		if(hist != null) {
			super.insert(hist);
		}
	}
	
	public void atualiza(Historico hist) throws DbException {
		if(hist != null) {
			super.update(hist);
		}
	}
	
	public int getNextId(){
		try
		{
			String sql = "SELECT ID_HISTORICO FROM " + Historico.class.getSimpleName();
			SQLQuery query = super.sessao.createSQLQuery(sql);
			return Integer.parseInt(query.list().get(query.list().size()-1).toString()) +1;
		}
		catch(Exception e)
		{
			return 0;
		}	
	}
}