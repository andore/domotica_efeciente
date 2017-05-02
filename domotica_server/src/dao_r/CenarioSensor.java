package dao_r;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CenarioSensor implements Serializable
{
	

	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List <Cenario>cenarios;


	private double valor;
	private String status;
	
	
	
	
}