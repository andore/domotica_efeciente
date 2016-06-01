package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.Source;

import hbn.ControleHbn;
import interfaceGrafica.AbstractControleGui;
import interfaceGrafica.ControleGuiCadastraCenario;
import interfaceGrafica.ControleGuiMensagem;
import interfaceGrafica.ControleGuiPrincipal;
import interfaceGrafica.ListenerCtrlCadastraCenario;
import interfaceGrafica.ListenerCtrlGuiMensagem;
import interfaceGrafica.ListenerCtrlGuiPrincipal;
import interfaceGrafica.ListenerGuiMensagem;
import interfaceGrafica.ListenerGuiPrincipal;

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

public class ControleServer implements NetListener, ListenerCtrlCadastraCenario, ListenerCtrlGuiPrincipal, ListenerCtrlGuiMensagem {
	
	private NetService net;
	private RoteadorOperacao roteador;
	private ControleGuiCadastraCenario guiteste;
	private ControleGuiPrincipal janelaPrincipal;
	final static Logger logger = Logger.getLogger(ControleServer.class);
	final static int TEMPO_ESPERA_TELA_MESAGEM = 1000;
	JFrame j;
	
	public ControleServer()
	{
		net = new NetService(this);
		roteador = new RoteadorOperacao();
	}
	
	public void init()
	{
		net.start();
		mostraJanelaPrincipal();
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
		guiteste = new ControleGuiCadastraCenario(this);
		
		try
		{
			ArduinoDao ardDao = new ArduinoDao();
			setJanela(guiteste);
			guiteste.setDados(ardDao.loadArduino());
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

	public void cadastraCenarioCancelar()
	{
		mostraJanelaPrincipal();
	}

	public void cadastraCenarioSalvar(Cenario cenario)
	{	
		CenarioDao cenDao;
		
		try
		{
			cenDao = new CenarioDao();
			cenDao.sessao.clear();
			cenDao.insere(cenario);
			JOptionPane.showMessageDialog(null,"Cenário salvado com sucesso!","SALVAR", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (DbException e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao salvar cenário, tente novamente.","ERRO", JOptionPane.ERROR_MESSAGE);
			logger.error("Erro ao gravar cenário no banco!", e);
		}
	}
	
	public void mostraJanelaPrincipal()
	{
		janelaPrincipal = new ControleGuiPrincipal(this);
		setJanela(janelaPrincipal);
	}

	public void acaoMonitoramento()
	{
		
	}

	public void acaoCadastraCenario()
	{
		mostraJanelaMensagem("<HTML><center>Aguarde...<BR>Consultando Banco de Dados",TEMPO_ESPERA_TELA_MESAGEM);
	}

	public void acaoUsuario() {
		// TODO Auto-generated method stub
		
	}
	
	public void mostraJanelaMensagem(String mensagem, int tempoEspera)
	{
		ControleGuiMensagem janelaMensagem = new ControleGuiMensagem(this, mensagem, tempoEspera);
		setJanela(janelaMensagem);
	}

	public void acaoTimeOutMensagem()
	{
		mostraJanelaCadastraCenario();
	}

}
