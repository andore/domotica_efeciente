package dao;

import java.util.List;

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
	
	@SuppressWarnings("rawtypes")
	public Atuador serachById(int id)
	{
		String sql = "from " + Atuador.class.getSimpleName() + " C WHERE C.id_atuador = :id_atuador";		
		List atuadores = sessao.createQuery(sql).setParameter("id_atuador", id).list();
		if( atuadores!=null && atuadores.size() > 0)
		{
			if(atuadores.size() == 1)
			{
				return (Atuador) atuadores.get(0);
			}
			else
			{
				logger.warn("Exeiste mais de um Atuador com ID:" + id);
				return null;
			}
		}
		else
		{
			logger.warn("Nenhum Atuador encontrado com ID:" + id);
			return null;
		}
	}
}
