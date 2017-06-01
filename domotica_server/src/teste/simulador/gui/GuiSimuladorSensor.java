package teste.simulador.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class GuiSimuladorSensor extends JPanel {

	protected JComboBox comboBox;

	private int posXCamp = 480;
	private int posYCampIni = 141;
	private int posYDist = 40;
	
	private int posXLab = 100;
	private int posYLabIni = 141;
	
	private int contLinha = 0 ;
	
	public ListenerGuiSimuladorSensor listener;
	
	List<JLabel> listaLabel = new ArrayList();
	List<JTextField> listaField = new ArrayList();
	
	public void setLinha(String descricao, String valor)
	{
		JLabel d = new JLabel();
		d.setText(descricao);
		d.setHorizontalAlignment(SwingConstants.RIGHT);
		d.setFont(new Font("Segoe UI", Font.BOLD, 20));
		d.setBounds(posXLab, posYLabIni + posYDist * contLinha, 360,30);
		listaLabel.add(d);
		add(d);
		
		final JTextField v = new JTextField();
		listaField.add(v);
		v.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.alteraValor(listaField.indexOf(v), v.getText());
			}
		});
		v.setText(valor);
		v.setHorizontalAlignment(SwingConstants.CENTER);
		v.setFont(new Font("Segoe UI", Font.BOLD, 20));
		v.setBounds(posXCamp, posYCampIni + posYDist * contLinha, 78, 30);
		
		
		
		
		
		
//		.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			public void keyReleased(KeyEvent e)
//			{
//				if(e.getKeyCode() == e.VK_ENTER)
//				{
//					listener.alteraValor(contLinha, listaField.get(contLinha).getText());
//				}
//			}
//		});
		
		
		add(v);
		
		contLinha ++;
	}
	
	public GuiSimuladorSensor(final ListenerGuiSimuladorSensor listener) {
		
		this.listener = listener;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ARDUINO");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(253, 38, 118, 44);
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 24));
		comboBox.setBounds(373, 49, 230, 26);
		
		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0)
			{
			
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.alteraArduino(comboBox.getSelectedIndex());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0)
			{
			
			}
		});

		add(comboBox);
	}
		
	public void reset()
	{
		for(JLabel j: listaLabel)
		{
			remove(j);
		}
		
		for(JTextField j: listaField)
		{
			remove(j);
		}
		
		repaint();
		contLinha = 0;
	}
}
