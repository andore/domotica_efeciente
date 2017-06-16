package id3;

import dao.Atuador;
import dao.Sensor;

public class Atributo
{	
	private Sensor s;
	private Atuador a;
	private int valSen;
	private int valAtu;
	private int reg;
	
	
	public Atributo()
	{
		valAtu = -99;
		valSen = -99;
	}
	
	public Sensor getS() {
		return s;
	}
	public void setS(Sensor s) {
		this.s = s;
	}
	public int getValSen() {
		return valSen;
	}
	public void setValSen(int valSen) {
		this.valSen = valSen;
	}
	public int getValAtu() {
		return valAtu;
	}
	public void setValAtu(int valAtu) {
		this.valAtu = valAtu;
	}
	public int getReg() {
		return reg;
	}
	public void setReg(int reg) {
		this.reg = reg;
	}
	public Atuador getA() {
		return a;
	}
	public void setA(Atuador a) {
		this.a = a;
	}
}
