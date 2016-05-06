package main;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import tratador.TratadorCadastramento;
import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.DbException;

public class RoteadorOperacao 
{
	private TratadorCadastramento cadastra;
	final static Logger logger = Logger.getLogger(RoteadorOperacao.class);
	
	
	public RoteadorOperacao()
	{
		cadastra = new TratadorCadastramento();
	}
	
	public MensagemResp getOperacao(Mensagem msg) throws StructException, DbException
	{
		
		MensagemResp resp = null;
		if(msg != null)
		{
			switch (msg.getOperacao())
			{
				case 1:
					logger.debug("Roteando mensagem para Tratador Cadastro");
					resp = cadastra.processa(msg);
					break;
					
				case 2:
					logger.debug("Roteando mensagem para Tratador Monitoramento");
					break;
	
				default:
					logger.debug("Operação não reconhecida!");
					break;
			}
			
		}
		else
		{
			logger.info("Mensagem vazia!");
		}
		return resp;
	}

}
