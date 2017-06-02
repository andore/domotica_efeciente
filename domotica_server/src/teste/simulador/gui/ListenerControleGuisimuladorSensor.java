package teste.simulador.gui;

import common.Status;
import dao.Arduino;

public interface ListenerControleGuisimuladorSensor {
	
	public void alteraValor(Arduino arduino, int indexSensor);
	public void alteraStatus(Arduino arduino, int indexAtuador);

}
