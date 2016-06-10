package net;

import common.Mensagem;

public interface NetListener 
{	
	public void netRecebe(Mensagem msg);
	public void netRecebe(String msg);
}
