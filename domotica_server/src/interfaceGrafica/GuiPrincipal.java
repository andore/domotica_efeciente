package interfaceGrafica;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiPrincipal extends JPanel{
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	ListenerGuiPrincipal listener;
		
	public GuiPrincipal(final ListenerGuiPrincipal listener) {
		this.listener = listener;
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		JButton btnCenarios = new JButton("CEN\u00C1RIOS");
		btnCenarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoBotaoCenario();
			}
		});
		btnCenarios.setBackground(new Color(169, 169, 169));
		btnCenarios.setFont(new Font("Calibri", Font.BOLD, 12));
		btnCenarios.setForeground(new Color(65, 105, 225));
		btnCenarios.setBounds(292, 280, 195, 42);
		add(btnCenarios);
		
		JLabel lblHouseKe = new JLabel("House Controller");
		lblHouseKe.setForeground(new Color(65, 105, 225));
		lblHouseKe.setFont(new Font("Kalinga", Font.BOLD, 23));
		lblHouseKe.setBounds(292, 116, 195, 37);
		add(lblHouseKe);
		
		JButton btnMonitoramento = new JButton("MONITORAMENTO");
		btnMonitoramento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				acaoBotaoMonitoramento();
			}
		});
		btnMonitoramento.setForeground(new Color(65, 105, 225));
		btnMonitoramento.setFont(new Font("Calibri", Font.BOLD, 12));
		btnMonitoramento.setBackground(new Color(169, 169, 169));
		btnMonitoramento.setBounds(292, 227, 195, 42);
		add(btnMonitoramento);
		
		JButton btnUsuarios = new JButton("USU\u00C1RIOS");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				acaoBotaoUsuario();
			}
		});
		btnUsuarios.setForeground(new Color(65, 105, 225));
		btnUsuarios.setFont(new Font("Calibri", Font.BOLD, 12));
		btnUsuarios.setBackground(new Color(169, 169, 169));
		btnUsuarios.setBounds(292, 333, 195, 42);
		add(btnUsuarios);
	}
	
	private void acaoBotaoMonitoramento()
	{
		listener.acaoMonitoramento();
	}
	
	private void acaoBotaoUsuario()
	{
		listener.acaoUsuario();
	}
	
	private void acaoBotaoCenario()
	{
		listener.acaoCadastraCenario();
	}
}
