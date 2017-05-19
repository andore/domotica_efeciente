package interfaceGrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.apache.log4j.Logger;
import common.Status;
import common.CodAtuador;
import dao.Arduino;
import dao.Atuador;
import dao.Cenario;

public class ControleGuiCadastraCenario extends AbstractControleGui implements ListenerGuiCadastraCenario
{
	private GuiCadastraCenario janela;
	private Cenario cenario;
	private List <Arduino> arduinos;
	private List <Atuador> lampadas;
	public Arduino arduinoSelecionado;
	private ListenerCtrlCadastraCenario listener;
	final static Logger logger = Logger.getLogger(ControleGuiCadastraCenario.class);
	
	public ControleGuiCadastraCenario(ListenerCtrlCadastraCenario listener)
	{
		this.listener = listener;
		janela = new GuiCadastraCenario(this);
		setCompDisable();
		cenario = new Cenario();
		lampadas = new ArrayList<Atuador>();
	}
	
	/*
	public void carregaCenario(Cenario cen)
	{
		janela.aquecedor.setEnabled(b);
		janela.arCond.setEnabled(b);
		janela.ilum.setEnabled(enabled);
		janela.janela.setEnabled(b);
		janela.lampIndex.setEditable(aFlag);
		janela.lampPorcent.setEnabled(enabled);
		janela.lampSli.setEnabled(enabled);
		janela.lampSta.setEnabled(b);
		janela.perciana.setEnabled(b);
		janela.temp.setEnabled(enabled);
		janela.ventilador.setEnabled(b);
		
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setDados(List <Arduino> arduinos)
	{
		this.arduinos = arduinos;
		
		if(arduinos!=null && arduinos.size()>0)
		{
			janela.comodo.setEnabled(true);
			janela.comodo.setModel(new DefaultComboBoxModel(getDescriArduino(arduinos)));
			setDispositivos(this.arduinos.get(0));
			setLampadas(this.lampadas);
			janela.usuario.setEnabled(true);
			janela.temp.setEnabled(true);
			janela.ilum.setEnabled(true);
			janela.nomeCenario.setEnabled(true);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setDispositivos(Arduino arduino)
	{
		this.arduinoSelecionado = arduino;
		lampadas.clear();
		janela.lampIndex.setModel(new DefaultComboBoxModel(new String []{""}));
		janela.lampIndex.setEnabled(false);
		janela.arCond.setEnabled(false);
		janela.ventilador.setEnabled(false);
		janela.aquecedor.setEnabled(false);
		janela.lampSta.setEnabled(false);
		janela.janela.setEnabled(false);
		janela.lampSli.setEnabled(false);
		
		if(arduino!=null)
		{
			List<Atuador> atuadores = arduino.getAtuadores();
			for(Atuador atuador: atuadores)
			{
				if(atuador.getStatus()==0)
				{
					atuador.setStatus(Status.AUTO_);
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
						janela.perciana.setEnabled(true);
						break;
					case CodAtuador.JANELA:
						janela.janela.setEnabled(true);
						break;
					case CodAtuador.VENTILADOR:
						janela.ventilador.setEnabled(true);
						break;
					case CodAtuador.AR_CONDICIONADO:
						janela.arCond.setEnabled(true);
						break;
					case CodAtuador.AQUECEDOR:
						janela.aquecedor.setEnabled(true);
						break;
						
					default:
						break;
						
				}
			}
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setLampadas(List <Atuador> lampadas)
	{
		if(lampadas!=null && lampadas.size()>0)
		{
			janela.lampIndex.setModel(new DefaultComboBoxModel(getDescricaoLampadas(lampadas)));
			janela.lampIndex.setEnabled(true);
			janela.lampSta.setEnabled(true);
			if(lampadas.get(0).getCod() == CodAtuador.LAMPADA_DIMERIZAVEL)
			{
				janela.lampSli.setEnabled(true);
				janela.lampPorcent.setEnabled(true);
			}
			else
			{
				janela.lampSli.setEnabled(false);
				janela.lampPorcent.setEnabled(false);
			}
		}
		else
		{
			janela.lampIndex.setEnabled(false);
			janela.lampSta.setEnabled(false);
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
		janela.nomeCenario.setEnabled(false);
		janela.temp.setEnabled(false);
		janela.ilum.setEnabled(false);
		janela.usuario.setEnabled(false);
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
		cenario.setNome_cenario(nomeCenario);
		
	}

	public void setTemperatura(int temp)
	{
		cenario.setValor_temperatura(temp);
		
	}

	public void setIluminacao(int ilum)
	{
		cenario.setValor_iluminacao(ilum);
		
	}

	public void setArCond(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.AR_CONDICIONADO)
			{
				a.setStatus(s.ordinal());
			}
		}
		
	}

	public void setVentilador(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.VENTILADOR)
			{
				a.setStatus(s.ordinal());
			}
		}
	}

	public void setAquecedor(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.AQUECEDOR)
			{
				a.setStatus(s.ordinal());
			}
		}
		
	}

	public void setLampada(int index, Status s, int value)
	{
		
		lampadas.get(index).setStatus(s.ordinal());
		if(lampadas.get(index).getCod() == CodAtuador.LAMPADA_DIMERIZAVEL)
		{
			janela.lampSli.setEnabled(true);
			janela.lampPorcent.setEnabled(true);
		}
		else
		{
			janela.lampSli.setEnabled(false);
			janela.lampPorcent.setEnabled(false);
		}
		
	}

	public void setJanela(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.JANELA)
			{
				a.setStatus(s.ordinal());
			}
		}
		
	}
	
	public void setPerciana(Status s)
	{
		for(Atuador a: arduinoSelecionado.getAtuadores())
		{
			if(a.getCod()==CodAtuador.PERCIANA)
			{
				a.setStatus(s.ordinal());
			}
		}
		
	}

	public void isPrivado(boolean isPrivado)
	{
		cenario.setPrivado(isPrivado);
	}

	public void salvar()
	{
		logger.debug("Salvando cenário.");
		cenario.setNome_cenario(janela.nomeCenario.getText());
		cenario.setId_arduino(arduinoSelecionado.getId());
		cenario.setValor_iluminacao(janela.ilum.getValue());
		cenario.setValor_temperatura(janela.temp.getValue());
		cenario.setPrivado(janela.usuario.getSelectedIndex()==1);
		cenario.setAtuadores(arduinoSelecionado.getAtuadores());
		listener.acaoCadastraCenarioSalvar(cenario);
	}

	public void cancelar()
	{
		listener.acaoCadastraCenarioCancelar();
	}

	public GuiCadastraCenario getJanela()
	{
		return janela;
	}
		
}
