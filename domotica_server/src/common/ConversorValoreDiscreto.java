package common;

import dao.Sensor;
import main.ControleServer;
import tratador.ValoresDiscretos;

public class ConversorValoreDiscreto {
	
	private static int MARGEM_TEMPERATURA = 2;
	private static int MARGEM_ILUMINACAO = 33;
	public static int getValorDiscretoSensor(Sensor s)
	{
		switch (s.getCod()) {
		case CodigoSensores.TEMPERATURA:
		case CodigoSensores.TEMPERATURA_EXTERNO:
			if(Math.abs(ControleServer.getCenarioAtual().getValor_temperatura() - s.getValor()) > MARGEM_TEMPERATURA)
			{
				if(s.getValor() > ControleServer.getCenarioAtual().getValor_temperatura())
				{
					return ValoresDiscretos.ALTA;
				}
				else
				{
					return ValoresDiscretos.BAIXA;
				}
			}
			else
			{
				return ValoresDiscretos.IDEAL;
			}
		
		case CodigoSensores.LUZ:
		case CodigoSensores.LUZ_EXTERNO:
			if(Math.abs(ControleServer.getCenarioAtual().getValor_iluminacao() - s.getValor()) > MARGEM_ILUMINACAO)
			{
				if(s.getValor() > ControleServer.getCenarioAtual().getValor_iluminacao())
				{
					return ValoresDiscretos.ALTA;
				}
				else
				{
					return ValoresDiscretos.BAIXA;
				}
			}
			else
			{
				return ValoresDiscretos.IDEAL;
			}
			
		case CodigoSensores.CHUVA:
		case CodigoSensores.UMIDADE:
		case CodigoSensores.MOVIMENTO:
			if(s.getValor()==0)
			{
				return ValoresDiscretos.NAO;
			}
			else
			{
				return ValoresDiscretos.SIM;
			}
		
		default:
			break;
		}
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		Sensor s = new Sensor();
		s.setId(5);
		s.setCod(6);
		s.setValor(23);
		s.setDescricao("TempExter");
		System.out.println(ConversorValoreDiscreto.getValorDiscretoSensor(s));

	}

}
