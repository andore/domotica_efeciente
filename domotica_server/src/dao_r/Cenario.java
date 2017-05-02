package dao_r;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Cenario implements Serializable
{
	
	@TableGenerator(name="CENARIO_GENERATOR",
	            table="GENERATED_KEYS",
	            pkColumnName="PK_COLUMN",
	            valueColumnName="VALUE_COLUMN",
	            pkColumnValue="id_cenario",
	            allocationSize=1
	)	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="CENARIO_GENERATOR")
	private int id_cenario;
	
	private int id_arduino;
	private String nome_cenario;
	private int id_usuario;
	private boolean privado;
	
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
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public boolean isPrivado() {
		return privado;
	}
	public void setPrivado(boolean privado) {
		this.privado = privado;
	}
	
	
}