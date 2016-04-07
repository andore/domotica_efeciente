package dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Cenario 
{
	@Id
	private int id_cenario;
	
	private int id_arduino;
	private String nome_cenario;
	private double valor_temperatura;
	private double valor_iluminacao;
	private boolean privado;
	
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "arduino_sensor", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_sensor", referencedColumnName="id_sensor")})
	private List <Sensor>sensores;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable ( name = "arduino_atuador", joinColumns={@JoinColumn(name="id_arduino", referencedColumnName="id_arduino")}, inverseJoinColumns={@JoinColumn(name="id_atuador", referencedColumnName="id_atuador")})
	private List <Atuador>atuadores;
	
	public int getId_cenario() {
		return id_cenario;
	}
	public void setId_cenario(int id_cenario) {
		this.id_cenario = id_cenario;
	}
	public int getId_arduino() {
		return id_arduino;
	}
	public void setId_arduino(int id_arduino) {
		this.id_arduino = id_arduino;
	}
	public String getNome_cenario() {
		return nome_cenario;
	}
	public void setNome_cenario(String nome_cenario) {
		this.nome_cenario = nome_cenario;
	}
	public double getValor_temperatura() {
		return valor_temperatura;
	}
	public void setValor_temperatura(double valor_temperatura) {
		this.valor_temperatura = valor_temperatura;
	}
	public double getValor_iluminacao() {
		return valor_iluminacao;
	}
	public void setValor_iluminacao(double valor_iluminacao) {
		this.valor_iluminacao = valor_iluminacao;
	}
	public boolean isPrivado() {
		return privado;
	}
	public void setPrivado(boolean privado) {
		this.privado = privado;
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
