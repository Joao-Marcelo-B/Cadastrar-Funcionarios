package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DTO.UsuarioDTO;

public class UsuarioDAO {

	Connection conexao;
	
	public ResultSet autenticarUsuario(UsuarioDTO user) {
		
		conexao = new Conexao().ConexaoBD();
		try {
			String sql = "SELECT * FROM Usuarios where nome_usuario = ? and senha_usuario = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, user.getNomeUsuario());
			statement.setString(2, user.getSenhaUsuario());
			
			ResultSet result = statement.executeQuery();
			return result;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Class UsuarioDAO: " + erro);
			return null;
		}
	}
}
