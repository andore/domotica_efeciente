package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import common.CodAtuador;
import common.CodigoSensores;
import common.Status;
import dao.Arduino;
import dao.ArduinoDao;
import dao.Atuador;
import dao.Cenario;
import dao.CenarioDao;
import dao.DbException;
import dao.Sensor;

public class ControleGuiMonitoracao extends AbstractControleGui implements ListenerGuiMonitoracao
{
	private GuiMonitoracao janela;
	private List <Arduino> arduinos;
	private List <Atuador> lampadas;
	public Arduino arduinoSelecionado;
	private Cenario cenarioPadrao;
	private AtualizaJanelaMonitoracao atualizaMonitoramento;
	boolean atuailza;
	
	private ListenerCtrlCadastraMonitoracao listener;
	
	public ControleGuiMonitoracao(ListenerCtrlCadastraMonitoracao listener) throws DbException
	{
		this.listener = listener;
		janela = new GuiMonitoracao(this);
		lampadas = new ArrayList<Atuador>();
		
		CenarioDao cenarioDao = new CenarioDao();
		
		if(cenarioDao.loadCenario()!= null && !cenarioDao.loadCenario().isEmpty() )
		{
			cenarioPadrao = new CenarioDao().loadCenario().get(0);
		}	
		else
		{
			cenarioPadrao = new Cenario();
			cenarioPadrao.setId_arduino(0);
			cenarioPadrao.setNome_cenario("Cenario Padrao");
			cenarioPadrao.setValor_iluminacao(100);
			cenarioPadrao.setValor_temperatura(24);
			cenarioDao.insere(cenarioPadrao);
		}
		
		atuailza = true;
		atualizaMonitoramento = new AtualizaJanelaMonitoracao();
		atualizaMonitoramento.run();
		janela.tempAtual.setText("?");
		janela.iluAtual.setText("?");
	}
	
	@Override
	public JPanel getJanela()
	{
		// TODO Auto-generated method stub
		return janela;
	}
	
	private void atualiza() throws DbException
	{
		ArduinoDao ardDao = new ArduinoDao();
		setDados(ardDao.loadArduino());
		janela.repaint();
	}
	
	public void setDados(List <Arduino> arduinos)
	{
		this.arduinos = arduinos;
		
		if(arduinos!=null && arduinos.size()>0)
		{
			
			habilitaDesabilitaCampo(janela.comodo, true);
			
			if(!janela.comodo.hasFocus())
			{
				janela.comodo.setModel(new DefaultComboBoxModel(getDescriArduino(arduinos)));
			}
				
			setDispositivos(this.arduinos.get(0));
			setLampadas(this.lampadas);
			
			habilitaDesabilitaCampo(janela.temp, true);
			habilitaDesabilitaCampo(janela.ilum, true);
	
			janela.tempAtual.setText(buscaValorSensor(this.arduinoSelecionado, CodigoSensores.TEMPERATURA));
			janela.iluAtual.setText(buscaValorSensor(this.arduinoSelecionado, CodigoSensores.LUZ));
		}
	}
	
	private String buscaValorSensor(Arduino ardu, int codSensor)
	{
		int pos = 0;
		
		for(Sensor s: ardu.getSensores())
		{
			if(s.getCod() == codSensor)
			{
				return String.valueOf(s.getValor());
			}
			
			pos++;
		}
		return "?";
	}
	
	private void setDispositivos(Arduino arduino)
	{
		this.arduinoSelecionado = arduino;
		lampadas.clear();
		
		if(!janela.lampIndex.hasFocus())
		{
			janela.lampIndex.setModel(new DefaultComboBoxModel(new String []{""}));
		}
			
		habilitaDesabilitaCampo(janela.lampIndex, false);
		habilitaDesabilitaCampo(janela.arCond, false);
		habilitaDesabilitaCampo(janela.ventilador, false);
		habilitaDesabilitaCampo(janela.aquecedor, false);
		habilitaDesabilitaCampo(janela.lampSta, false);
		habilitaDesabilitaCampo(janela.janela, false);
		habilitaDesabilitaCampo(janela.lampSli, false);
		
		if(arduino!=null)
		{
			List<Atuador> atuadores = arduino.getAtuadores();
			for(Atuador atuador: atuadores)
			{
				//if(atuador.getStatus()==null)
				{
					//atuador.setStatus(Status.AUTO);
				}
				switch(atuador.getCod())
				{
					case CodAtuador.LAMPADA:
						lampadas.add(atuador);
						break;
					case CodAtuador.LAMPADA_DIMERIZAVEL:
						lampadas.add(atuador);
						break;
					case CodAtuador.PERCIANA:
						habilitaDesabilitaCampo(janela.perciana, true);
						break;
					case CodAtuador.JANELA:
						habilitaDesabilitaCampo(janela.janela, true);
						break;
					case CodAtuador.VENTILADOR:
						habilitaDesabilitaCampo(janela.ventilador, true);
						break;
					case CodAtuador.AR_CONDICIONADO:
						habilitaDesabilitaCampo(janela.arCond, true);
						break;
					case CodAtuador.AQUECEDOR:
						habilitaDesabilitaCampo(janela.aquecedor, true);
						break;
						
					default:
						break;
						
				}
			}
		}
		
	}
	
