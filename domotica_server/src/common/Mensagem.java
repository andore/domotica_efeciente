package common;

import net.NetService;

public class Mensagem 
{
	private final int OPERACAO_SIZE = 2;
	private final int IDARDUINO_SIZE = 3;
	private final int POS_MENSAGEM = 1024; 

	private StringBuffer pacote;
	private String ip;
	private int operacao;
	private int idArduino;
	private String mensagem;
	
	public Mensagem (String pacote, String ip) throws MensagemException
	{		
		this.ip = ip;
		
		try
		{
			this.pacote = new StringBuffer(pacote);
			this.operacao = Integer.parseInt(pacote.substring(0, OPERACAO_SIZE));
			this.idArduino = Integer.parseInt(pacote.substring(OPERACAO_SIZE, OPERACAO_SIZE + IDARDUINO_SIZE));
			this.mensagem = pacote.substring(OPERACAO_SIZE + IDARDUINO_SIZE);
		}catch(Exception e)
		{
			System.out.println("Erro ao quebrar mensagem:" + "[" + pacote.toString() + "]");
			throw new MensagemException("Erro ao quebrar mensagem:" + "[" + pacote.toString() + "]", e.getMessage());
		}
		
		
	}
	
	public StringBuffer getPacote() {
		return pacote;
	}

	public void setPacote(StringBuffer pacote) {
		this.pacote = pacote;
	}

	public int getIdArduino() {
		return idArduino;
	}
	public void setIdArduino(int idArduino) {
		this.idArduino = idArduino;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getOperacao() {
		return operacao;
	}
	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}
	
	public static void main(String[] args) {
		Mensagem teste;
		try {
			teste = new Mensagem("01999mensagem", "192.168.0.1");
			System.out.println("ip:" + teste.getIp());
			System.out.println("id_arduino:" + teste.getIdArduino());
			System.out.println("operacao:" + teste.getOperacao());
			System.out.println("mensagem:" + teste.getMensagem());
		} catch (MensagemException e) {
			// TODO Auto-generated catch block
		
		}
		
	} 
	
}
