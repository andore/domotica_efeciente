package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Arduino  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int id_arduino;
	
	private String descricao;
	private String ip;
	private int qtd_sensor;
	private int qtd_atuador;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "ARDUINO_HAS_SENSORS", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_sensor", referencedColumnName="id_sensor")})
	private List <Sensor>sensores ;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn (name="id_arduino")
	private List <Atuador>atuadores ;
	
	public int getId() {
		return id_arduino;
	}
	public void setId(int id) {
		this.id_arduino = id;
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
		return qtd_sensor;
	}
	public void setQtdSensor(int qtdSensor) {
		this.qtd_sensor = qtdSensor;
	}
	public int getQtdAtuador() {
		return qtd_atuador;
	}
	public void setQtdAtuador(int qtdAtuador) {
		this.qtd_atuador = qtdAtuador;
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
