package dao;

import java.util.List;

import org.hibernate.Session;

public class ArduinoDao extends AbstractDao 
{
	public ArduinoDao() throws DbException 
	{
		super();
	}
	
	public void insere(Arduino arduino) throws DbException
	{
		if(arduino != null)
		{
			super.insert(arduino);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Arduino> loadArduino() throws DbException
	{
		return super.load("Arduino");		
	}

}
