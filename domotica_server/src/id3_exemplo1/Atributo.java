package id3_exemplo1;

abstract public class Atributo {
	private String nome;
	private double valor;
	private boolean isConhecido;
	private double substituto;
	
	public Atributo(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
		substituto = -1;
		isConhecido = false;
	}
	
	public Atributo(String nome, String valor) {
		this.nome = nome;
		try {
			this.valor = Double.valueOf(valor);
			this.isConhecido = false;

		}
		catch(NumberFormatException nfe) {
			this.valor = -1;
			this.isConhecido = true;
		}
		
		substituto = -1;
	}
	
	public void setName(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setSubstituto(double surrogate) {
		this.substituto = surrogate;
	}

	public double getSurrogate() {
		return substituto;
	}

	public void setDesconhedico(boolean isConhecido) {
		this.isConhecido = isConhecido;
	}

	public boolean isConhecido() {
		return isConhecido;
	}
}
