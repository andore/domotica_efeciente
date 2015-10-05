package main;

import common.Mensagem;

public class RoteadorOperacao 
{
	public void getOperacao(Mensagem msg)
	{
		
		if(msg != null)
		{
			switch (msg.getOperacao()) {
			case 1:
				System.out.println("Roteando mensagem para Tratador Cadastro");
				break;
				
			case 2:
				System.out.println("Roteando mensagem para Tratador Monitoramento");
				break;

			default:
				System.out.println("Opera��o n�o reconhecida!");
				break;
			}
		}
		else
		{
			System.out.println("Mensagem vazia!");
		}
	}

}
