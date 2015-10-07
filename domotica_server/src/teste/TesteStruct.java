package teste;

import main.ControleServer;
import common.StrCadastraArduino;
import common.StructException;

public class TesteStruct {
	
	public static void main(String[] args) 
	{	
		TesteStruct x = new TesteStruct();
		x.teste();
	}
	
	void teste()
	{
		StrCadastraArduino str;
		try {
			str = new StrCadastraArduino(
					"999"
					+ "descricao "
					+ "01"
					+ "01"
					+ "01"
					+ "temperatur"
					+ "01"
					+ "01"
					+ "04"
					+ "janela    "
			);
		} 
		catch (StructException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("OK");
	}
}


