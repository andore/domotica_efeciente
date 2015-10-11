package tratador;

import common.Mensagem;
import common.StrCadastraArduino;
import common.StructException;
import dao.Arduino;
import dao.ArduinoDao;
import hbn.ControleHbn;

public class TratadorCadastramento 
{
	private ControleHbn db;
	
	public TratadorCadastramento()
	{
		db  = new ControleHbn();
	}
	
	public String processa(Mensagem msg) throws StructException
	{
		if(msg==null)
		{
			return null;
		}
		
		StrCadastraArduino cadastra = new StrCadastraArduino(msg.getMensagem());
		Arduino arduino = new Arduino();
		ArduinoDao dao = new ArduinoDao(db.getSession());
		
		if(cadastra!=null)
		{
			arduino.setId(msg.getIdArduino());
			arduino.setIp(msg.getIp());
			arduino.setDescricao(cadastra.getDescricaoArduino());
			arduino.setQtdSensor(cadastra.getQtdSensor());
			arduino.setSensores(cadastra.getSensores());
			arduino.setQtdAtuador(cadastra.getQtdAtuador());
			arduino.setAtuadores(cadastra.getAtuadores());
			
			dao.insereArduino(arduino);	
		}
		
		return"ok";
	}
	
}
