package dao;

import org.hibernate.Session;

import common.Status;

public class SensorDao extends AbstractDao {

	public SensorDao() {
		super();
	}
	
	public void insere(Sensor sensor) throws DbException
	{
		if(sensor != null)
		{
			if(sensor.getStatus()==null)
			{
				sensor.setStatus(Status.AUTO);
			}
			super.insert(sensor);
		}
		
	}

}
