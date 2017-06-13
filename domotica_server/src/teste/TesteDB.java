package teste;

import java.util.ArrayList;
import java.util.List;

import common.Mensagem;
import common.Status;
import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.AtuadorDao;
import dao.DbException;
import dao.Historico;
import dao.HistoricoDao;
import dao.Sensor;
import dao.SensorDao;

public class TesteDB 
{
	public TesteDB() {
		
	}
	
	public void testeInsere(Mensagem msg)
	{
		Arduino arduino = new Arduino();
		arduino.setId(msg.getIdArduino());
		arduino.setDescricao("Sala");
		arduino.setIp(msg.getIp());;
		
		List<Sensor>  sensores = new ArrayList<Sensor>();
		Sensor sensor = new Sensor();
	    sensor.setDescricao("Temperatura");
		sensor.setId(999);
		sensores.add(sensor);
		arduino.setSensores(sensores);
		ArduinoDao perssist = null;
		try {
			perssist = new ArduinoDao();
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<Atuador>  atuadores = new ArrayList<Atuador>();
		Atuador atuador = new Atuador();
	    atuador.setDescricao("Temperatura");
	    atuador.setId(1);
	    atuadores.add(atuador);
		arduino.setAtuadores(atuadores);
		
		try {
			perssist.insere(arduino);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void TesteCarregaDados()
	{
		ArduinoDao dao = null;
		try {
			dao = new ArduinoDao();
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			try
			{
				dao.loadArduino();
			} catch (DbException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void testeHistorico()
	{
		try 
		{
			HistoricoDao d = new HistoricoDao();
			AtuadorDao atuDao = new AtuadorDao();
			List<Historico> lh = d.getByAtuId(2);
			
			for(Historico h: lh)
			{
				System.out.println("ID_HISTORICO:" + h.getId_historico());
				System.out.println("Data de criacao:" + h.getData_criacao());
				System.out.println("ID_ATUADOR:" + h.getId_atuador());
				System.out.println("DESCRICAO:" + atuDao.serachById(h.getId_atuador()).getDescricao());
				System.out.println("STATUS:" + Status.getStatus(h.getStatus_atuador()).toString() + "\n\n");
			}
			
		} 
		catch (DbException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void testeHistoricoReg()
	{
		try 
		{
			HistoricoDao d = new HistoricoDao();
			AtuadorDao atuDao = new AtuadorDao();
			SensorDao senDao = new SensorDao();
			List<Historico> lh = d.getByRegId(61);
			
			for(Historico h: lh)
			{
				System.out.println("ID_HISTORICO:" + h.getId_historico());
				System.out.println("ID_REGISTRO:" + h.getRegistro());
				System.out.println("Data de criacao:" + h.getData_criacao());
				
				if(h.getId_atuador()!=null)
				{
					System.out.println("ID_ATUADOR:" + h.getId_atuador());
					System.out.println("DESCRICAO:" + atuDao.serachById(h.getId_atuador()).getDescricao());
					System.out.println("STATUS:" + Status.getStatus(h.getStatus_atuador()).toString() + "\n\n");
				}
				
				if(h.getId_sensor()!=null)
				{
					System.out.println("ID_SENSOR:" + h.getId_sensor());
					System.out.println("DESCRICAO:" + senDao.serachById(h.getId_sensor()).getDescricao());
					System.out.println("VALOR:" + h.getValor_sensor() + "\n\n");
				}
				
			}
			
		} 
		catch (DbException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{	
		
		TesteDB teste = new TesteDB();
		
		
//		Mensagem resp =  null;
//		resp = new Mensagem();
//		resp.setOperacao(0);
//		resp.setIp("192.168.1.2");
//		resp.setMensagem("001");
//		resp.setIdArduino(0);
//		teste.testeInsere(resp);
		
		teste.testeHistoricoReg();
	}

}
