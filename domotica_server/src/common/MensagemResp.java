package common;

public class MensagemResp 
{
	private int operacao;
	private String ip;
	private String mensagem;
	private StringBuffer pacote;
	
	public MensagemResp()
	{
		pacote = new StringBuffer();
	}
	
	public String getPacote()
	{
		pacote.append(operacao);
		
		if(mensagem!=null)
		{
			pacote.append(mensagem);
		}
		
		return pacote.toString();
	}
	
	public int getOperacao()
	{
		return operacao;
	}
	public void setOperacao(int operacao)
	{
		this.operacao = operacao;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getMensagem()
	{
		return mensagem;
	}
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}
	
	
	

}
