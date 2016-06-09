package common;

import java.text.DecimalFormat;

public abstract class AbstractStruct 
{
	private int pos=0;
	private StringBuffer in;
	protected StringBuffer out;
	private String buf;
	
	public AbstractStruct()
	{
		this("");
	}
	
	public AbstractStruct(String str)
	{
		this.in = new StringBuffer(str);
		this.out = new StringBuffer();
	}
	
	String getString(int length) throws StructException
	{
		
		try
		{
			buf = in.substring(pos, pos + length);
			pos+=length;
			return buf;
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + in.toString() + "]",  e.getMessage());
		}
	
	}
	
	int getInt(int length) throws StructException
	{
		try
		{
			buf = in.substring(pos, pos + length);
			pos+=length;
			return Integer.parseInt(buf.trim());
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + in.toString() + "]",  e.getMessage());
		}
	}
	
	double getDouble(int length, int decimal) throws StructException
	{
		try
		{
			buf = in.substring(pos, pos + length);
		    pos+=length;
	
			String dec = in.substring(pos, pos + decimal);
			pos+=decimal;
			
			double x = Integer.parseInt(buf) + Integer.parseInt(dec)/(Math.pow(10, decimal));
			return x;
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao quebrar String:[" + in.toString() + "]",  e.getMessage());
		}
	}
	
	void put (String s, int tam) throws StructException
	{
		
		try{
			if(s.length()<tam)
			{
				int cont = tam - s.length();
				for(int i=0; i<cont; i++) s+=' ';
			}
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao tentar montar String",  e.getMessage());
		}
		
		out.append(s.substring(0,tam));
	}
	
	void put (long s, int tam) throws StructException
	{
		try
		{
			DecimalFormat f = new DecimalFormat();
			f.setMaximumIntegerDigits(tam);
			f.setMinimumIntegerDigits(tam);
			out.append(f.format(s));
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao tentar montar String",  e.getMessage());
		}
	}
	
	void put (double s, int tam, int deci) throws StructException
	{
		try
		{
			DecimalFormat f = new DecimalFormat();
			f.setMaximumIntegerDigits(tam);
			f.setMinimumIntegerDigits(tam);
			
			f.setMaximumFractionDigits(deci);
			f.setMinimumFractionDigits(deci);
			out.append(f.format(s));
		}
		catch(Exception e)
		{
			throw new StructException("Erro ao tentar montar String",  e.getMessage());
		}
	}
		
}
