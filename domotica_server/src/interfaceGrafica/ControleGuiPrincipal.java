package interfaceGrafica;

import javax.swing.JPanel;

public class ControleGuiPrincipal extends AbstractControleGui implements ListenerGuiPrincipal{
	GuiPrincipal janela;
	ListenerCtrlGuiPrincipal listener;
	
	public ControleGuiPrincipal(ListenerCtrlGuiPrincipal listener)
	{
		janela = new GuiPrincipal(this);
		this.listener = listener; 
	}
	
	@Override
	public JPanel getJanela()
	{
		return janela;
	}

	public void acaoMonitoramento()
	{
		listener.acaoPrincipalMonitoramento();
	}

	public void acaoCadastraCenario()
	{
		listener.acaoPrincipalCadastraCenario();
	}

	public void acaoUsuario()
	{
		listener.acaoPrincipalUsuario();
	}

}
