package common;

public class EstMensagem extends Estrutura{
	
	private String ip;
	
	private int operacao;
	private int idArduino;
	private String msg;
	
	public EstMensagem(String str) throws EstruturaException {
		super(str);
	}

	public EstMensagem() {
		super();
	}

	@Override
	protected void quebra() throws EstruturaException {
		operacao = getInt();
		idArduino = getInt();
	}

	@Override
	protected void montaStr() throws EstruturaException {
		put(operacao);
		put(idArduino);
		put(msg);
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public int getIdArduino() {
		return idArduino;
	}

	public void setIdArduino(int idArduino) {
		this.idArduino = idArduino;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
