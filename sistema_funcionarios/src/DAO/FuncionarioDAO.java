package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.Funcionario;

public class FuncionarioDAO {

	
	PreparedStatement statement;
	ResultSet result;
	
	public void CadastrarFuncionario(Funcionario func) {
		Connection conexao;
		conexao = new Conexao().ConexaoBD();
		String sql = "INSERT INTO funcionarios(nome, cpf, salario, setor) values(?, ?, ?, ?)";
		try {
			statement = conexao.prepareStatement(sql);
			statement.setString(1, func.getNome());
			statement.setString(2, func.getCpf());
			statement.setDouble(3, func.getSalario());
			statement.setString(4, func.getSetor());
			
			statement.execute();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Class ModelFuncionarios: " + erro);
		}
	}
	
	public ArrayList<Funcionario> PesquisaFuncionario() {
		ArrayList<Funcionario> dadosFuncionario = new ArrayList<>();
		Connection conexao;
		conexao = new Conexao().ConexaoBD();
		try {
			String sql = "SELECT * from funcionarios";
			statement = conexao.prepareStatement(sql);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				int id = result.getInt("id_funcionario");
				String nome = result.getString("nome");
				String cpf = result.getString("cpf");
				double salario = result.getDouble("salario");
				String setor = result.getString("setor");
				Funcionario funcionario = new Funcionario(nome, cpf, salario, setor);
				funcionario.setId(id);
				
				dadosFuncionario.add(funcionario);
			}
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null,"Class ModelFuncionario function Pesquisa: " + erro);
		}
		return dadosFuncionario;
	}
	
	public void AlterarFuncionario(Funcionario func) {
		Connection conexao;
		
		conexao = new Conexao().ConexaoBD();
		String sql = "update funcionarios set nome = ?, cpf = ?, salario = ?, setor = ? where id_funcionario = ?";
		
		try {
			statement = conexao.prepareStatement(sql);
			statement.setString(1, func.getNome());
			statement.setString(2, func.getCpf());
			statement.setDouble(3, func.getSalario());
			statement.setString(4, func.getSetor());
			statement.setInt(5, func.getId());
			statement.executeUpdate();
		} catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Class FuncionarioDAO, metodo Altera: " + erro);
		}
	}
	
	public void DeletarFuncionario(Funcionario func) {
		Connection conexao = null;
		PreparedStatement statement = null;
		try {
		
			conexao = new Conexao().ConexaoBD();
			
			String sql = "delete from funcionarios where id_funcionario = " + func.getId();
  			statement = conexao.prepareStatement(sql);
			
			statement.executeUpdate(sql);
		} catch(SQLException erro) {
			JOptionPane.showMessageDialog(null,"Classe FuncionarioDAO, metodo excluir: " + erro);
		}
	}
}






























