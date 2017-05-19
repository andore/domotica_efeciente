package tratador;

import common.EstMensagem;
import common.Estrutura;
import common.EstruturaException;
import common.MensagemResp;
import common.StructException;
import dao.DbException;

public abstract class AbstractTratador
{
	abstract public Estrutura processa(EstMensagem msg) throws StructException, DbException, EstruturaException;
	abstract public MensagemResp processaHtml(EstMensagem msg) throws StructException, DbException, EstruturaException;
	
}
