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
		MensagemResp resp =  null;
		try
		{
			resp = roteador.getOperacao(msg);
		}		
		catch (StructException e)
		{
			resp = new MensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setIp(msg.getIp());
			resp.setMensagem("1");
			logger.error("Erro ao provessar mensagem:", e);
		} 
		catch (DbException e)
		{
			resp = new MensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setIp(msg.getIp());
			resp.setMensagem("1");
			logger.error("Erro ao provessar mensagem:", e);
		}
		finally
		{
			if(resp != null)
			{
				net.envia(resp);
			}
		}

	}

}
