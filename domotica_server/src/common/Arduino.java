package common;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Arduino {
	
	@Id
	private int id;
	
	private String descricao;
	private String ip;
	private int qtdSensor;
	private int qtdAtuador;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	
}
