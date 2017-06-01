package teste.simulador.gui;

import dao.Arduino;

public interface ListenerControleGuisimuladorSensor {
	
	public void alteraValor(Arduino arduino, int indexSensor);

}
