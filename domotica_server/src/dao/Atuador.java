package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Atuador implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id_Atuador;
	private int cod;
	private String descricao;
	private int status;
		
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_Atuador", nullable = true)
	private List <Historico> historico;
	
	public List<Historico> getHistoricoAtuador() {
		return historico;
	}
	public void setHistoricoAtuador(List<Historico> historico) {
		this.historico = historico;
	}
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