	private void setLampadas(List <Atuador> lampadas)
	{
		if(lampadas!=null && lampadas.size()>0)
		{
			if(!janela.lampIndex.hasFocus())
			{
				janela.lampIndex.setModel(new DefaultComboBoxModel(getDescricaoLampadas(lampadas)));
			}	
				
			habilitaDesabilitaCampo(janela.lampIndex, true);
			habilitaDesabilitaCampo(janela.lampSta, true);
				
			if(lampadas.get(0).getCod() == CodAtuador.LAMPADA_DIMERIZAVEL)
			{
				habilitaDesabilitaCampo(janela.lampSli, true);
				habilitaDesabilitaCampo(janela.lampPorcent, true);
			}
			else
			{			
				habilitaDesabilitaCampo(janela.lampSli, false);
				habilitaDesabilitaCampo(janela.lampPorcent, false);
			}			
		}
		else
		{
			habilitaDesabilitaCampo(janela.lampIndex, false);
			habilitaDesabilitaCampo(janela.lampSta, false);
		}
	}
	
	private void habilitaDesabilitaCampo(JComponent componenete, boolean habilita)
	{
		if(!componenete.hasFocus())
		{
			componenete.setEnabled(habilita);
		}
	}
	
	
	private String[] getDescricaoLampadas(List <Atuador> lampadas)
	{
		String [] buf = new String[lampadas.size()];
		
		for(int i=0; i<buf.length; i++)
		{
			buf[i] = lampadas.get(i).getDescricao();
		}
		
		return buf;
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
	
	private void setCompDisable()
	{
		janela.arCond.setEnabled(false);
		janela.ventilador.setEnabled(false);
		janela.aquecedor.setEnabled(false);
		janela.lampSta.setEnabled(false);
		janela.janela.setEnabled(false);
		janela.temp.setEnabled(false);
		janela.ilum.setEnabled(false);
		janela.lampSli.setEnabled(false);
	}

	public void setComodo(int index)
	{
		arduinoSelecionado = arduinos.get(index);
		//cenario.setId_arduino(arduinoSelecionado.getId());
		setDispositivos(this.arduinos.get(index));
		setLampadas(lampadas);
	}

	public void setNomeCenario(String nomeCenario)
	{
		cenarioPadrao.setNome_cenario(nomeCenario);
		
	}

	public void setTemperatura(int temp)
	{
		cenarioPadrao.setValor_temperatura(temp);
		
	}

	public void setIluminacao(int ilum)
	{
		cenarioPadrao.setValor_iluminacao(ilum);
		
	}

	public void setArCond(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.AR_CONDICIONADO)
			{
				//a.setStatus(s);
			}
		}
		
	}

	public void setVentilador(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.VENTILADOR)
			{
				//a.setStatus(s);
			}
		}
	}

	public void setAquecedor(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.AQUECEDOR)
			{
				//a.setStatus(s);
			}
		}
		
	}

	public void setLampada(int index, Status s, int value)
	{
		
		//lampadas.get(index).setStatus(s);
		if(lampadas.get(index).getCod() == CodAtuador.LAMPADA_DIMERIZAVEL)
		{
			habilitaDesabilitaCampo(janela.lampSli, true);
			habilitaDesabilitaCampo(janela.lampPorcent, true);
		}
		else
		{
			habilitaDesabilitaCampo(janela.lampSli, false);
			habilitaDesabilitaCampo(janela.lampPorcent, false);
		}
		
	}

	public void setJanela(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.JANELA)
			{
				//a.setStatus(s);
			}
		}
		
	}
	
	public void setPerciana(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.PERCIANA)
			{
				//a.setStatus(s);
			}
		}
		
	}

	public void isPrivado(boolean isPrivado)
	{
		cenarioPadrao.setPrivado(isPrivado);
	}

	public void salvar()
	{
		logger.debug("Update Monitoracao");
		atuailza = false;
		cenarioPadrao.setId_arduino(arduinoSelecionado.getId());
		cenarioPadrao.setValor_iluminacao(janela.ilum.getValue());
		cenarioPadrao.setValor_temperatura(janela.temp.getValue());
		cenarioPadrao.setAtuadores(arduinoSelecionado.getAtuadores());
		listener.acaoMonitoracaoCenarioSalvar(cenarioPadrao);
	}

	public void cancelar()
	{
		listener.acaoMonitoracaoCancelar();
		atuailza = false;
	}
	
	public class AtualizaJanelaMonitoracao implements Runnable
	{
		
		private int tempoEspera;
		private Timer time;
		
		public AtualizaJanelaMonitoracao()
		{
			tempoEspera = 10000;
		}
		
		public void run()
		{
				 ActionListener taskPerformer = new ActionListener()
				 {
			        public void actionPerformed(ActionEvent evt)
			        {
			        	try 
			        	{
			        		atualiza();
						} 
			        	catch (DbException e)
			        	{
							e.printStackTrace();
						}
			        }
				 };
				 time = new Timer(tempoEspera, taskPerformer);
				 time.start();	
		}
		
		//public void setClasseGui(ControleGuiMonitoracao gui)
	   //	{
			
		//}
	
	}
}



