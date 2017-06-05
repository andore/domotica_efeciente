package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Historico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@TableGenerator(name="HISTORICO_GENERATOR",
            table="GENERATED_KEYS",
            pkColumnName="PK_COLUMN",
            valueColumnName="VALUE_COLUMN",
            pkColumnValue="id_historico",
            allocationSize=1
			)	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="HISTORICO_GENERATOR")
	private int id_historico;
	private Date data_criacao;
	private int valor_sensor;
	private int status_atuador;
	
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinColumn(name="id_arduino")
	private int id_arduino;	
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinColumn(name="id_atuador")
	private int id_sensor;	
	private int id_atuador;
		
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
	public int getId_arduino() {
		return id_arduino;
	}
	public void setId_arduino(int id_arduino) {
		this.id_arduino = id_arduino;
	}
	public int getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(int id_sensor) {
		this.id_sensor = id_sensor;
	}
	public int getId_atuador() {
		return id_atuador;
	}
	public void setId_atuador(int id_atuador) {
		this.id_atuador = id_atuador;
	}	
	
}
