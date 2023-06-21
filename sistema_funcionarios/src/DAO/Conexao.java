package DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	public Connection ConexaoBD() {
		Connection conexao = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			String url = "jdbc:mysql://localhost:3306/sistema_funcionarios?user=root&password=";
			//Class.forName(driver);
			conexao = DriverManager.getConnection(url);
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null,"Class conexaoDAO" + erro);
		}
		
		return conexao;
	}
}

