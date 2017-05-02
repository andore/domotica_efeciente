package dao_r;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Arduino  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int id_arduino;
	private String descricao;
	private String ip;
	
	public int getId_arduino() {
		return id_arduino;
	}
	public void setId_arduino(int id_arduino) {
		this.id_arduino = id_arduino;
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
}
