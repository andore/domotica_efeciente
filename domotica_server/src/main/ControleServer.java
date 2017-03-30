package main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.Source;

import hbn.ControleHbn;
import interfaceGrafica.AbstractControleGui;
import interfaceGrafica.ControleGuiCadastraCenario;
import interfaceGrafica.ControleGuiMensagem;
import interfaceGrafica.ControleGuiMonitoracao;
import interfaceGrafica.ControleGuiPrincipal;
import interfaceGrafica.ListenerCtrlCadastraCenario;
import interfaceGrafica.ListenerCtrlCadastraMonitoracao;
import interfaceGrafica.ListenerCtrlGuiMensagem;
import interfaceGrafica.ListenerCtrlGuiPrincipal;
import interfaceGrafica.ListenerGuiMensagem;
import interfaceGrafica.ListenerGuiPrincipal;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.hibernate.Session;

import common.EstMensagemResp;
import common.EstMensagem;
import common.EstruturaException;
import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.ArduinoDao;
import dao.Cenario;
import dao.CenarioDao;
import dao.DbException;
import net.NetListener;
import net.NetService;

public class ControleServer implements NetListener, ListenerCtrlCadastraCenario, ListenerCtrlGuiPrincipal, ListenerCtrlGuiMensagem, ListenerCtrlCadastraMonitoracao {
	
