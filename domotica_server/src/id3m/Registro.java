package id3m;

import java.util.List;

public class Registro {

	private List<Atributo> atrs;
	private int id_reg;
	private int valAtu;
	
	public List<Atributo> getAtrs() {
		return atrs;
	}

	public void setAtrs(List<Atributo> atrs) {
		this.atrs = atrs;
	}

	public int getId_reg() {
		return id_reg;
	}

	public void setId_reg(int id_reg) {
		this.id_reg = id_reg;
	}

	public int getValAtu() {
		return valAtu;
	}

	public void setValAtu(int valAtu) {
		this.valAtu = valAtu;
	}

}
