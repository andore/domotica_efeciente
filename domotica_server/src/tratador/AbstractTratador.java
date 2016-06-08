package tratador;

import common.Mensagem;
import common.MensagemResp;
import common.StructException;
import dao.DbException;

public abstract class AbstractTratador
{
	abstract public MensagemResp processa(Mensagem msg) throws StructException, DbException;
}
