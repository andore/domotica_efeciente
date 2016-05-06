package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hbn.ControleHbn;
import interfaceGrafica.ControleGui;
import interfaceGrafica.ListenerCadastraCenario;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.ArduinoDao;
import dao.Cenario;
import dao.CenarioDao;
import dao.DbException;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener, ListenerCadastraCenario {
	
	private NetService net;
	private RoteadorOperacao roteador;
	private ControleGui guiteste;
	final static Logger logger = Logger.getLogger(ControleServer.class);
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
	}
	
	public void init()
	{
		net.start();
		mostraJanelaCadastraCenario();
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
			logger.error("Erro ao processar mensagem:", e);
		} 
		catch (DbException e)
		{
			resp = new MensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setIp(msg.getIp());
			resp.setMensagem("1");
			logger.error("Erro ao processar mensagem:", e);
		}
		finally
		{
			if(resp != null)
			{
				net.envia(resp);
			}
		}

	}
	
	public void mostraJanelaCadastraCenario()
	{
		guiteste = new ControleGui(this);
		ArduinoDao ardDao = new ArduinoDao();
		try
		{
			setJanela(guiteste);
			guiteste.setDados(ardDao.loadArduino());
			//db.getSession().close();
			
		} catch (DbException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setJanela(ControleGui gui)
	{
		logger.debug("Setando Janela: " + gui.getJanela().getClass().getSimpleName());
		
		JFrame j = new JFrame();
		j.setBounds(800, 300, 800, 600);
		j.add(gui.getJanela());
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cadastraCenarioCancelar()
	{
		
		
	}

	public void cadastraCenarioSalvar(Cenario cenario)
	{	
		CenarioDao cenDao = new CenarioDao();
		cenDao.sessao.clear();
		try
		{
			cenDao.insere(cenario);
			JOptionPane.showMessageDialog(null,"Cenário salvado com sucesso!","SALVAR", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (DbException e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao salvar cenário, tente novamente.","ERRO", JOptionPane.ERROR_MESSAGE);
			logger.error("Erro ao gravar cenário no banco!", e);
		}
	}

}
