package interfaceGrafica;

import common.Status;

public interface ListenerGuiCadastraCenario
{
	
	public void setComodo(int index);
	public void setNomeCenario(String nomeCenario);
	public void setTemperatura(int temp);
	public void setIluminacao(int ilum);
	
	public void setArCond(Status s);
	public void setVentilador(Status s);
	public void setAquecedor(Status s);
	public void setLampada(int index, Status s, int value);
	public void setJanela(Status s);
	public void setPerciana(Status s);
	
	public void isPrivado (boolean isPrivado);
	
	public void salvar();
	public void cancelar();

}
