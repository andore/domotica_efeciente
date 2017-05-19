package teste;

import common.EstCadastra;
import common.EstruturaException;

public class TetaEstCadastra {
	
	public static void main(String[] args) 
	{	
		try 
		{
			EstCadastra est = new EstCadastra("1#99#Quarto#1#22#01#Sensor de Temperatura#1#33#04#Janela");
			
			System.out.println("operacao:" + est.getOperacao());
			System.out.println("id Arduino:" + est.getIdArduino());
			System.out.println("descricao arduino:" + est.getDescricaoArduino());
			
			System.out.println("Qtd Sensores:" + est.getQtdSensor());
			System.out.println("id Sensor:" + est.getSensores().get(0).getId());
			System.out.println("codigo sensor:" + est.getSensores().get(0).getCod());
			System.out.println("descricao sensor:" + est.getSensores().get(0).getDescricao());
			
			System.out.println("Qtd Atuador:" + est.getQtdAtuador());
			System.out.println("id Atuador:" + est.getAtuadores().get(0).getId());
			System.out.println("codigo Atuador:" + est.getAtuadores().get(0).getCod());
			System.out.println("descricao Atuador:" + est.getAtuadores().get(0).getDescricao());
			
			
			System.out.println(est.toText());			
			
		} catch (EstruturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
