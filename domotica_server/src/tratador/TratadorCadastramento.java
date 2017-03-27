package tratador;

import org.apache.log4j.Logger;

import common.EstCadastra;
import common.EstMensagem;
import common.EstruturaException;
import common.Mensagem;
import common.MensagemResp;
import common.StrCadastraArduino;
import common.StructException;
import dao.Arduino;
import dao.ArduinoDao;
import dao.DbException;


public class TratadorCadastramento extends AbstractTratador 
{
	final static Logger logger = Logger.getLogger(TratadorCadastramento.class);
	private static final String SQL_STATE_UNIQUE_KEY = "23000";
	
	public TratadorCadastramento()
	{
		
	}
	
	public MensagemResp processa(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		if(msg==null)
		{
			return null;
		}
		
		EstCadastra cadastra = new EstCadastra(msg.getStrIn());
		Arduino arduino = new Arduino();
		ArduinoDao dao = new ArduinoDao();
		MensagemResp resp = new MensagemResp();
		
		
		if(cadastra!=null)
		{
			logger.debug("Cadastrando Arduino:[" + cadastra.getDescricaoArduino().trim() + "]");

			arduino.setId(msg.getIdArduino());
			arduino.setIp(msg.getIp());
			arduino.setDescricao(cadastra.getDescricaoArduino());
			arduino.setSensores(cadastra.getSensores());
			arduino.setAtuadores(cadastra.getAtuadores());
			arduino.setId_cenario_ativo(0);
			
			try
			{
				dao.insere(arduino);
			}
			catch (DbException e)
			{
				if(e.getSqlState()!=null && 
						e.getSqlState().equals(SQL_STATE_UNIQUE_KEY))
				{
					logger.info("Arduino já cadastrador");
				}
				else
				{
					throw e;
				}
			}			
			logger.debug("Cadastrado com Sucesso");
		}
		
		resp.setOperacao(msg.getOperacao());
		resp.setIp(msg.getIp());
		resp.setMensagem("0");
		return resp;
	}
	
}
