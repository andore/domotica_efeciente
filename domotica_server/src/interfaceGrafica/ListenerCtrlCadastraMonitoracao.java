package interfaceGrafica;

import dao.Cenario;

public interface ListenerCtrlCadastraMonitoracao
{
	public void acaoMonitoracaoCancelar();
	public void acaoMonitoracaoCenarioSalvar(Cenario cenario);
}
