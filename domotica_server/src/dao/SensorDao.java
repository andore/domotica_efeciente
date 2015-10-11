package dao;

import org.hibernate.Session;

public class SensorDao extends AbstractDao {

	public SensorDao(Session sessao) {
		super(sessao);
	}
	
	public void insereArduino(Sensor sensor) throws DbException
	{
		if(sensor != null)
		{
			super.insert(sensor);
		}
		
	}

}
