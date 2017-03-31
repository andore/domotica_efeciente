package common;

public class EstMensagemResp extends EstMensagem {

	public EstMensagemResp(String str) throws EstruturaException {
		super(str);
		// TODO Auto-generated constructor stub
	}
	
	public EstMensagemResp() {
		super();
	}

	String resp;
	
	@Override
	protected void quebra() throws EstruturaException {
		super.quebra();
		resp = getString();
	}
	
	@Override
	protected void montaStr() throws EstruturaException {
		super.montaStr();
		put(resp);
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}
	
	

}
