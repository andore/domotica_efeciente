package teste;

import java.util.ArrayList;
import java.util.List;

import common.Mensagem;
import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.DbException;
import dao.Sensor;

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
	
	public static void main(String[] args) 
	{	
		
		TesteDB teste = new TesteDB();
		
		
		Mensagem resp =  null;
		resp = new Mensagem();
		resp.setOperacao(0);
		resp.setIp("192.168.1.2");
		resp.setMensagem("001");
		resp.setIdArduino(0);
		
		teste.testeInsere(resp);
	}

}
