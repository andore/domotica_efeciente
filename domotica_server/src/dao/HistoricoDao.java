package dao;

import java.util.List;

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
	
	public List<Historico> getByAtuId(int id)
	{
		
		String sql = "from " + Historico.class.getSimpleName() + " C WHERE C.id_atuador = :id_atuador";		
		List historicos = sessao.createQuery(sql).setParameter("id_atuador", id).list();
		
		if( historicos!=null && historicos.size() > 0)
		{
			return historicos;
		}
		else
		{
			logger.warn("Nenhum Historico encontrado com ID:" + id);
			return null;
		}
	}
	
	public List<Historico> getByRegId(int id)
	{
		String sql = "from " + Historico.class.getSimpleName() + " C WHERE C.registro = :registro order by C.id_sensor";		
		List historicos = sessao.createQuery(sql).setParameter("registro", id).list();
		
		if( historicos!=null && historicos.size() > 0)
		{
			return historicos;
		}
		else
		{
			logger.warn("Nenhum Historico encontrado com ID:" + id);
			return null;
		}
	}
}