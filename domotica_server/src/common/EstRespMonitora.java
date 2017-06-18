package common;

import java.util.ArrayList;
import java.util.List;
import dao.Atuador;
import dao.Sensor;

public class EstRespMonitora extends Estrutura {

	public EstRespMonitora(String str) throws EstruturaException {
		super(str);
	}
	
	public EstRespMonitora() {
		super();
	}
	
	private int operacao;
	private int qtdAtuador;
	private List<Atuador> atuadores;
	

	@Override
	protected void quebra() throws EstruturaException {
		
		operacao = getInt();
		
		qtdAtuador = getInt();
		atuadores = new ArrayList<Atuador>();
		for (int i = 0; i < qtdAtuador; i++) {
			Atuador buf = new Atuador();
			buf.setId(getInt());
			buf.setCod(getInt());
			buf.setStatus(getInt());
			atuadores.add(buf);
		}
	}

	@Override
	protected void montaStr() throws EstruturaException
	{
		put(operacao);
		put(qtdAtuador);
		for (int i = 0; i < qtdAtuador; i++) {
			put(atuadores.get(i).getId());
			put(atuadores.get(i).getCod());
			put(atuadores.get(i).getStatus());
		}
	}
	
	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public int getQtdAtuador() {
		return qtdAtuador;
	}

	public void setQtdAtuador(int qtdAtuador) {
		this.qtdAtuador = qtdAtuador;
	}

	public List<Atuador> getAtuadores() {
		return atuadores;
	}

	public void setAtuadores(List<Atuador> atuadores) {
		this.atuadores = atuadores;
	}
}
