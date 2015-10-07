package dao;

import org.hibernate.Session;

import common.Sensor;

public class SensorDao extends AbstractDao {

	public SensorDao(Session sessao) {
		super(sessao);
	}
	
	public void insereArduino(Sensor sensor)
	{
		if(sensor != null)
		{
			super.insert(sensor);
		}
		
	}

}
