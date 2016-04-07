package dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import common.Status;


@Entity
public class Atuador implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id_Atuador;
	private int cod;
	private String descricao;
	
	@Column(columnDefinition = "int default 2")
	private Status status;
	
	
	public int getId() {
		return id_Atuador;
	}
	public void setId(int id) {
		this.id_Atuador = id;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descicao) {
		this.descricao = descicao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
