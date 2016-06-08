package tratador;

import org.apache.log4j.Logger;

import common.Mensagem;
import common.MensagemResp;
import common.StrMonitoramento;
import common.StructException;
import dao.Atuador;
import dao.AtuadorDao;
import dao.Cenario;
import dao.DbException;
import dao.Sensor;
import dao.SensorDao;

public class TratadorMonitoramento extends AbstractTratador 
{
	final static Logger logger = Logger.getLogger(TratadorMonitoramento.class);
	private MensagemResp resp = new MensagemResp();
	
	public TratadorMonitoramento()
	{
		
	}
	
	public MensagemResp processa(Mensagem msg) throws StructException, DbException
	{
		StrMonitoramento vo = new StrMonitoramento(msg.getMensagem());
		
		for(Sensor sensor: vo.getSensores())
		{
			updateSensor(sensor);
		}
		
		for(Atuador atuador: vo.getAtuadores())
		{
			updateAtuador(atuador);
		}
		
		return resp;
	}
	
	private void updateSensor(Sensor sensor) throws DbException
	{
		SensorDao dao = new SensorDao();
		dao.update(sensor);
	}
	
	private void updateAtuador(Atuador atuador) throws DbException
	{
		AtuadorDao dao = new AtuadorDao();
		dao.update(atuador);
	}
	
	private void mantemTemperatura(Cenario cenario)
	{
		
	}
	
	private void mantemIluminacao(Cenario cenario)
	{
		
	}
}
