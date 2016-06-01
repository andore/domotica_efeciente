package interfaceGrafica;

import javax.swing.JPanel;

public class ControleGuiMensagem extends AbstractControleGui implements ListenerGuiMensagem{
	
	GuiMensagem janela;
	ListenerCtrlGuiMensagem listener;
	
	public ControleGuiMensagem(ListenerCtrlGuiMensagem listener, String texto, int tempoEspera)
	{
		this.listener = listener;
		janela = new GuiMensagem(this, texto, tempoEspera);
	}
	
	@Override
	public JPanel getJanela() {
		return janela;
	}

	public void timeOut()
	{
		listener.acaoTimeOutMensagem();
	}

}
