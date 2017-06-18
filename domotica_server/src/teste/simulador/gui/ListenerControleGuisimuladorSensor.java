package teste.simulador.gui;

import dao.Arduino;

public interface ListenerControleGuisimuladorSensor {
	
	public void alteraValor(Arduino arduino);
	public void alteraStatus(Arduino arduino);

}
