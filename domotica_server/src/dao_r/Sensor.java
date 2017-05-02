package dao_r;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import common.Status;


@Entity
public class Sensor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id_sensor;
	private String descricao;
	private int cod;
	
	
	
}	


		


