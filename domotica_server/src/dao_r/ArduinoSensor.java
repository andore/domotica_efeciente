package dao_r;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class ArduinoSensor  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@TableGenerator(name="ARDUINO_SENSOR_GENERATOR",
            table="GENERATED_KEYS",
            pkColumnName="PK_COLUMN",
            valueColumnName="VALUE_COLUMN",
            pkColumnValue="id_arduino_sensor",
            allocationSize=1
			)	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="ARDUINO_SENSOR_GENERATOR")
	private int id_arduino_sensor;
	
	@OneToOne
	private Arduino arduino;
	
	@OneToOne
	private Sensor sensor;
	
	private double valor;

	public int getId_arduino_sensor() {
		return id_arduino_sensor;
	}

	public void setId_arduino_sensor(int id_arduino_sensor) {
		this.id_arduino_sensor = id_arduino_sensor;
	}

	public Arduino getArduino() {
		return arduino;
	}

	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	} 
}
