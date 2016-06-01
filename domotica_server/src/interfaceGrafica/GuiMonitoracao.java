package interfaceGrafica;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import common.Status;
import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class GuiMonitoracao extends JPanel
{
	protected JTextField textField_1;
	protected JTextField textField_2;
	protected JTextField lampPorcent;
	public final JSlider lampSli;
	private ListenerGuiCadastraCenario listener;
	protected JComboBox arCond;
	protected JComboBox ventilador;
	protected JComboBox aquecedor;
	protected JComboBox lampIndex;
	protected JComboBox janela;
	protected JComboBox comodo;
	protected JButton btnSalvar;
	protected JButton btnSair;
	protected JSlider temp;
	protected JSlider ilum;
	protected JComboBox lampSta;
	private JLabel lblPerciana;
	protected JComboBox perciana;
	private JLabel lblTemperaturaAtual;
	private JLabel lblIluminaoAtual;
	private JTextField tempAtual;
	private JTextField iluAtual;

	/**
	 * Create the panel.
	 */
	public GuiMonitoracao(final ListenerGuiCadastraCenario listener)
	{
		this.listener = listener;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblMonitoracao = new JLabel("Monitora\u00E7\u00E3o");
		lblMonitoracao.setForeground(new Color(65, 105, 225));
		lblMonitoracao.setFont(new Font("Kalinga", Font.BOLD, 23));
		lblMonitoracao.setBounds(126, 29, 215, 37);
		add(lblMonitoracao);
		
		JLabel lblTemperaturaDesejada = new JLabel("Temperatura Desejada");
		lblTemperaturaDesejada.setHorizontalAlignment(SwingConstants.LEFT);
		lblTemperaturaDesejada.setForeground(Color.GRAY);
		lblTemperaturaDesejada.setFont(new Font("Calibri", Font.BOLD, 17));
		lblTemperaturaDesejada.setBounds(162, 242, 179, 23);
		add(lblTemperaturaDesejada);
		
		JLabel lblIluminaoDesejada = new JLabel("Ilumina\u00E7\u00E3o Desejada");
		lblIluminaoDesejada.setHorizontalAlignment(SwingConstants.LEFT);
		lblIluminaoDesejada.setForeground(Color.GRAY);
		lblIluminaoDesejada.setFont(new Font("Calibri", Font.BOLD, 17));
		lblIluminaoDesejada.setBounds(162, 276, 179, 23);
		add(lblIluminaoDesejada);
		
		JLabel lblC = new JLabel("\u00B0C");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setForeground(Color.GRAY);
		lblC.setFont(new Font("Calibri", Font.BOLD, 18));
		lblC.setBounds(566, 241, 18, 23);
		add(lblC);
		
		JLabel label_1 = new JLabel("%");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("Calibri", Font.BOLD, 18));
		label_1.setBounds(566, 275, 18, 23);
		add(label_1);
		
		JLabel lblControle = new JLabel("Controle");
		lblControle.setBackground(Color.WHITE);
		lblControle.setHorizontalAlignment(SwingConstants.LEFT);
		lblControle.setForeground(new Color(51, 102, 255));
		lblControle.setFont(new Font("Calibri", Font.BOLD, 24));
		lblControle.setBounds(126, 208, 132, 23);
		add(lblControle);
		
		JLabel lblArcondicionado = new JLabel("Ar-Condicionado");
		lblArcondicionado.setHorizontalAlignment(SwingConstants.LEFT);
		lblArcondicionado.setForeground(Color.GRAY);
		lblArcondicionado.setFont(new Font("Calibri", Font.BOLD, 17));
		lblArcondicionado.setBounds(162, 339, 116, 23);
		add(lblArcondicionado);
		
		arCond = new JComboBox();
		arCond.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setArCond((Status)arCond.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		arCond.setEnabled(false);
		arCond.setFont(new Font("Calibri", Font.BOLD, 13));
		arCond.setForeground(SystemColor.textHighlight);
		arCond.setModel(new DefaultComboBoxModel(Status.values()));
		arCond.setSelectedIndex(2);
		arCond.setBounds(349, 311, 77, 20);
		add(arCond);
		
		JLabel lblVentilador = new JLabel("Ventilador");
		lblVentilador.setHorizontalAlignment(SwingConstants.LEFT);
		lblVentilador.setForeground(Color.GRAY);
		lblVentilador.setFont(new Font("Calibri", Font.BOLD, 17));
		lblVentilador.setBounds(162, 310, 116, 23);
		add(lblVentilador);
		
		ventilador = new JComboBox();
		ventilador.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setVentilador((Status)ventilador.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		ventilador.setEnabled(false);
		ventilador.setFont(new Font("Calibri", Font.BOLD, 13));
		ventilador.setModel(new DefaultComboBoxModel(Status.values()));
		ventilador.setSelectedIndex(2);
		ventilador.setForeground(SystemColor.textHighlight);
		ventilador.setBounds(349, 340, 77, 20);
		add(ventilador);
		
		JLabel lblAquecedor = new JLabel("Aquecedor");
		lblAquecedor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAquecedor.setForeground(Color.GRAY);
		lblAquecedor.setFont(new Font("Calibri", Font.BOLD, 17));
		lblAquecedor.setBounds(162, 368, 116, 23);
		add(lblAquecedor);
		
		aquecedor = new JComboBox();
		aquecedor.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setAquecedor((Status)aquecedor.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		aquecedor.setEnabled(false);
		aquecedor.setFont(new Font("Calibri", Font.BOLD, 13));
		aquecedor.setModel(new DefaultComboBoxModel(Status.values()));
		aquecedor.setSelectedIndex(2);
		aquecedor.setForeground(SystemColor.textHighlight);
		aquecedor.setBounds(349, 369, 77, 20);
		add(aquecedor);
		
		JLabel lblLmpada = new JLabel("L\u00E2mpada");
		lblLmpada.setHorizontalAlignment(SwingConstants.LEFT);
		lblLmpada.setForeground(Color.GRAY);
		lblLmpada.setFont(new Font("Calibri", Font.BOLD, 17));
		lblLmpada.setBounds(162, 397, 69, 23);
		add(lblLmpada);
		
		lampIndex = new JComboBox();
		lampIndex.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setLampada(lampIndex.getSelectedIndex(), (Status)lampSta.getSelectedItem(), lampSli.getValue());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		lampIndex.setEnabled(false);
		lampIndex.setFont(new Font("Calibri", Font.BOLD, 13));
		lampIndex.setModel(new DefaultComboBoxModel(new String[] {"Principal", "Abajur", "Escrivaninha"}));
		lampIndex.setForeground(SystemColor.textHighlight);
		lampIndex.setBounds(241, 398, 99, 20);
		add(lampIndex);
		
		JLabel label_2 = new JLabel("%");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("Calibri", Font.BOLD, 18));
		label_2.setBounds(566, 397, 18, 23);
		add(label_2);
		
		janela = new JComboBox();
		janela.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setJanela((Status)janela.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		janela.setEnabled(false);
		janela.setModel(new DefaultComboBoxModel(Status.values()));
		janela.setSelectedIndex(2);
		janela.setForeground(SystemColor.textHighlight);
		janela.setFont(new Font("Calibri", Font.BOLD, 13));
		janela.setBounds(349, 427, 77, 20);
		add(janela);
		
		JLabel label_3 = new JLabel("Janela");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("Calibri", Font.BOLD, 17));
		label_3.setBounds(162, 426, 116, 23);
		add(label_3);
		
		JLabel lblCmodo = new JLabel("C\u00F4modo");
		lblCmodo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCmodo.setForeground(Color.GRAY);
		lblCmodo.setFont(new Font("Calibri", Font.BOLD, 17));
		lblCmodo.setBounds(162, 77, 108, 23);
		add(lblCmodo);
		
		comodo = new JComboBox();
		comodo.setEnabled(false);
		comodo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) 
			{
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setComodo(comodo.getSelectedIndex());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) 
			{
			}
		});
		comodo.setToolTipText("");
		comodo.setForeground(SystemColor.textHighlight);
		comodo.setFont(new Font("Calibri", Font.BOLD, 13));
		comodo.setBounds(244, 77, 97, 20);
		add(comodo);
		
		btnSalvar = new JButton("SALVAR");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				if(btnSalvar.isEnabled())
				{
					listener.salvar();
				}
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setForeground(SystemColor.windowBorder);
		btnSalvar.setFont(new Font("Calibri", Font.BOLD, 12));
		btnSalvar.setBackground(new Color(169, 169, 169));
		btnSalvar.setBounds(309, 512, 89, 23);
		add(btnSalvar);
		
		btnSalvar.setEnabled(false);
		
		btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSair.setForeground(SystemColor.windowBorder);
		btnSair.setFont(new Font("Calibri", Font.BOLD, 12));
		btnSair.setBackground(new Color(169, 169, 169));
		btnSair.setBounds(416, 512, 89, 23);
		add(btnSair);
		
		temp = new JSlider();
		temp.setMaximum(32);
		temp.setEnabled(false);
		temp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				textField_1.setText(String.valueOf(temp.getValue()));
			}
		});
		temp.setValue(21);
		temp.setBackground(SystemColor.textHighlightText);
		temp.setBounds(338, 242, 179, 23);
		add(temp);
		
		ilum = new JSlider();
		ilum.setEnabled(false);
		ilum.setValue(70);
		ilum.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				textField_2.setText(String.valueOf(ilum.getValue()));
				if(lampPorcent.isEnabled())lampPorcent.setText(String.valueOf(ilum.getValue()));
				if(lampSli.isEnabled())lampSli.setValue(ilum.getValue());
			}
		});
		ilum.setBackground(Color.WHITE);
		ilum.setBounds(338, 276, 179, 23);
		add(ilum);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(String.valueOf(temp.getValue()));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(SystemColor.textHighlight);
		textField_1.setFont(new Font("Calibri", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(527, 241, 32, 23);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText(String.valueOf(ilum.getValue()));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(SystemColor.textHighlight);
		textField_2.setFont(new Font("Calibri", Font.BOLD, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(527, 275, 32, 23);
		add(textField_2);
		
		lampSli = new JSlider();
		lampSli.setEnabled(false);
		lampSli.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(lampSli.isEnabled())
				{
					lampPorcent.setText(String.valueOf(lampSli.getValue()));
					listener.setLampada(lampIndex.getSelectedIndex(), (Status)lampSta.getSelectedItem(), lampSli.getValue());
				}
			}
		});
		if(lampSli.isEnabled())
			lampSli.setValue(ilum.getValue());
		else
			lampSli.setValue(0);
		lampSli.setBackground(Color.WHITE);
		lampSli.setBounds(436, 397, 94, 23);
		add(lampSli);
		
		lampPorcent = new JTextField();
		lampPorcent.setEditable(false);
		lampPorcent.setText(String.valueOf(lampSli.getValue()));
		lampPorcent.setHorizontalAlignment(SwingConstants.CENTER);
		lampPorcent.setForeground(SystemColor.textHighlight);
		lampPorcent.setFont(new Font("Calibri", Font.BOLD, 16));
		lampPorcent.setColumns(10);
		lampPorcent.setBounds(532, 397, 32, 23);
		add(lampPorcent);
		
		lampSta = new JComboBox();
		lampSta.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) 
			{
				listener.setLampada(lampIndex.getSelectedIndex(), (Status)lampSta.getSelectedItem(), lampSli.getValue());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		lampSta.setEnabled(false);
		lampSta.setModel(new DefaultComboBoxModel(Status.values()));
		lampSta.setSelectedIndex(2);
		lampSta.setForeground(SystemColor.textHighlight);
		lampSta.setFont(new Font("Calibri", Font.BOLD, 13));
		lampSta.setBounds(349, 398, 77, 20);
		add(lampSta);
		
		lblPerciana = new JLabel("Perciana");
		lblPerciana.setHorizontalAlignment(SwingConstants.LEFT);
		lblPerciana.setForeground(Color.GRAY);
		lblPerciana.setFont(new Font("Calibri", Font.BOLD, 17));
		lblPerciana.setBounds(162, 453, 116, 23);
		add(lblPerciana);
		
		perciana = new JComboBox();
		perciana.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0)
			{
				listener.setPerciana((Status)perciana.getSelectedItem());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		perciana.setEnabled(false);
		perciana.setModel(new DefaultComboBoxModel(Status.values()));
		perciana.setSelectedIndex(2);
		perciana.setForeground(SystemColor.textHighlight);
		perciana.setFont(new Font("Calibri", Font.BOLD, 13));
		perciana.setBounds(349, 454, 77, 20);
		add(perciana);
		
		lblTemperaturaAtual = new JLabel("Temperatura Atual");
		lblTemperaturaAtual.setHorizontalAlignment(SwingConstants.LEFT);
		lblTemperaturaAtual.setForeground(Color.GRAY);
		lblTemperaturaAtual.setFont(new Font("Calibri", Font.BOLD, 17));
		lblTemperaturaAtual.setBounds(162, 119, 137, 23);
		add(lblTemperaturaAtual);
		
		lblIluminaoAtual = new JLabel("Ilumina\u00E7\u00E3o Atual");
		lblIluminaoAtual.setHorizontalAlignment(SwingConstants.LEFT);
		lblIluminaoAtual.setForeground(Color.GRAY);
		lblIluminaoAtual.setFont(new Font("Calibri", Font.BOLD, 17));
		lblIluminaoAtual.setBounds(162, 153, 137, 23);
		add(lblIluminaoAtual);
		
		tempAtual = new JTextField();
		tempAtual.setText("21");
		tempAtual.setHorizontalAlignment(SwingConstants.CENTER);
		tempAtual.setForeground(SystemColor.textHighlight);
		tempAtual.setFont(new Font("Calibri", Font.BOLD, 16));
		tempAtual.setEditable(false);
		tempAtual.setColumns(10);
		tempAtual.setBounds(309, 120, 32, 23);
		add(tempAtual);
		
		iluAtual = new JTextField();
		iluAtual.setText("70");
		iluAtual.setHorizontalAlignment(SwingConstants.CENTER);
		iluAtual.setForeground(SystemColor.textHighlight);
		iluAtual.setFont(new Font("Calibri", Font.BOLD, 16));
		iluAtual.setEditable(false);
		iluAtual.setColumns(10);
		iluAtual.setBounds(309, 153, 32, 23);
		add(iluAtual);
		
		JLabel label = new JLabel("\u00B0C");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		label.setBounds(349, 119, 18, 23);
		add(label);
		
		JLabel label_4 = new JLabel("%");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.GRAY);
		label_4.setFont(new Font("Calibri", Font.BOLD, 18));
		label_4.setBounds(349, 153, 18, 23);
		add(label_4);

	}
}
