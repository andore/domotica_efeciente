package interfaceGrafica;

import javax.swing.JPanel;
import org.apache.log4j.Logger;

public abstract class AbstractControleGui
{
	final static Logger logger = Logger.getLogger(AbstractControleGui.class);
	
	public AbstractControleGui()
	{
		
	}

	public abstract JPanel getJanela();
}
