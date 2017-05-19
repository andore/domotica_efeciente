package common;


public abstract class Struct 
{
	private int pos=0;
	private StringBuffer str;
	private String buf;
	
	public Struct(String str)
	{
		this.str = new StringBuffer(str);
	}
	
	String getString(int length) throws StructException
	{
		
		try
		{
			buf = str.substring(pos, pos + length);
			pos+=length;
			return buf;
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + str.toString() + "]",  e.getMessage());
		}
	
	}
	
	int getInt(int length) throws StructException
	{
		try
		{
			buf = str.substring(pos, pos + length);
			pos+=length;
			return Integer.parseInt(buf);
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + str.toString() + "]",  e.getMessage());
		}
	}
	
	double getDouble(int length, int decimal) throws StructException
	{
		try
		{
			buf = str.substring(pos, pos + length);
		    pos+=length;
	
			String dec = str.substring(pos, pos + decimal);
			pos+=decimal;
			
			double x = Integer.parseInt(buf) + Integer.parseInt(dec)/(Math.pow(10, decimal));
			return x;
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + str.toString() + "]",  e.getMessage());
		}
	}
	

}
