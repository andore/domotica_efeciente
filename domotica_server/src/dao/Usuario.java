package dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Usuario 
{
	@Id
	private int id_usuario;
	private String nome_usuario;
	private String login;
	private String senha;
	private int permissao_nivel;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn (name="id_usuario")
	private List <Cenario> cenario;
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome_usuario() {
		return nome_usuario;
	}
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getPermissao_nivel() {
		return permissao_nivel;
	}
	public void setPermissao_nivel(int permissao_nivel) {
		this.permissao_nivel = permissao_nivel;
	}
	public List<Cenario> getCenario() {
		return cenario;
	}
	public void setCenario(List<Cenario> cenario) {
		this.cenario = cenario;
	}
	
	
}
