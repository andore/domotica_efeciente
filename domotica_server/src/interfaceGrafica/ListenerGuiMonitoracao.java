package interfaceGrafica;

import common.Status;

public interface ListenerGuiMonitoracao
{
	
	public void setComodo(int index);
	public void setTemperatura(int temp);
	public void setIluminacao(int ilum);
	
	public void setArCond(Status s);
	public void setVentilador(Status s);
	public void setAquecedor(Status s);
	public void setLampada(int index, Status s, int value);
	public void setJanela(Status s);
	public void setPerciana(Status s);
		
	public void salvar();
	public void cancelar();

}
