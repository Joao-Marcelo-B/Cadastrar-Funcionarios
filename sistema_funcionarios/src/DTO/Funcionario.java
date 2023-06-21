package DTO;

public class Funcionario {
	private int id;
	private String nome;
	private String cpf;
	private double salario;
	private String setor;
	
	public Funcionario(String nome, String cpf, double salario, String setor) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.setor = setor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}	
}
