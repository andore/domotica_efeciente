package tratador;

import java.util.ArrayList;
import java.util.List;

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
import main.ControleServer;

public class TratadorMonitoramento extends AbstractTratador 
{
	final static Logger logger = Logger.getLogger(TratadorMonitoramento.class);
	private MensagemResp resp = new MensagemResp();
	
	public TratadorMonitoramento()
	{
		
	}
	
	public MensagemResp processa(Mensagem msg) throws StructException, DbException
	{
		atualizaSensores(msg.getMensagem());
		mantemIluminacao(ControleServer.getCenarioAtual());
		
		return resp;
	}
	
	private void atualizaSensores(String msg) throws StructException, DbException
	{
		StrMonitoramento vo = new StrMonitoramento(msg);
		
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
		StrMonitoramento resposta = new StrMonitoramento();
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
		resp.setMensagem(resposta.getString());
	}
}
