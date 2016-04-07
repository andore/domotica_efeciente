package tratador;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import common.Mensagem;
import common.MensagemResp;
import common.Status;
import common.StrCadastraArduino;
import common.StructException;
import dao.AbstractDao;
import dao.Arduino;
import dao.ArduinoDao;
import dao.DbException;
import dao.Sensor;
import hbn.ControleHbn;

public class TratadorCadastramento 
{
	private Session sessao;
	final static Logger logger = Logger.getLogger(TratadorCadastramento.class);
	public TratadorCadastramento(Session sessao)
	{
		this.sessao = sessao;
	}
	
	public MensagemResp processa(Mensagem msg) throws StructException, DbException
	{
		if(msg==null)
		{
			return null;
		}
		
		StrCadastraArduino cadastra = new StrCadastraArduino(msg.getMensagem());
		Arduino arduino = new Arduino();
		ArduinoDao dao = new ArduinoDao(sessao);
		MensagemResp resp = new MensagemResp();
		
		
		if(cadastra!=null)
		{
			logger.debug("Cadastrando Arduino:[" + cadastra.getDescricaoArduino().trim() + "]");

			arduino.setId(msg.getIdArduino());
			arduino.setIp(msg.getIp());
			arduino.setDescricao(cadastra.getDescricaoArduino());
			arduino.setSensores(cadastra.getSensores());
			arduino.setAtuadores(cadastra.getAtuadores());
			
			dao.insere(arduino);	
			logger.debug("Cadastrado com Sucesso");
		}
		
		resp.setOperacao(msg.getOperacao());
		resp.setIp(msg.getIp());
		resp.setMensagem("0");
		return resp;
	}
	
}
