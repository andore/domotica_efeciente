package id3m;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import common.CodigoModulo;
import common.Status;
import dao.AtuadorDao;
import dao.DbException;
import dao.Historico;
import dao.HistoricoDao;
import dao.Sensor;
import dao.SensorDao;

public class ID3<E> {

	final static Logger logger = Logger.getLogger(ID3.class);
	List<Registro> registros = new ArrayList<Registro>();
	List<Atributo> atributoAtuador = new ArrayList<Atributo>();
	
	private void getRegistros(int atuadorId, int modulo) throws DbException
	{
		HistoricoDao dao = new HistoricoDao();
		List<Historico> historicos = dao.getByAtuId(atuadorId);
		SensorDao daoSen = new SensorDao();
		AtuadorDao daoAtu = new AtuadorDao();
		
		for(Historico h: historicos)
		{
			int valAtuadorAtual = h.getStatus_atuador();
			
			/** pega os historicos por registro */
			List<Historico> histReg = dao.getByRegId(h.getRegistro());
			
			for(Historico hr: histReg)
			{
				Atributo a = new Atributo();
				a.setReg(hr.getRegistro());
				
				if(hr.getId_atuador() != null)
				{
					if(hr.getId_atuador() == atuadorId)
					{
						
						if(valAtuadorAtual != hr.getStatus_atuador())
						{
							throw new DbException("ERRO", "INCONSISTENCIA NA BASE");
						}
						
						a.setValAtu(hr.getStatus_atuador());
						a.setA(daoAtu.serachById(hr.getId_atuador()));
						atributoAtuador.add(a);
					}
					else
					{
						continue;
					}
					
				}
				else
				{
					
					Sensor sen = daoSen.serachById(hr.getId_sensor());
					if(CodigoModulo.getModuloSensor(sen.getCod()) == modulo || 
							CodigoModulo.getModuloSensor(sen.getCod()) == CodigoModulo.PRESENCA)
					{
						a.setS(sen);
						a.setValSen(hr.getValor_sensor());
						a.setValAtu(valAtuadorAtual);
					}
					else
					{
						continue;
					}
				}
				
				boolean regJaInserido = false;
				
				for(Registro r: registros)
				{
					if(r.getId_reg() == hr.getRegistro())
					{
						regJaInserido = true;
						r.getAtrs().add(a);
					}
				}
				
				if(!regJaInserido)
				{
					Registro r = new Registro();
					r.setId_reg(hr.getRegistro());
					
					List<Atributo> atrs = new ArrayList<Atributo>();
					atrs.add(a);
					
					r.setAtrs(atrs);
					registros.add(r);
				}	
			}	
		}
	}
	
	public Status start(int idAtuador, int modulo)
	{
		Entropia ent = new Entropia();
		
		try 
		{
			getRegistros(idAtuador, modulo);	
			logger.info("\n\n\nINICIANDO ID3 - "+ atributoAtuador.get(0).getA().getDescricao());
			
			double entropia = ent.cauculo(atributoAtuador, 0);
			logger.info("Entropia:" + entropia);
			
			List<Atributo> listAtrbSeparado [] = separaPorAtributo();
			
			for(int i=0; i<listAtrbSeparado.length; i++)
			{	
				if(listAtrbSeparado[i].get(0).getS() != null)
					logger.info("GANHO " + listAtrbSeparado[i].get(0).getS().getDescricao() +" : " + Ganho.calcula(entropia, listAtrbSeparado[i]));
			}
			
		} 
		catch (DbException e)
		{
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	
	public List<Atributo> [] separaPorAtributo()
	{
		List<Atributo> lista [] = new List[registros.get(0).getAtrs().size() -1];
		
		for(int i=0; i<lista.length; i++)
		{
			lista[i] = new ArrayList<Atributo>();
			for(int j=0; j<registros.size(); j++)
			{
				Atributo a = registros.get(j).getAtrs().get(i+1);
				if( a.getS()!= null )
				{
					lista[i].add(a);
				}
			}
		}
		
		return lista;
	}

	public static void main(String[] args) {
		ID3 teste = new ID3();
		teste.start(3, CodigoModulo.TEMPERATURA);
		logger.info("SUCESSO");
		System.exit(0);
	}

}
