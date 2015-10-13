package main;

import org.apache.log4j.Logger;
import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.DbException;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener {
	
	private NetService net;
	private RoteadorOperacao roteador;
	final static Logger logger = Logger.getLogger(ControleServer.class);
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
	}
	
	public void init()
	{
		net.start();	
	}
	
	public void netRecebe(Mensagem msg) {
		try
		{
			MensagemResp resp;
			resp = roteador.getOperacao(msg);
			if(resp != null)
			{
				net.envia(resp);
			}
		}		
		catch (StructException e)
		{
			logger.error("Erro ao provessar mensagem:", e);
			e.printStackTrace();
		} 
		catch (DbException e)
		{
			logger.error("Erro ao provessar mensagem:", e);
			e.printStackTrace();
		}

	}

}
