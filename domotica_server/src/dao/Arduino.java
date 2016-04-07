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
	//private int qtd_sensor;
	//private int qtd_atuador;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "arduino_sensor", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_sensor", referencedColumnName="id_sensor")})
	private List <Sensor>sensores;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "arduino_atuador", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_atuador", referencedColumnName="id_atuador")})
	private List <Atuador>atuadores;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "arduino_cenario", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_cenario", referencedColumnName="id_cenario")})
	private List <Cenario>cenarios;
	
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
	public List<Cenario> getCenarios() {
		return cenarios;
	}
	public void setCenarios(List<Cenario> cenarios) {
		this.cenarios = cenarios;
	}

}
