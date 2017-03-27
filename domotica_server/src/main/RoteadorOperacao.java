package main;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import tratador.TratadorCadastramento;
import tratador.TratadorMonitoramento;
import tratador.TratadorMonitoramentoHtml;
import common.EstMensagem;
import common.EstruturaException;
import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.DbException;

public class RoteadorOperacao 
{
	private TratadorCadastramento cadastra;
	private TratadorMonitoramento monitora;
	private TratadorMonitoramentoHtml monitoraHtml;
	final static Logger logger = Logger.getLogger(RoteadorOperacao.class);
	private final int delay = 2000;
	
	
	public RoteadorOperacao()
	{
		cadastra = new TratadorCadastramento();
		monitora = new TratadorMonitoramento();
		monitoraHtml = new TratadorMonitoramentoHtml();
		
	}
	
	public MensagemResp getOperacao(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		
		MensagemResp resp = null;
		if(msg != null)
		{
			switch (msg.getOperacao())
			{
				case 1:
					logger.debug("Roteando mensagem para Tratador Cadastro");
					try 
					{
						logger.debug("Espera de " + delay/1000 + "s");
						Thread.sleep(delay);
					} 
					catch (InterruptedException e)
					{
					
					}
					resp = cadastra.processa(msg);
					resp.setMensagem("+" + resp.getMensagem());
					break;
					
				case 2:
					logger.debug("Roteando mensagem para Tratador Monitoramento");
					resp = monitora.processa(msg); 
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
	
	public MensagemResp getOperacao(String msg) throws StructException, DbException, EstruturaException
	{
		
		MensagemResp resp = null;
		if(msg != null)
		{
			resp = monitoraHtml.processa(new EstMensagem(msg));
		}
		else
		{
			logger.info("Mensagem vazia!");
		}
		return resp;
	}

}
