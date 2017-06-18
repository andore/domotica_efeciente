package common;

import java.util.ArrayList;
import java.util.List;
import dao.Atuador;
import dao.Sensor;

public class EstMonitora extends EstMensagem {

	public EstMonitora(String str) throws EstruturaException {
		super(str);
		isAdicionaSensor = false;
	}
	
	public EstMonitora(String str, boolean isAdicionaSensor) throws EstruturaException {
		super(str);
		this.isAdicionaSensor = isAdicionaSensor;
	}
	
	public EstMonitora(boolean isAdicionaSensor) {
		super();
		this.isAdicionaSensor = isAdicionaSensor;
	}

	public EstMonitora() {
		super();
		isAdicionaSensor = false;
	}

	private int qtdSensor;
	private List<Sensor> sensores;
	private int qtdAtuador;
	private List<Atuador> atuadores;
	private boolean isAdicionaSensor;

	@Override
	protected void quebra() throws EstruturaException {
		super.quebra();

		qtdSensor = getInt();
		sensores = new ArrayList<Sensor>();
		for (int i = 0; i < qtdSensor; i++) {
			Sensor buf = new Sensor();
			buf.setId(getInt());
			buf.setCod(getInt());
			buf.setValor(getInt());
			sensores.add(buf);
		}

		qtdAtuador = getInt();
		atuadores = new ArrayList<Atuador>();
		for (int i = 0; i < qtdAtuador; i++) {
			Atuador buf = new Atuador();
			buf.setId(getInt());
			buf.setCod(getInt());
			buf.setStatus(getInt());
			atuadores.add(buf);
		}
	}

	@Override
	protected void montaStr() throws EstruturaException {
		//super.montaStr();

		if(isAdicionaSensor)
		{
			put(qtdSensor);
			for (int i = 0; i < qtdSensor; i++) {
				put(sensores.get(i).getId());
				put(sensores.get(i).getCod());
				put(sensores.get(i).getValor());
			}

		}
		
		put(qtdAtuador);
		for (int i = 0; i < qtdAtuador; i++) {
			put(atuadores.get(i).getId());
			put(atuadores.get(i).getCod());
			put(atuadores.get(i).getStatus());
		}
	}

	public int getQtdSensor() {
		return qtdSensor;
	}

	public void setQtdSensor(int qtdSensor) {
		this.qtdSensor = qtdSensor;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public int getQtdAtuador() {
		return qtdAtuador;
	}

	public void setQtdAtuador(int qtdAtuador) {
		this.qtdAtuador = qtdAtuador;
	}

	public List<Atuador> getAtuadores() {
		return atuadores;
	}

	public void setAtuadores(List<Atuador> atuadores) {
		this.atuadores = atuadores;
	}
}
