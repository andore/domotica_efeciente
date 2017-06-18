package id3;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import common.ConversorValoreDiscreto;
import common.Status;

public class Entropia {
	final static Logger logger = Logger.getLogger(Entropia.class);
	public static double cauculo(List<Atributo> atributos, int valorAtuador) 
	{
		int contPositivo = 0;
		int contNegativo = 0;
		
		for(Atributo a: atributos)
		{
			if(a.getValAtu() == valorAtuador)
			{
				contPositivo++;
			}
			else
			{
				contNegativo++;
			}
		}
		
		return equacaoEntropia(contPositivo, contNegativo);
	}
	
	public static double cauculoSens(List<Atributo> atributos, int valorSen, int valAtu) 
	{
		int contPositivo = 0;
		int contNegativo = 0;
		
		for(Atributo a: atributos)
		{
			if(a.getValSen() == valorSen)
			{
				if(a.getValAtu() == valAtu)
				{
					contPositivo++;
				}
				else
				{
					contNegativo++;
				}
			}
			
		}
		
		
		if(contNegativo==0 && contPositivo==0)
		{
			logger.info("SENSOR " + atributos.get(0).getS().getDescricao() + " COM VALOR: [" + ConversorValoreDiscreto.getDescricaoValorSensor(atributos.get(0).getS().getCod(), valorSen) + "] NAO TEM DADO\n");
		}
		else if(contNegativo==0)
		{
			logger.info("SENSOR " + atributos.get(0).getS().getDescricao() + " COM VALOR: [" + ConversorValoreDiscreto.getDescricaoValorSensor(atributos.get(0).getS().getCod(), valorSen) + "] VAI SER STATUS " + Status.getStatus(valAtu) + '\n');
		}
		else if(contPositivo==0)
		{
			logger.info("SENSOR " + atributos.get(0).getS().getDescricao() + " COM VALOR: [" + ConversorValoreDiscreto.getDescricaoValorSensor(atributos.get(0).getS().getCod(), valorSen) + "] VAI SER STATUS " + Status.getStatus(1) + '\n');
		}
			
		return equacaoEntropia(contPositivo, contNegativo);
	}
	
	public static double equacaoEntropia(double contPositivo, double contNegativo)
	{
		double probabilidade =0;
		double entropia =0;

		if(contPositivo!=0)
		{
			probabilidade =  contPositivo / (contPositivo + contNegativo);
			entropia = -probabilidade * (Math.log(probabilidade) / Math.log(2));
		}
			
		if(contNegativo!=0)
		{
			probabilidade = contNegativo / (contPositivo + contNegativo);
			entropia += -probabilidade * (Math.log(probabilidade) / Math.log(2));
		}
				
		return entropia;
	}
	
	public static void main(String[] args) 
	{	
		Entropia e = new Entropia();
		
		List<Atributo> a = new ArrayList<Atributo>();
		
		for(int i=0; i<9; i++)
		{
			Atributo a1 = new Atributo();
			a1.setValAtu(ValorAtributo.ABERTO);
			a.add(a1);
		}
		
		for(int i=0; i<5; i++)
		{
			Atributo a1 = new Atributo();
			a1.setValAtu(ValorAtributo.FECHADO);
			a.add(a1);
		}
		
		System.out.println(e.cauculo(a, ValorAtributo.ABERTO));

	}
}
