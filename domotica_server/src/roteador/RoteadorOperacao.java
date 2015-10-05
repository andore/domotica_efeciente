package roteador;

import common.Mensagem;

public class RoteadorOperacao 
{
	public void getOperacao(Mensagem msg)
	{
		
		if(msg != null)
		{
			switch (msg.getOperacao()) {
			case 1:
				System.out.println("Roteando mensagem para Tratador Operação Cadastro");
				break;
				
			case 2:
				System.out.println("Roteando mensagem para Tratador Operação Monitoramento");
				break;

			default:
				System.out.println("Operação não reconhecida!");
				break;
			}
		}
		else
		{
			System.out.println("Mensagem vazia!");
		}
	}

}