	private NetService net;
	private RoteadorOperacao roteador;
	private ControleGuiCadastraCenario janelaCadastraCenario;
	private ControleGuiPrincipal janelaPrincipal;
	private ControleGuiMonitoracao janelaMonitoracao;
	private int janelaProx;
	final static Logger logger = Logger.getLogger(ControleServer.class);
	final static int TEMPO_ESPERA_TELA_MESAGEM = 1000;
	private static Cenario cenarioAtual;
	JFrame j;
	
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
		carregaCearioInicial();
	}
	
	private void carregaCearioInicial()
	{
		try
		{
			CenarioDao cenDao = new CenarioDao();
			cenarioAtual = cenDao.serachById(0);
		} 
		catch (DbException e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		net.start();
		mostraJanelaPrincipal();
	}
	
	public void netRecebe(EstMensagem msg) throws EstruturaException {
		EstMensagemResp resp =  null;
		try
		{
			resp = roteador.getOperacao(msg);
		}		
		catch (StructException e)
		{
			resp = new EstMensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setIp(msg.getIp());
			resp.setResp("1");
			logger.error("Erro ao processar mensagem:", e);
		} 
		catch (DbException e)
		{
			resp = new EstMensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setResp("1");
			logger.error("Erro ao processar mensagem:", e);
		}
		catch (EstruturaException e)
		{
			resp = new EstMensagemResp();
			resp.setOperacao(msg.getOperacao());
			resp.setIp(msg.getIp());
			resp.setResp("1");
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
	
	public void netRecebe(String msg) throws EstruturaException
	{
		MensagemResp resp =  null;
		try 
		{
			resp = roteador.getOperacao(msg);
		} 
		catch (StructException e) 
		{
			resp = new MensagemResp();
			resp.setOperacao(0);
			resp.setIp("");
			resp.setMensagem("<HTML><font size=\"5\">Ops, ocorreu uma falha -_-' <BR>Detalhes:<i> " + e.getMessage());
			logger.error("Erro ao processar mensagem:", e);
		} 
		catch (DbException e)
		{
			resp = new MensagemResp();
			resp.setOperacao(0);
			resp.setIp("");
			resp.setMensagem("<HTML><font size=\"5\">Ops, tem algo errado que não está certo -_-' <BR>Detalhes:<i> " + e.getMessage());
			logger.error("Erro ao processar mensagem:", e);
		}
		finally
		{
			if(resp != null)
			{
				net.enviaHtml(resp);
			}
		}
	}
	
	public void mostraJanelaCadastraCenario()
	{
		janelaCadastraCenario = new ControleGuiCadastraCenario(this);
		
		try
		{
			ArduinoDao ardDao = new ArduinoDao();
			setJanela(janelaCadastraCenario);
			janelaCadastraCenario.setDados(ardDao.loadArduino());
			//db.getSession().close();
			
		}
		catch (Exception e)
		{
			logger.error("ERRO ao popular janela Cadastra Cenarios", e);
			JOptionPane.showMessageDialog(null,"Opa, tem alguma coisa errada =(\nFalha ao consultar banco de dados.","Ocorreu um Erro =(",JOptionPane.ERROR_MESSAGE);
			mostraJanelaPrincipal();
		}
	}
	
	public void setJanela(AbstractControleGui gui)
	{
		logger.debug("Setando Janela: " + gui.getJanela().getClass().getSimpleName());

		int posXAnt = 0;
		int posYAnt = 0;
		
		if(j!=null)
		{
			posXAnt = j.getX();
			posYAnt = j.getY();
			j.removeAll();
			j.dispose();
		}
		j = new JFrame();
		j.setBounds(posXAnt, posYAnt, 800, 600);
		j.add(gui.getJanela());
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void acaoCadastraCenarioCancelar()
	{
		mostraJanelaPrincipal();
	}

	public void acaoCadastraCenarioSalvar(Cenario cenario)
	{	
		CenarioDao cenDao;
		
		try
		{
			cenDao = new CenarioDao();
			cenDao.insere(cenario);
			JOptionPane.showMessageDialog(null,"Cenário salvado com sucesso!","SALVAR", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (DbException e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao salvar cenário, tente novamente.","ERRO", JOptionPane.ERROR_MESSAGE);
			logger.error("Erro ao gravar cenário no banco!", e);
		}
		finally 
		{
			mostraJanelaPrincipal();
		}
	}
	
	public void mostraJanelaPrincipal()
	{
		janelaPrincipal = new ControleGuiPrincipal(this);
		setJanela(janelaPrincipal);
	}

	public void acaoPrincipalMonitoramento()
	{
		janelaProx = 1;
		mostraJanelaMensagem("<HTML><center>Aguarde...<BR>Consultando Banco de Dados",TEMPO_ESPERA_TELA_MESAGEM);
		
	}

	public void acaoPrincipalCadastraCenario()
	{
		janelaProx = 2;
		mostraJanelaMensagem("<HTML><center>Aguarde...<BR>Consultando Banco de Dados",TEMPO_ESPERA_TELA_MESAGEM);
	}

	public void acaoPrincipalUsuario() {
		// TODO Auto-generated method stub
		
	}
	
	public void mostraJanelaMensagem(String mensagem, int tempoEspera)
	{
		ControleGuiMensagem janelaMensagem = new ControleGuiMensagem(this, mensagem, tempoEspera);
		setJanela(janelaMensagem);
	}

	public void acaoTimeOutMensagem()
	{
		if(janelaProx == 1)
		{
			mostraJanelaMonitoracao();
		}
		else if (janelaProx == 2)
		{
			mostraJanelaCadastraCenario();
		}
		else
		{
			mostraJanelaPrincipal();
		}
	}
	
	public void mostraJanelaMonitoracao()
	{
		try
		{
			janelaMonitoracao = new ControleGuiMonitoracao(this);
			ArduinoDao ardDao = new ArduinoDao();
			janelaMonitoracao.setDados(ardDao.loadArduino());			
			setJanela(janelaMonitoracao);
		} 
		catch (DbException e)
		{
			logger.error("ERRO ao popular janela Monitoracao", e);
			JOptionPane.showMessageDialog(null,"Opa, tem alguma coisa errada =(\nFalha ao consultar banco de dados.","Ocorreu um Erro =(",JOptionPane.ERROR_MESSAGE);
			mostraJanelaPrincipal();
		}
	}

	public void acaoMonitoracaoCancelar()
	{
		mostraJanelaPrincipal();
	}

	public void acaoMonitoracaoCenarioSalvar(Cenario cenario)
	{
		try
		{
			CenarioDao dao = new CenarioDao();
			List listCen = dao.loadCenario();			
			Cenario load = dao.serachById(cenario.getId_cenario());
			
			if(load != null)
			{
				load.setValor_iluminacao(cenario.getValor_iluminacao());
				load.setValor_temperatura(cenario.getValor_temperatura());
				dao.update(load);
				logger.debug("Cenario alterado com sucesso.");
			}
			else
			{
				throw new DbException("Falha ao salvar Cenario", null);
			}
			
		} 
		catch (DbException e)
		{
			logger.error("Falha ao salvar cenario", e);
			JOptionPane.showMessageDialog(null,"Opa, tem alguma coisa errada =(\nFalha ao consultar banco de dados.","Ocorreu um Erro =(",JOptionPane.ERROR_MESSAGE);
			mostraJanelaPrincipal();
		}
	}

	public static Cenario getCenarioAtual() {
		return cenarioAtual;
	}

	public static void setCenarioAtual(Cenario cenarioAtual) {
		ControleServer.cenarioAtual = cenarioAtual;
	}
	
	
}
