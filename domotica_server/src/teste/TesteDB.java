package teste;

import hbn.ControleHbn;

import java.util.ArrayList;
import java.util.List;

import common.Mensagem;
import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.DbException;
import dao.Sensor;

public class TesteDB {
	private ControleHbn bd;	
	
	public TesteDB() {
		bd = new ControleHbn();
	}
	
	public void testeInsere(Mensagem msg)
	{
		Arduino arduino = new Arduino();
		arduino.setId(msg.getIdArduino());
		arduino.setDescricao("Sala");
		arduino.setIp(msg.getIp());;
		arduino.setQtdAtuador(0);
		arduino.setQtdAtuador(0);
		
		List<Sensor>  sensores = new ArrayList<Sensor>();
		Sensor sensor = new Sensor();
	    sensor.setDescricao("Temperatura");
		sensor.setId(999);
		sensores.add(sensor);
		arduino.setSensores(sensores);
		ArduinoDao perssist = new ArduinoDao(bd.getSession());
		
		List<Atuador>  atuadores = new ArrayList<Atuador>();
		Atuador atuador = new Atuador();
	    atuador.setDescricao("Temperatura");
	    atuador.setId(1);
	    atuadores.add(atuador);
		arduino.setAtuadores(atuadores);
		
		try {
			perssist.insereArduino(arduino);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
