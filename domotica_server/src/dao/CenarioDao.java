package dao;

import java.util.List;

import org.firebirdsql.jdbc.FBSQLException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;

public class CenarioDao extends AbstractDao {
	
	Transaction tx;
	boolean primeiraPassagem;
	public CenarioDao() throws DbException {
		super();
		primeiraPassagem = true;
	}
	
	public void insere(Cenario cenario) throws DbException
	{
		tx = sessao.beginTransaction();
		
		cenario.setId_cenario(getNextId());
		insere_tabela_cenario(cenario);
		insereAtuadorStatus(cenario);
		
		tx.commit();
	}
	
	private int getNextId()
	{
		try
		{
			String sql = "SELECT ID_CENARIO FROM " + Cenario.class.getSimpleName();
			SQLQuery query = super.sessao.createSQLQuery(sql);
			return Integer.parseInt(query.list().get(query.list().size()-1).toString()) +1;
		}
		catch(Exception e)
		{
			return 0;
		}	
	}
	
	private void insere_tabela_cenario(Cenario cenario)
	{
		
		try
		{
			String sql = "INSERT INTO " + Cenario.class.getSimpleName() + 
			         " (id_cenario,id_arduino,nome_cenario,privado,valor_iluminacao,valor_temperatura,id_usuario) VALUES (:id_cenario,:id_arduino,:nome_cenario,:privado,:valor_iluminacao,:valor_temperatura,:id_usuario) ";
			
			//String sql = "INSERT INTO " + Cenario.class.getSimpleName() + 
	         //" (id_cenario,id_arduino,nome_cenario,privado,valor_iluminacao,valor_temperatura,id_usuario) select b.id_cenario, b.id_arduino,b.nome_cenario,b.privado,b.valor_iluminacao,b.valor_temperatura,b.id_usuario from b ";
			
			SQLQuery query = super.sessao.createSQLQuery(sql);
			query.setParameter("id_cenario", cenario.getId_cenario());
			query.setParameter("id_arduino", cenario.getId_arduino());
			query.setParameter("nome_cenario", cenario.getNome_cenario());
			query.setBoolean("privado", cenario.isPrivado());
			query.setDouble("valor_iluminacao", cenario.getValor_iluminacao());
			query.setDouble("valor_temperatura", cenario.getValor_temperatura());
			query.setParameter("id_usuario", cenario.getId_usuario());
			
			logger.info("Linhas afetadas: " + query.executeUpdate());
		}
		catch(ConstraintViolationException e)
		{
			if(e.getSQLState().contentEquals("23000") && primeiraPassagem)
			{
				primeiraPassagem = false;
				Usuario u = new Usuario();
				u.setId_usuario(0);
				u.setLogin("padrao");
				u.setNome_usuario("padrao");
				u.setPermissao_nivel(0);
				u.setSenha("");
				
				try 
				{
					UsuarioDao ud = new UsuarioDao();
					ud.insert(u);
					insere(cenario);
				} 
				catch (DbException e1)
				{
					
				}
			}
			else
			{
				//throws new DbException("Erro ao inserir na tabela cenario");
			}			
		}	
	}
	
	private void insereAtuadorStatus(Cenario cenario)
	{	
		if(cenario.getAtuadores() != null)
		{
			try
			{
				for(Atuador a:cenario.getAtuadores())
				{
					String sql = "INSERT INTO " + "cenario_atuador (id_cenario,id_atuador,sta_atuador)" + 
					         " VALUES (:id_cenario,:id_atuador,:sta_atuador) ";
					
					SQLQuery query = super.sessao.createSQLQuery(sql);
					query.setParameter("id_cenario", cenario.getId_cenario());
					query.setParameter("id_atuador", a.getId());
					query.setParameter("sta_atuador", a.getStatus());
					
					logger.info("Linhas afetadas: " + query.executeUpdate());
				}
			}
			catch(SQLGrammarException e)
			{
				if(e.getCause() instanceof FBSQLException )
				{
					FBSQLException f = (FBSQLException) e.getCause();
					if(f.getSQLState().contentEquals("42000"))
					{
						Transaction at = sessao.beginTransaction();
						String sql = "ALTER TABLE cenario_atuador ADD atuadro_sta INT default 2";
						SQLQuery query = super.sessao.createSQLQuery(sql);
						logger.info("Linhas afetadas: " + query.executeUpdate());
						at.commit();
						insereAtuadorStatus(cenario);
						tx = sessao.beginTransaction();
					}
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Cenario serachById(int id_cenario)
	{
		String sql = "from " + Cenario.class.getSimpleName() + " C WHERE C.id_cenario = :id_cenario";		
		List cenarios = sessao.createQuery(sql).setParameter("id_cenario", id_cenario).list();
		if( cenarios!=null && cenarios.size() > 0)
		{
			if(cenarios.size() == 1)
			{
				return (Cenario) cenarios.get(0);
			}
			else
			{
				logger.warn("Exeiste mais de um Cenario com ID:" + id_cenario);
				return null;
			}
		}
		else
		{
			logger.warn("Nenhum Cenario encontrado com ID:" + id_cenario);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cenario> loadCenario() throws DbException
	{
		return super.load("Cenario");		
	}

}
