package common;

public class EstMensagemResp extends Estrutura {

	public EstMensagemResp(String str) throws EstruturaException {
		super(str);
		// TODO Auto-generated constructor stub
	}
	
	public EstMensagemResp() {
		super();
	}

	String resp;
	int operacao;
	
	@Override
	protected void quebra() throws EstruturaException {
		
		operacao = getInt();
		resp = getString();
	}
	
	@Override
	protected void montaStr() throws EstruturaException {
		put(operacao);
		put(resp);
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}
	
}
