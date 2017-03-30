package tratador;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import common.EstMensagem;
import common.EstMensagemResp;
import common.EstMonitora;
import common.EstruturaException;
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
import main.ControleServer;

public class TratadorMonitoramento extends AbstractTratador 
{
	final static Logger logger = Logger.getLogger(TratadorMonitoramento.class);
	private EstMonitora resp = new EstMonitora();
	
	public TratadorMonitoramento()
	{
		
	}
	
	public EstMonitora processa(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		atualizaSensores(msg);
		mantemIluminacao(ControleServer.getCenarioAtual());
		
		return resp;
	}
	
	private void atualizaSensores(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		EstMonitora vo = new EstMonitora(msg.getStrIn());
		
		for(Sensor sensor: vo.getSensores())
		{
			updateSensor(sensor);
		}
		
		for(Atuador atuador: vo.getAtuadores())
		{
			updateAtuador(atuador);
		}
	}
	
	private void updateSensor(Sensor sensor) throws DbException
	{
		SensorDao dao = new SensorDao();
		//Sensor buf = dao.serachById(sensor.getId());
		//dao.sessao.clear();
		
		//dao = new SensorDao();
		//sensor.setDescricao(buf.getDescricao());
		dao.update(sensor);
	}
	
	private void updateAtuador(Atuador atuador) throws DbException
	{
		AtuadorDao dao = new AtuadorDao();
		//Atuador buf = dao.serachById(atuador.getId());
		//dao.sessao.clear();
		
		//dao = new AtuadorDao();
		//atuador.setDescricao(buf.getDescricao());
		dao.update(atuador);
	}
	
	private void mantemTemperatura(Cenario cenario)
	{
		
	}
	
	private void mantemIluminacao(Cenario cenario) throws DbException, StructException
	{
		EstMonitora resposta = new EstMonitora();
		List<Atuador> atuadores = new ArrayList<Atuador>();
		AtuadorDao dao = new AtuadorDao();
		
		for(Atuador atuCen: cenario.getAtuadores())
		{
			Atuador atu = dao.serachById(atuCen.getId());
			
			if(atu.getStatus() != atuCen.getStatus())
			{
				atuadores.add(atuCen);
			}
		}
		resposta.setQtdSensor(0);
		resposta.setAtuadores(atuadores);
		resposta.setQtdAtuador(atuadores.size());
		resp = resposta;
	}

	@Override
	public MensagemResp processaHtml(EstMensagem msg) throws StructException, DbException, EstruturaException {
		// TODO Auto-generated method stub
		return null;
	}
}
