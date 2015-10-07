package common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Sensor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id_sensor;
	private int cod;
	private String descricao;
	public int getId() {
		return id_sensor;
	}
	public void setId(int id) {
		this.id_sensor = id;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescicao() {
		return descricao;
	}
	public void setDescicao(String descicao) {
		this.descricao = descicao;
	}
}
