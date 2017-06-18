package tratador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import common.CodAtuador;
import common.CodigoModulo;
import common.CodigoSensores;
import common.ConversorValoreDiscreto;
import common.EstMensagem;
import common.EstMonitora;
import common.EstruturaException;
import common.MensagemResp;
import common.Status;
import common.StructException;
import dao.Atuador;
import dao.AtuadorDao;
import dao.Cenario;
import dao.DbException;
import dao.Historico;
import dao.HistoricoDao;
import dao.Sensor;
import dao.SensorDao;
import id3.VerificaArvore;
import main.ControleServer;

public class TratadorMonitoramento extends AbstractTratador 
{
	final static Logger logger = Logger.getLogger(TratadorMonitoramento.class);
	private EstMonitora resp = new EstMonitora(); 
	private boolean isSalvaHistorico;
	EstMonitora vo;
	public TratadorMonitoramento()
	{
		
	}
	
	public EstMonitora processa(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		atualiza(msg);
		
		if(isSalvaHistorico)
		{
			incluirHistorico(msg);
		}
		
		mantemIluminacao(ControleServer.getCenarioAtual(), msg);
		mantemTemperatura(ControleServer.getCenarioAtual(), msg);
		
		return resp;
	}
	
	private void atualiza(EstMensagem msg) throws StructException, DbException, EstruturaException
	{
		EstMonitora vo = new EstMonitora(msg.getStrIn());
		
		for(Sensor sensor: vo.getSensores())
		{
			updateSensor(sensor);
		}
		
		isSalvaHistorico = false;
		
		for(Atuador atuador: vo.getAtuadores())
		{
			updateAtuador(atuador);
		}
	}
	
	private void incluirHistorico(EstMensagem msg) throws EstruturaException, DbException{
		EstMonitora mon = new EstMonitora(msg.getStrIn());		
		Historico hist;
		HistoricoDao histDao = new HistoricoDao();
		int idHist = histDao.getNextId();
		Date data = new Date();		
		
		for(Sensor sensor: mon.getSensores()){
			hist = new Historico();	
			hist.setId_historico(idHist);
			hist.setValor_sensor(ConversorValoreDiscreto.getValorDiscretoSensor(sensor));
			hist.setId_sensor(sensor.getId());
			hist.setId_arduino(mon.getIdArduino());			
			hist.setData_criacao(data);
			hist.setRegistro(idHist);
			histDao.insere(hist);
		}
		
		for(Atuador atuador: mon.getAtuadores()){
			if(atuador.getStatus() != Status.AUTO_)
			{
				hist = new Historico();
				hist.setStatus_atuador(atuador.getStatus());
				hist.setId_atuador(atuador.getId());
				hist.setId_arduino(mon.getIdArduino());			
				hist.setId_historico(idHist);
				hist.setData_criacao(data);
				hist.setRegistro(idHist);
				histDao.insere(hist);
			}
		}
	}
	
	private void updateSensor(Sensor sensor) throws DbException
	{
		SensorDao dao = new SensorDao();
		Sensor sb = dao.serachById(sensor.getId());
		sb.setValor(sensor.getValor());
		dao.update(sb);
	}
	
	private void updateAtuador(Atuador atuador) throws DbException
	{
		AtuadorDao dao = new AtuadorDao();
		Atuador ab = dao.serachById(atuador.getId());
		if(ab.getStatus() != atuador.getStatus())
		{
			ab.setStatus(atuador.getStatus());
			dao.update(ab);
			isSalvaHistorico = true;
		}
	}
	
	private void mantemTemperatura(Cenario cenario, EstMensagem msg) throws EstruturaException, DbException
	{
		if(vo == null)
		{
			vo = new EstMonitora(msg.getStrIn());
		}
		VerificaArvore verificaId3 = new VerificaArvore();
		
		for(Atuador a : vo.getAtuadores())
		{
			if(CodigoModulo.getModuloAtuador(a.getCod()) == CodigoModulo.TEMPERATURA)
			{
				for(Atuador atuCen :cenario.getAtuadores())
				{
					if(atuCen.getId() == a.getId())
					{
						if(atuCen.getStatus() == Status.AUTO_)
						{
							int status = verificaId3.verifica(a.getId(), vo.getSensores());
							if(status != -1)
							{
								a.setStatus(status);
							}
						}
						else 
						{
							a.setStatus(atuCen.getStatus());
						}
					}
				}
			}
		}
			
		resp.setAtuadores(vo.getAtuadores());
		resp.setQtdAtuador(vo.getQtdAtuador());
	}
	
	private void mantemIluminacao(Cenario cenario, EstMensagem msg) throws DbException, StructException, EstruturaException
	{
		if(vo == null)
		{
			vo = new EstMonitora(msg.getStrIn());
		}
		VerificaArvore verificaId3 = new VerificaArvore();
		
		for(Atuador a : vo.getAtuadores())
		{
			if(CodigoModulo.getModuloAtuador(a.getCod()) == CodigoModulo.ILUMINACAO)
			{
				for(Atuador atuCen :cenario.getAtuadores())
				{
					if(atuCen.getId() == a.getId())
					{
						if(atuCen.getStatus() == Status.AUTO_)
						{
//							int status = verificaId3.verifica(a.getId(), vo.getSensores());
//							if(status != -1)
//							{
//								a.setStatus(status);
//							}
						}
						else 
						{
							a.setStatus(atuCen.getStatus());
						}
					}
				}
			}
		}
			
		resp.setAtuadores(vo.getAtuadores());
		resp.setQtdAtuador(vo.getQtdAtuador());
	}

	@Override
	public MensagemResp processaHtml(EstMensagem msg) throws StructException, DbException, EstruturaException {
		// TODO Auto-generated method stub
		return null;
	}
}
