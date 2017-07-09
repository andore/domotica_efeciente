package tratador;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import common.CodigoSensores;
import common.ConversorValoreDiscreto;
import common.EstMensagem;
import common.EstMensagemResp;
import common.EstruturaException;
import common.MensagemResp;
import common.StructException;
import dao.Arduino;
import dao.ArduinoDao;
import dao.DbException;
import dao.Sensor;

public class TratadorMonitoramentoHtml extends AbstractTratador {

	final static Logger logger = Logger.getLogger(TratadorMonitoramentoHtml.class); 
	private String ipServer;
	private int porta = 9994;
	
	
	public TratadorMonitoramentoHtml()
	{
		ipServer = getIp();
	}
	
	@Override
	public MensagemResp processaHtml(EstMensagem msg) throws StructException, DbException
	{
		MensagemResp resp = new MensagemResp();
		resp.setOperacao(2);
		resp.setMensagem(getHtmlMsgTeste());
		
		return resp;
	}
	
	public String getHtmlMsgTeste() throws DbException
	{
		String buf = "";
		ArduinoDao dao;
		String infSensores = "";
		
		
		dao = new ArduinoDao();
		for(Arduino arduino:dao.loadArduino())
		{
			infSensores += "<b> <br>" + arduino.getDescricao() + "</b>\r\n";
				
			for(Sensor s:arduino.getSensores())
			{
				if(s.getCod() == CodigoSensores.LUZ)
				{
					infSensores += "<p>Iluminação = " + s.getValor() + "%</p>\r\n";
				}
				else if(s.getCod() == CodigoSensores.TEMPERATURA)
				{
					infSensores += "<p>Temperarura = " + s.getValor() + "°C</p>\r\n";
				}
				else if(s.getCod() == CodigoSensores.TEMPERATURA_EXTERNO)
				{
					infSensores += "<p>" + s.getDescricao() + " = " + s.getValor() + "°C</p>\r\n";
				}
				else
				{
					infSensores += "<p>" + s.getDescricao() + " = " + ConversorValoreDiscreto.getDescricaoValorSensor(s.getCod(), s.getValor()) + "</p>\r\n";
				}
			}
					
			buf = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<!DOCTYPE HTML>\r\n<html>\r\n";
			buf += "<font size=\"20\"><h3>Domotica Web Service</h3>";
			buf += "<hr />";
			//buf += "<p>Lampada <a href=\"?function=led4_on\"><button style=\"height:60px;width:150px\"><font size=\"20\">ON</button></a><a href=\"?function=led4_off\"><button style=\"height:60px;width:150px\"><font size=\"20\">OFF</button></a></p>";
			//buf += "<p>Ar-Condicionado <a href=\"?function=led5_on\"><button style=\"height:60px;width:150px\"><font size=\"20\">ON</button></a><a href=\"?function=led5_off\"><button style=\"height:60px;width:150px\"><font size=\"20\">OFF</button></a></p>";
				
			if(infSensores.trim().length() > 0)
			{
				//buf += "<br>";
				//buf += "<h3>Monitoração</h3>";
				buf += infSensores;
			}
		}
		buf += "<meta http-equiv=\"refresh\" content=\"3; http://" + ipServer +":"+ porta +"\">";
		buf += "</html>\n";
		return buf;
	}
	
	public String getIp()
	{
		InetAddress ia = null;
		try 
		{
			ia = InetAddress.getLocalHost();
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return "";
		}
		return ia.getHostAddress();
	}

	@Override
	public EstMensagemResp processa(EstMensagem msg) throws StructException, DbException, EstruturaException {
		// TODO Auto-generated method stub
		return null;
	}

}
