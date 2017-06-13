package id3m;

import java.util.List;

public class Registro {

	private List<Atributo> atrs;
	private int id_reg;
	
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
}
