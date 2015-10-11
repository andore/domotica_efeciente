package dao;

import org.hibernate.Session;

public class ArduinoDao extends AbstractDao 
{
	public ArduinoDao(Session sessao) 
	{
		super(sessao);
	}
	
	public void insereArduino(Arduino arduino) throws DbException
	{
		if(arduino != null)
		{
			super.insert(arduino);
		}
		
	}

}
