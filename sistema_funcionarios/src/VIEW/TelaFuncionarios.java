package VIEW;
import java.awt.BorderLayout;
import DAO.FuncionarioDAO;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.ModelFuncionarios;
import DTO.Funcionario;
import javax.swing.ImageIcon;
public class TelaFuncionarios extends JFrame {
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtSalario;
	private JTextField txtSetor;
	private JTable table;
	
	ModelFuncionarios model = new ModelFuncionarios();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionarios frame = new TelaFuncionarios();
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
	public void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtSalario.setText("");
		txtSetor.setText("");
	}
	
	public boolean validarCampos(String nome, String cpf, double salario, String setor) {
		if(nome.trim().isEmpty() || cpf.trim().isEmpty() || setor.trim().isEmpty() || Double.valueOf(salario).isNaN()) {
			JOptionPane.showMessageDialog(this, "Campos não preenchidos");
			return false;
		}
		return true;
	}
	
	public Funcionario gerarFuncionario() {
		String nome = txtNome.getText();
		String cpf = txtCpf.getText();
		double salario = Double.parseDouble(txtSalario.getText());
		String setor = txtSetor.getText();
		Funcionario f = new Funcionario(nome, cpf, salario, setor);
		return f;
	}
	
	public TelaFuncionarios() {
		
		model.addRow();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Funcionarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(243, 11, 136, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 88, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 113, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Salario");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(10, 138, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Setor");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 163, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(52, 86, 202, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(52, 111, 202, 20);
		contentPane.add(txtCpf);
		
		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(52, 138, 202, 20);
		contentPane.add(txtSalario);
		
		txtSetor = new JTextField();
		txtSetor.setColumns(10);
		txtSetor.setBounds(52, 163, 202, 20);
		contentPane.add(txtSetor);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario f = gerarFuncionario();
				FuncionarioDAO funcionario = new FuncionarioDAO();
				if(validarCampos(f.getNome(), f.getCpf(), f.getSalario(), f.getSetor())) {
					funcionario.CadastrarFuncionario(f);
					model.atualizar();
					limparCampos();
				}
			}
		});
		
		btnNewButton.setBounds(79, 194, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioDAO funcionario = new FuncionarioDAO();
				int index = table.getSelectedRow();
				int id = (int)table.getValueAt(index, 0);
				Funcionario f = gerarFuncionario();
				f.setId(id);
				boolean validar = validarCampos(f.getNome(), f.getCpf(), f.getSalario(), f.getSetor());
					if(validar) {
						if(JOptionPane.showConfirmDialog(contentPane, "Você desejar alterar as informações do funcionario?", "Alterar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							funcionario.AlterarFuncionario(f);
							model.atualizar();
							limparCampos();
						}		
					}
				}
		});
		btnNewButton_1.setBounds(212, 194, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioDAO funcionario = new FuncionarioDAO();
				int index = table.getSelectedRow();
				int id = (int)table.getValueAt(index, 0);
				Funcionario f = gerarFuncionario();
				f.setId(id);
				
					if(JOptionPane.showConfirmDialog(contentPane,"Você realmente deseja remover o funcionario?" ,"Remover", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						funcionario.DeletarFuncionario(f);
						model.atualizar();
						limparCampos();
					}	
				 else {
					JOptionPane.showMessageDialog(contentPane ,"Operação Inválida");
				}
			}
		});
		btnNewButton_2.setBounds(330, 194, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 244, 524, 183);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				Funcionario f = model.returnFuncionario(index);
				
				txtNome.setText(f.getNome());
				txtCpf.setText(f.getCpf());
				txtSalario.setText(Double.toString(f.getSalario()));
				txtSetor.setText(f.getSetor());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\GitHub\\Cadastrar-Funcionarios\\sistema_funcionarios\\icon20_20.png"));
		btnNewButton_3.setBounds(264, 163, 25, 25);
		contentPane.add(btnNewButton_3);
	}
}
