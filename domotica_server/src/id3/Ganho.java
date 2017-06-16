package id3;

import java.util.ArrayList;
import java.util.List;

public class Ganho {
	
	public static double calcula(double entropia, List<Atributo> atributos)
	{
		double gain = 0;
		double cont = 0;
		int qtdValSen =0;
		
		
		for(Atributo a: atributos)
		{
			if(a.getValSen()>qtdValSen)
			{
				qtdValSen = a.getValSen();
			}
		}
		
		qtdValSen++;
		
		for(int i=0; i<qtdValSen; i++)
		{
			cont=0;
			for(Atributo a: atributos)
			{	
				if(a.getValSen()==i)
				{
					cont++;
				}
			}
			
			//System.out.println("Entropia " + atributos.get(0).getS().getDescricao() + ":" + Entropia.cauculoSens(atributos, i, 0));
			gain -= ((cont/(double)atributos.size()) * Entropia.cauculoSens(atributos, i, 0));
			//System.out.println("Ganho" +  gain);
		}
		gain += entropia;
		return gain;
	}
	
	public static void main(String[] args)
	{
		List<Atributo> a = new ArrayList<Atributo>();
		
		List<Atributo> aPos = new ArrayList<Atributo>();
		List<Atributo> aNeg = new ArrayList<Atributo>();
		
		
		Atributo a1 = new Atributo();
		a1.setValAtu(ValorAtributo.ABERTO);
		a1.setValSen(0);
		a.add(a1);
		
		Atributo a2 = new Atributo();
		a2.setValAtu(ValorAtributo.ABERTO);
		a2.setValSen(0);
		a.add(a2);
		
		Atributo a3 = new Atributo();
		a3.setValAtu(ValorAtributo.FECHADO);
		a3.setValSen(0);
		a.add(a3);
		
		Atributo a4 = new Atributo();
		a4.setValAtu(ValorAtributo.FECHADO);
		a4.setValSen(0);
		a.add(a4);
		
		Atributo a5 = new Atributo();
		a5.setValAtu(ValorAtributo.FECHADO);
		a5.setValSen(1);
		a.add(a5);
		
		Atributo a6 = new Atributo();
		a6.setValAtu(ValorAtributo.ABERTO);
		a6.setValSen(1);
		a.add(a6);
		
		Atributo a7 = new Atributo();
		a7.setValAtu(ValorAtributo.FECHADO);
		a7.setValSen(1);
		a.add(a7);
		
		Atributo a8 = new Atributo();
		a8.setValAtu(ValorAtributo.ABERTO);
		a8.setValSen(0);
		a.add(a8);
		
		Atributo a9 = new Atributo();
		a9.setValAtu(ValorAtributo.FECHADO);
		a9.setValSen(1);
		a.add(a9);
		
		Atributo a10 = new Atributo();
		a10.setValAtu(ValorAtributo.FECHADO);
		a10.setValSen(1);
		a.add(a10);
		
		Atributo a11 = new Atributo();
		a11.setValAtu(ValorAtributo.FECHADO);
		a11.setValSen(1);
		a.add(a11);
		
		Atributo a12 = new Atributo();
		a12.setValAtu(ValorAtributo.FECHADO);
		a12.setValSen(0);
		a.add(a12);
		
		Atributo a13 = new Atributo();
		a13.setValAtu(ValorAtributo.FECHADO);
		a13.setValSen(1);
		a.add(a13);
		
		Atributo a14 = new Atributo();
		a14.setValAtu(ValorAtributo.ABERTO);
		a14.setValSen(0);
		a.add(a14);
		double entropia = Entropia.cauculo(a, ValorAtributo.ABERTO);
		
		System.out.println("ENTROPIA GERAL:" + entropia);
		
		System.out.println("GANHO:" + Ganho.calcula(entropia, a));
	}
}
