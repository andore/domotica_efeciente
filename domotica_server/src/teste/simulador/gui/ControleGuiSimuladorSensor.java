package teste.simulador.gui;

import interfaceGrafica.AbstractControleGui;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.DbException;
import dao.Sensor;
import dao.SensorDao;

public class ControleGuiSimuladorSensor extends AbstractControleGui implements ListenerGuiSimuladorSensor{
	private GuiSimuladorSensor janela = new GuiSimuladorSensor(this);
	private List <Arduino> arduinos;
	private int indexArduino = 0;
	SensorDao sensorDao;
	private ListenerControleGuisimuladorSensor listener;
	
	public ControleGuiSimuladorSensor(ListenerControleGuisimuladorSensor listener)
	{
		
		this.listener = listener;
		try 
		{
			setArduinos();
			setSensores();
		} 
		catch (DbException e)
		{
			e.printStackTrace();
		}
	}
	
	private List getArduinos() throws DbException
	{
		ArduinoDao dao = new ArduinoDao();
		arduinos = dao.loadArduino();
		
		return arduinos;
	}
	
	private void setArduinos() throws DbException
	{
		if(arduinos == null)
		{
			getArduinos();
		}
		janela.comboBox.setModel(new DefaultComboBoxModel(getDescriArduino(arduinos)));
	}
	
	private String[] getDescriArduino(List <Arduino> arduinos)
	{
		String [] buf = new String[arduinos.size()];
		
		for(int i=0; i<buf.length; i++)
		{
			buf[i] = arduinos.get(i).getDescricao();
		}
		
		return buf;
	}
	
	private void setSensores()
	{
		for(Sensor s: arduinos.get(indexArduino).getSensores())
		{
			janela.setLinha(s.getDescricao(), String.valueOf(s.getValor()));
		}
	}

	public GuiSimuladorSensor getJanela() {
		return janela;
	}

	public void setJanela(GuiSimuladorSensor janela) {
		this.janela = janela;
	}

	public void alteraArduino(int index)
	{
		this.indexArduino = index;
		janela.reset();
		setSensores();
	}

	public void alteraValor(int index, String valor) {
		System.out.println("ALTERA SENSOR" + arduinos.get(indexArduino).getSensores().get(index).getDescricao() + " VALOR:" + valor);
		arduinos.get(indexArduino).getSensores().get(index).setValor(Integer.parseInt(valor));
		listener.alteraValor(arduinos.get(indexArduino), index);
	}
	
	private void alteraBanco(int index, String valor)
	{
		arduinos.get(indexArduino).getSensores().get(index).setValor(Integer.parseInt(valor));
		try 
		{
			if(sensorDao == null){
				sensorDao = new SensorDao();
			}
			sensorDao.update(arduinos.get(indexArduino).getSensores().get(index));
			getArduinos();
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Alterado com SUCESSO");	
	}
}
