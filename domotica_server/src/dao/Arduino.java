package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private int id_cenario_ativo;
	
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn (name="id_arduino")
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
	public int getId_cenario_ativo() {
		return id_cenario_ativo;
	}
	public void setId_cenario_ativo(int id_cenario_ativo) {
		this.id_cenario_ativo = id_cenario_ativo;
	}
	
	

}
