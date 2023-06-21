package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import DTO.UsuarioDTO;
import DAO.UsuarioDAO;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeUsuario;
	private JPasswordField txtSenhaUsuario;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(179, 11, 66, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 95, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 140, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(78, 94, 252, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String usuario = txtNomeUsuario.getText();
					String senha = txtSenhaUsuario.getText();
					UsuarioDTO userDto = new UsuarioDTO(usuario, senha);
					UsuarioDAO userDao = new UsuarioDAO();
					
					ResultSet result = userDao.autenticarUsuario(userDto);
					
					if(result.next()) {
						setVisible(false);
						dispose();
						TelaFuncionarios funcionarioView = new TelaFuncionarios();
						funcionarioView.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Usuario ou senha inválidos.");
					}
					
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Class View Login: " + erro);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(163, 190, 89, 23);
		contentPane.add(btnLogin);
		
		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setBounds(78, 139, 252, 20);
		contentPane.add(txtSenhaUsuario);
	}
}
