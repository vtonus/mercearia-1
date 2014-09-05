package br.com.mercearia.modelo;


import java.util.Calendar;

public class Cliente {
	private int id;
	private String cpf;
	private String nome;
	private String email;
	private long telefone;
	private String sexo;
	private String endereco;
	private Calendar dataNascimento;
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public String getSexoC() {
		if (sexo.equals("m"))
		{
			return "masculino";
		}
		if (sexo.equals("f"))
		{
			return "feminino";
		}
		else
		{
			return "";
		}
		
	}
	
	public void setSexo(String sexo) 
	{
		this.sexo = sexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSexoC(String parameter) {
		parameter = parameter..toLowerCase();
		if (parameter.equals("masculino"))
		{
			this.setSexo("m");
		}
		else if (parameter.equals("feminino"))
		{
			this.setSexo("f");
		}
		else
		{
			this.setSexo("");
		}
		
	}
}
