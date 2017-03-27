package tratador;

import common.EstMensagem;
import common.EstruturaException;
import common.MensagemResp;
import common.StructException;
import dao.DbException;

public abstract class AbstractTratador
{
	abstract public MensagemResp processa(EstMensagem msg) throws StructException, DbException, EstruturaException;
}
