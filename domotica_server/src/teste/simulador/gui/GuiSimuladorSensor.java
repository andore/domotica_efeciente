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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import common.Status;

public class GuiSimuladorSensor extends JPanel {

	protected JComboBox comboBox;

	private int posXCampSen = 180;
	private int posXCampAtu = 480;
	
	private int posYCampIni = 141;
	private int posYDist = 40;
	
	private int posXLabSen = 0;
	private int posXLabAtu = 100;
	
	private int posYLabIni = 141;
	
	private int contLinhaSen = 0 ;
	private int contLinhaAtu = 0 ;
	
	public ListenerGuiSimuladorSensor listener;
	
	List<JLabel> descricaoSensores = new ArrayList();
	List<JTextField> listaField = new ArrayList();
	
	
	List<JLabel> descricaoAtuadores = new ArrayList();
	List<JComboBox> listaStatus = new ArrayList();
	
	
	public void setSensor(String descricao, String valor)
	{
		JLabel d = new JLabel();
		d.setText(descricao);
		d.setHorizontalAlignment(SwingConstants.LEADING);
		d.setFont(new Font("Segoe UI", Font.BOLD, 20));
		d.setBounds(posXLabSen, posYLabIni + posYDist * contLinhaSen, 360,30);
		descricaoSensores.add(d);
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
		v.setBounds(posXCampSen, posYCampIni + posYDist * contLinhaSen, 78, 30);
		add(v);
		contLinhaSen ++;
	}
	
	public void setAtuador(String descricao, int status)
	{
		JLabel d = new JLabel();
		d.setText(descricao);
		d.setHorizontalAlignment(SwingConstants.RIGHT);
		d.setFont(new Font("Segoe UI", Font.BOLD, 20));
		d.setBounds(posXLabAtu, posYLabIni + posYDist * contLinhaAtu, 360,30);
		descricaoAtuadores.add(d);
		add(d);
		
		final JComboBox v = new JComboBox();
		listaStatus.add(v);
		v.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0)
			{
			
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setStatus(listaStatus.indexOf(v),(Status)v.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0)
			{
			
			}
		});
		v.setModel(new DefaultComboBoxModel<Object>(Status.values()));
		v.setSelectedIndex(status);
		v.setFont(new Font("Calibri", Font.BOLD, 13));
		v.setBounds(posXCampAtu, posYCampIni + posYDist * contLinhaAtu, 78, 30);
		add(v);
		contLinhaAtu ++;
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
		for(JLabel j: descricaoSensores)
		{
			remove(j);
		}
		
		for(JTextField j: listaField)
		{
			remove(j);
		}
		
		for(JLabel j: descricaoAtuadores)
		{
			remove(j);
		}
		
		for(JComboBox j: listaStatus)
		{
			remove(j);
		}
		
		repaint();
		contLinhaSen = 0;
		contLinhaAtu = 0;
	}
}
