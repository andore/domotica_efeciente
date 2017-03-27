package common;

public abstract class Estrutura {
	protected final String separador = "#";
	protected String strIn;
	protected StringBuffer strOut;
	protected String campos[];
	protected int index;

	public Estrutura() {
		strIn = "";
		strOut = new StringBuffer();
	}

	public Estrutura(String str) throws EstruturaException {
		strOut = new StringBuffer();
		index = 0;
		
		try
		{
			this.strIn = str;
			campos = str.split(separador);
		}
		catch(Exception e)
		{
			throw new EstruturaException("Falha ao tentar quebrar mensagem:[" + str + "]", e.getMessage());
		}
		
		quebra();
	}
	
	public String toText() throws EstruturaException{
		montaStr();
		return strOut.toString().substring(0, strOut.length() - 1);
	}

	abstract protected void quebra() throws EstruturaException;
	
	/** Este método deve ser implementado para montagem do toString() */
	abstract protected void montaStr() throws EstruturaException;
	
	protected int getInt() throws EstruturaException
	{
		try
		{
			return Integer.parseInt(campos[index++]);
		}
		catch(Exception e)
		{
			throw new EstruturaException("Falha ao tentar quebrar Estrutura:[" + strIn + "]", e.getMessage());
		}	
	}
	
	protected String getString() throws EstruturaException
	{
		try
		{
			return campos[index++];
		}
		catch(Exception e)
		{
			throw new EstruturaException("Falha ao tentar quebrar Estrutura:[" + strIn + "]", e.getMessage());
		}	
	}
	
	protected void put(int valor) 
	{
		strOut.append(valor);
		strOut.append('#');
	}
	
	protected void put(String valor) 
	{
		strOut.append(valor==null? " ": valor);
		strOut.append('#');
	}
}
