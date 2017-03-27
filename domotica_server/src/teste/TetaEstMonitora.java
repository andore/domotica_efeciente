package teste;

import common.EstMonitora;
import common.EstruturaException;

public class TetaEstMonitora {
	
	public static void main(String[] args) 
	{	
		try 
		{
			EstMonitora est = new EstMonitora("2#4#1#9#1#23#1#9#3");
			
			System.out.println("operacao:" + est.getOperacao());
			System.out.println("id Arduino:" + est.getIdArduino());
			
			System.out.println("Qtd Sensores:" + est.getQtdSensor());
			System.out.println("id Sensor:" + est.getSensores().get(0).getId());
			System.out.println("codigo sensor:" + est.getSensores().get(0).getCod());
			System.out.println("valor sensor:" + est.getSensores().get(0).getValor());
			
			System.out.println("Qtd Atuador:" + est.getQtdAtuador());
			System.out.println("id Atuador:" + est.getAtuadores().get(0).getId());
			System.out.println("codigo Atuador:" + est.getAtuadores().get(0).getCod());		
			
			System.out.println(est.toText());
			
			//EstMonitora estException = new EstMonitora("dadw#dadw");
			
		} catch (EstruturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
