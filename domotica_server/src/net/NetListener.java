package net;

import common.EstMensagem;
import common.EstruturaException;

public interface NetListener 
{	
	public void netRecebe(EstMensagem msg) throws EstruturaException;
	public void netRecebe(String msg) throws EstruturaException;
}
