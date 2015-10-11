package main;

import tratador.TratadorCadastramento;
import common.Mensagem;
import common.StructException;
import dao.DbException;

public class RoteadorOperacao 
{
	private TratadorCadastramento cadastra; 
	
	public void getOperacao(Mensagem msg) throws StructException, DbException
	{
		
		if(msg != null)
		{
			switch (msg.getOperacao()) {
			case 1:
				System.out.println("Roteando mensagem para Tratador Cadastro");
				cadastra = new TratadorCadastramento();
				cadastra.processa(msg);
				break;
				
			case 2:
				System.out.println("Roteando mensagem para Tratador Monitoramento");
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
