package dao;

import java.util.List;

import org.firebirdsql.jdbc.parser.JaybirdSqlParser.nextValueExpression_return;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hbn.ControleHbn;

public class CenarioDao extends AbstractDao {

	public CenarioDao() throws DbException {
		super();
	}
	
	public void insere(Cenario cenario) throws DbException
	{
		insere_tabela_cenario(cenario);
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
	
	private void insere_tabela_cenario(Cenario cenario) throws DbException
	{
		Transaction tx = sessao.beginTransaction();
		
		String sql = "INSERT INTO " + Cenario.class.getSimpleName() + 
		         " VALUES (:id_cenario,:id_arduino,:nome_cenario,:privado,:valor_iluminacao,:valor_temperatura,:id_usuario) ";
		
		SQLQuery query = super.sessao.createSQLQuery(sql);
		query.setParameter("id_cenario", getNextId());
		query.setParameter("id_arduino", cenario.getId_arduino());
		query.setParameter("nome_cenario", cenario.getNome_cenario());
		query.setBoolean("privado", cenario.isPrivado());
		query.setDouble("valor_iluminacao", cenario.getValor_iluminacao());
		query.setDouble("valor_temperatura", cenario.getValor_temperatura());
		query.setParameter("id_usuario", cenario.getId_usuario());
		
		logger.info("Linhas afetadas: " + query.executeUpdate());
		tx.commit();
	}
	
	private void insereSensorStatus(Cenario cenario)
	{
		Transaction tx = sessao.beginTransaction();
		
		
		
		for(Atuador s:cenario.getAtuadores())
		{
			String sql = "INSERT INTO " + "cenario_atuador" + 
			         " VALUES (:id_cenario,:id_atuador,:sta_atuador) ";
			
			SQLQuery query = super.sessao.createSQLQuery(sql);
			query.setParameter("id_cenario", cenario.getId_cenario());
			query.setParameter("id_atuador", s.getId());
			//query.setParameter("sta_atuador", s.get);
			
			logger.info("Linhas afetadas: " + query.executeUpdate());
		}
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cenario> loadCenario() throws DbException
	{
		return super.load("Cenario");		
	}

}
