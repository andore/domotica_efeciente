package dao;

import org.hibernate.Session;

import common.Status;

public class SensorDao extends AbstractDao {

	public SensorDao() throws DbException {
		super();
	}
	
	public void insere(Sensor sensor) throws DbException
	{
		super.insert(sensor);	
	}
	
	public void update(Sensor sensor) throws DbException
	{
		super.update(sensor);
	}

}
