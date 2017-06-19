package id3;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import common.ConversorValoreDiscreto;
import common.Status;
import dao.Atuador;
import dao.AtuadorDao;
import dao.DbException;
import dao.Sensor;

public class VerificaArvore {
	final static Logger logger = Logger.getLogger(VerificaArvore.class);
	private int status;
	
	public int verifica(int idAtuador, List<Sensor> sensores)
	{		
		status = -1;
		Atuador atuador;
		try 
		{
			atuador = new AtuadorDao().serachById(idAtuador);
			logger.info("Iniciando verificação para o Atuador:" + atuador.getDescricao());
		} 
		catch (DbException e)
		{
			e.printStackTrace();
		}
		
		for(No raiz: RepositorioArvores.getArvores())
		{
			if(raiz.getIdAtuador() == idAtuador)
			{
				navegaArvore(raiz, sensores);
				break;
			}
		}
		
		if(status != -1)
		{
			logger.info("Arvore resultou em STATUS:" + Status.getStatus(status).toString());
		}	
		else
		{
			logger.info("Arvore NÂO resultou em decisao");
		}
		return status;
	}
	
	private void navegaArvore(No no, List<Sensor> sensores)
	{
		if(no.getAtr() != null)
		{
			for(Sensor s: sensores)
			{
				if(s.getId()==no.getAtr().getS().getId())
				{
					if(no.getFilhos() != null &&no.getFilhos().size()>0)
					{
						for(No filho : no.getFilhos())
						{
							if(filho.getValPai() == ConversorValoreDiscreto.getValorDiscretoSensor(s))
							{
								navegaArvore(filho, sensores);
							}
						}
					}
				}
			}
		}
		else if(no.getValFolha()!=null)
		{
			status = no.getValFolha();
		}
	}
	
	
	
	public static void main(String[] args) {
		List<Sensor> sensores = new ArrayList<Sensor>();
		
		Sensor s = new Sensor();
		s.setId(5);
		s.setCod(6);
		s.setValor(18);
		s.setDescricao("TempExter");
		sensores.add(s);
		
		s = new Sensor();
		s.setId(4);
		s.setCod(5);
		s.setValor(1);
		s.setDescricao("humidade");
		sensores.add(s);
		
		
		s = new Sensor();
		s.setId(3);
		s.setValor(1);
		s.setCod(4);
		s.setDescricao("chuva");
		sensores.add(s);
		
		VerificaArvore teste = new VerificaArvore();
		teste.verifica(3, sensores);

	}
	

}
