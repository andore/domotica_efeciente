package common;

import java.util.ArrayList;
import java.util.List;

import dao.Atuador;
import dao.Sensor;

public class StrCadastraArduino extends AbstractStruct {

	public StrCadastraArduino(String str) throws StructException {
		super(str);
		quebra();
	}
	
	public StrCadastraArduino()
	{
		super("");
	}

	private String descricaoArduino;

	private int qtdSensor;
	private int qtdAtuador;
	private List<Sensor> sensores;
	private List<Atuador> atuadores;
	
	private void quebra() throws StructException
	{
	
		descricaoArduino = getString(10);
		qtdSensor = getInt(2);
		sensores = new ArrayList<Sensor>();
		for(int i=0; i< qtdSensor; i++)
		{
			Sensor s = new Sensor();
			s.setId(getInt(2));
			s.setCod(getInt(2));
			s.setDescricao(getString(10));
		
			sensores.add(s);
		}
		qtdAtuador = getInt(2);
		atuadores = new ArrayList<Atuador>();
		for(int i=0; i< qtdAtuador; i++)
		{
			Atuador a = new Atuador();
			a.setId(getInt(2));
			a.setCod(getInt(2));
			a.setDescricao(getString(10));
		
			atuadores.add(a);
		}		
	}
	
	public String getString() throws StructException
	{
		put(descricaoArduino, 10);
		put(qtdSensor, 2);
		
		for(Sensor s: sensores)
		{
			put(s.getId(), 2);
			put(s.getCod(), 2);
			put(s.getDescricao(), 10);
		}
		
		put(qtdAtuador, 2);
		
		for(Atuador s: atuadores)
		{
			put(s.getId(), 2);
			put(s.getCod(), 2);
			put(s.getDescricao(), 10);
		}
		
		return super.out.toString();
	}

	public String getDescricaoArduino() {
		return descricaoArduino;
	}

	public void setDescricaoArduino(String descricaoArduino) {
		this.descricaoArduino = descricaoArduino;
	}

	public int getQtdSensor() {
		return qtdSensor;
	}

	public void setQtdSensor(int qtdSensor) {
		this.qtdSensor = qtdSensor;
	}

	public int getQtdAtuador() {
		return qtdAtuador;
	}

	public void setQtdAtuador(int qtdAtuador) {
		this.qtdAtuador = qtdAtuador;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public List<Atuador> getAtuadores() {
		return atuadores;
	}

	public void setAtuadores(List<Atuador> atuadores) {
		this.atuadores = atuadores;
	}

}
