package dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Historico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id_historico;
	private Date data_criacao;
	private int valor_sensor;
	private int status_atuador;
		
	public int getId_historico() {
		return id_historico;
	}	
	public void setId_historico(int id_historico) {
		this.id_historico = id_historico;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public int getValor_sensor() {
		return valor_sensor;
	}
	public void setValor_sensor(int valor_sensor) {
		this.valor_sensor = valor_sensor;
	}
	public int getStatus_atuador() {
		return status_atuador;
	}
	public void setStatus_atuador(int status_atuador) {
		this.status_atuador = status_atuador;
	}	
	
}
