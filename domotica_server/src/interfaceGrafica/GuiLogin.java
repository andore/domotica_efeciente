package interfaceGrafica;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

public class GuiLogin extends JPanel {
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	
	
	public GuiLogin() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBackground(new Color(169, 169, 169));
		btnEntrar.setFont(new Font("Calibri", Font.BOLD, 12));
		btnEntrar.setForeground(new Color(65, 105, 225));
		btnEntrar.setBounds(290, 175, 89, 23);
		add(btnEntrar);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.BOLD, 12));
		textField_1.setForeground(new Color(105, 105, 105));
		textField_1.setColumns(10);
		textField_1.setBounds(122, 78, 257, 23);
		add(textField_1);
		
		JLabel lblUsuario = new JLabel("USU\u00C1RIO");
		lblUsuario.setForeground(new Color(128, 128, 128));
		lblUsuario.setFont(new Font("Calibri", Font.BOLD, 17));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBounds(48, 78, 72, 23);
		add(lblUsuario);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(new Color(128, 128, 128));
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Calibri", Font.BOLD, 17));
		lblSenha.setBounds(48, 126, 72, 23);
		add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(105, 105, 105));
		passwordField.setBounds(122, 123, 100, 23);
		add(passwordField);
		
		JLabel lblHouseKe = new JLabel("House Controller");
		lblHouseKe.setForeground(new Color(65, 105, 225));
		lblHouseKe.setFont(new Font("Kalinga", Font.BOLD, 23));
		lblHouseKe.setBounds(48, 11, 195, 37);
		add(lblHouseKe);

	}
}
