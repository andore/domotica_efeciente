package common;

public class CodigoModulo {
	public static final int TEMPERATURA = 0;
	public static final int ILUMINACAO = 1;
	public static final int PRESENCA = 2;
	
	public static int getModuloSensor(int cod)
	{
		switch (cod)
		{
			case CodigoSensores.TEMPERATURA:
			case CodigoSensores.TEMPERATURA_EXTERNO:
			case CodigoSensores.UMIDADE:
			case CodigoSensores.CHUVA:
				return TEMPERATURA;
				
			case CodigoSensores.LUZ:
			case CodigoSensores.LUZ_EXTERNO:
				return ILUMINACAO;
				
			case CodigoSensores.MOVIMENTO:
				return PRESENCA;
	
			default:
				return -1;
		}
	}
}
