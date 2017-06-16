package id3m;

import java.util.List;

public class No {
	private No pai;
	private List<No> filhos;
	private List<Registro> reg;
	private Atributo atr;
	private Integer valFolha;
	private Integer valPai;
	
	public No getPai() {
		return pai;
	}
	public void setPai(No pai) {
		this.pai = pai;
	}
	public List<No> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<No> filhos) {
		this.filhos = filhos;
	}
	public List<Registro> getReg() {
		return reg;
	}
	public void setReg(List<Registro> reg) {
		this.reg = reg;
	}
	public Atributo getAtr() {
		return atr;
	}
	public void setAtr(Atributo atr) {
		this.atr = atr;
	}
	public Integer getValFolha() {
		return valFolha;
	}
	public Integer getValPai() {
		return valPai;
	}
	public void setValPai(Integer valSen) {
		this.valPai = valSen;
	}
	public void setValFolha(Integer valFolha) {
		this.valFolha = valFolha;
	}
	
}
