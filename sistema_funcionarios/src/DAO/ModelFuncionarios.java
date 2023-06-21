package DAO;

import DTO.Funcionario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ModelFuncionarios extends AbstractTableModel{
	private String[] colunas = {"ID", "Nome", "Cpf", "Salario", "Setor"};
	ArrayList<Funcionario> dados = new ArrayList<>();
	
	public Funcionario returnFuncionario(int index) {
		return dados.get(index);
	}
	
	public void atualizar() {
		FuncionarioDAO fun = new FuncionarioDAO();
		dados = fun.PesquisaFuncionario();
		this.fireTableDataChanged();
	}
	
	public void alterarFuncionario(int index, Funcionario f) {
		dados.set(index, f);
		this.fireTableDataChanged();
	}
	
	public void removerFuncionario(int index) {
		dados.remove(index);
		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
			case 0:
				return dados.get(linha).getId();
			case 1:
				return dados.get(linha).getNome();
			case 2:
				return dados.get(linha).getCpf();
			case 3:
				return dados.get(linha).getSalario();
			case 4:
				return dados.get(linha).getSetor();
		}
		return null;
	}
	
	public void addRow() {
		FuncionarioDAO fun = new FuncionarioDAO();
		dados = fun.PesquisaFuncionario();
		this.fireTableDataChanged();
	}
}
