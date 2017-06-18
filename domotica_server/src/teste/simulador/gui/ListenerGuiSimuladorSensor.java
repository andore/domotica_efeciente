package teste.simulador.gui;

import common.Status;

public interface ListenerGuiSimuladorSensor 
{
	public void alteraArduino(int index);
	public void alteraValor(int index, String valor);
	public void setStatus(int index, Status s);
	public void salva();

}
