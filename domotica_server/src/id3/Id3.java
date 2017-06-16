package id3;

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

public class Id3 {

	final static Logger logger = Logger.getLogger(Id3.class);
	List<Atributo> atributoAtuador = new ArrayList<Atributo>();
	List<Integer> atributosUtilizados = new ArrayList<Integer>();
	
	private double entropia=0;
	
	private List<Registro> getRegistrosBanco(int atuadorId, int modulo) throws DbException
	{
		HistoricoDao dao = new HistoricoDao();
		List<Historico> historicos = dao.getByAtuId(atuadorId);
		SensorDao daoSen = new SensorDao();
		AtuadorDao daoAtu = new AtuadorDao();
		List<Registro> registros = new ArrayList<Registro>();
		
		if(historicos==null)
		{
			logger.warn("Não há historico para atuador ID = " + atuadorId);
		}
		else{
		
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
						r.setValAtu(hr.getStatus_atuador());
						
						List<Atributo> atrs = new ArrayList<Atributo>();
						atrs.add(a);
						
						r.setAtrs(atrs);
						registros.add(r);
					}	
				}	
			}
		}
		return registros;
	}
	
	public No getArvore(int idAtuador, int modulo)
	{
		List<Registro> registros;
		No raiz = new No();
		try 
		{
			registros = getRegistrosBanco(idAtuador, modulo);	
			if(atributoAtuador==null || atributoAtuador.size()==0)
			{
				logger.info("Nada a fazer");
				return null;
			}
			else
			{
				logger.info("\n\n\nINICIANDO ID3 - "+ atributoAtuador.get(0).getA().getDescricao());
				entropia = Entropia.cauculo(atributoAtuador, 0);
				logger.info("Entropia:" + entropia);
				raiz.setIdAtuador(idAtuador);
				raiz.setReg(registros);
				criaArvore(raiz);
			}
		}
		
		catch (DbException e)
		{
			e.printStackTrace();
			return null;
		}
			
		return raiz;
	}
	
	private boolean isAtributoUsado(int id)
	{
		for(Integer i:atributosUtilizados)
		{
			if(id == i.intValue())
			{
				return true;
			}
		}
		return false;
	}
	
	
	private void arvore(Atributo atrRaiz)
	{
		//raiz.setReg(registros); 
		System.out.println("\n\n");
		
		
		for(int i=0; i<10; i++)
		{
			No f = new No();
			//f.setReg(getSubListaRegistros(registros, atrRaiz.getS().getId(), i)); 
		}
	}
	
	
	private void criaArvore(No no)
	{
		if(no.getReg() != null && no.getReg().size()>0)
		{
			List<Registro> registros = no.getReg();
			Atributo atrM = getMaiorGanho(registros);
			no.setAtr(atrM);
			if(atrM!=null)
			{
				for(int i=0 ; i<3; i++)
				{
					No n = new No();
					n.setReg(getSubListaRegistros(registros, atrM.getS().getId(), i));
					
					if(no.getFilhos()==null)
					{
						List<No> nos = new ArrayList<No>();
						no.setFilhos(nos);
					}
					if(n.getReg().size()>0)
					{
						no.getFilhos().add(n);
						n.setValPai(i);
					}
				}
				
				for(No n: no.getFilhos()){
					if(!isNoFolha(n))
					{
						criaArvore(n);
					}
				}
			}
			else
			{
				logger.info("Nao há mais ganho final, FIM DA ARVORE");
			}	
		}
		else
		{
			logger.info("Acabou os registros, FIM DA ARVORE");
		}
	}
	
	public boolean isNoFolha(No no)
	{
		int valorAtuador;
		
		if(no.getReg()==null || no.getReg().size()==0)
		{
			return true;
		}
		else
		{
			valorAtuador = no.getReg().get(0).getValAtu();
			
			for(Registro r:no.getReg())
			{
				if(r.getValAtu() != valorAtuador)
				{
					return false;
				}
			}
			
			no.setValFolha(valorAtuador);
			
			return true;
		}
	}
	
	private Atributo getMaiorGanho(List<Registro> registros)
	{
		double ganho = 0;
		int indiceAtributoMiorGanho = -1;
		
		List<Atributo> listAtrbSeparado [] = separaPorAtributo(registros);
		
		for(int i=0; i<listAtrbSeparado.length; i++)
		{	
			if(listAtrbSeparado[i].get(0).getS() != null && !isAtributoUsado(listAtrbSeparado[i].get(0).getS().getId()))
			{
				double ganhoAtual = Ganho.calcula(entropia, listAtrbSeparado[i]);
				if(ganhoAtual > ganho)
				{
					ganho = ganhoAtual;
					indiceAtributoMiorGanho = i;
				}
				
				//logger.info("GANHO " + listAtrbSeparado[i].get(0).getS().getDescricao() +" : " + ganhoAtual);
			}
		}
		
		if(indiceAtributoMiorGanho<0)
		{
			return null;
		}
		else
		{
			logger.info("Maior Ganho:" + listAtrbSeparado[indiceAtributoMiorGanho].get(0).getS().getDescricao() +" = "+ ganho);
			atributosUtilizados.add(listAtrbSeparado[indiceAtributoMiorGanho].get(0).getS().getId());
			
			return listAtrbSeparado[indiceAtributoMiorGanho].get(0);
		}
	}
	
	
	public List<Registro> getSubListaRegistros(List<Registro> registros, int idAtr, int valor)
	{
		logger.info("Registros sub-arvore com valor:" + valor);
		List<Registro> list = new ArrayList<Registro>();
		
		for(Registro r: registros)
		{
			
			for(Atributo a:r.getAtrs())
			{
				if(a.getS()!=null && a.getS().getId() == idAtr && a.getValSen() == valor)
				{	
					for(Integer i:atributosUtilizados)
					{
						logger.info("Registro:" + r.getId_reg());
						list.add(r);
					}
				}
			}	
		}
		
		if(list.size()>0)
			System.out.println("\n");
		
		return list;
	}
	
	public List<Atributo> [] separaPorAtributo(List<Registro> registros)
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
		Id3 teste = new Id3();
		teste.imprimeArvore(teste.getArvore(3, CodigoModulo.TEMPERATURA));
		logger.info("SUCESSO");
		System.exit(0);
	}
	
	private void imprimeArvore(No no)
	{
		if(no.getAtr()!=null && no.getAtr().getS()!=null)
		{
			System.out.print(no.getAtr().getS().getDescricao() + "  ");
		}
		if(no.getValFolha()!=null)
		{
			System.err.print(no.getValFolha() + "   ");
		}
		
		
		if(no.getFilhos()!=null)
		{
			System.out.println("\n");
			for(No n : no.getFilhos())
			{
				System.out.print(n.getValPai() + " ");
				
				if(n.getAtr()!=null && n.getAtr().getS()!=null)
				{
					System.out.print(n.getAtr().getS().getDescricao() + "   ");
				}
				if(n.getValFolha()!=null)
				{
					System.err.print(n.getValFolha() + "   ");
				}
			}	
		}
		
		System.out.println("\n");
	}
	
}
