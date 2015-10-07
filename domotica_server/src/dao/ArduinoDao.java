package dao;

import org.hibernate.Session;

import common.Arduino;

public class ArduinoDao extends AbstractDao 
{
	public ArduinoDao(Session sessao) 
	{
		super(sessao);
	}
	
	public void insereArduino(Arduino arduino)
	{
		if(arduino != null)
		{
			super.insert(arduino);
		}
		
	}

}
