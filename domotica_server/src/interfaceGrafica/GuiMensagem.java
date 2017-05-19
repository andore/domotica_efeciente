package interfaceGrafica;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


public class GuiMensagem extends JPanel{
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	 Timer time;
	
	ListenerGuiMensagem listener;
	final static Logger logger = Logger.getLogger(GuiMensagem.class);
	private int tempoEspera;
	public GuiMensagem(final ListenerGuiMensagem listener, final String texto, int tempoEspera) {
		this.listener = listener;
		this.setTempoEspera(tempoEspera);
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel mensagem = new JLabel(texto);
		mensagem.setFont(new Font("Tahoma", Font.PLAIN, 31));
		mensagem.setForeground(new Color(0, 153, 204));
		mensagem.setHorizontalAlignment(SwingConstants.CENTER);
		mensagem.setBounds(0, 0, 800, 600);
		add(mensagem);
		
		 ActionListener taskPerformer = new ActionListener()
		 {
	        public void actionPerformed(ActionEvent evt)
	        {
	          timeOut();
	        }
		 };
		 time = new Timer(tempoEspera, taskPerformer);
		 time.start();
		 
	}
	
	public void timeOut()
	{	
		listener.timeOut();
		time.stop();
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
}
