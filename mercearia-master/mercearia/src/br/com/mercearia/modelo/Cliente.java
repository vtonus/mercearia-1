package br.com.mercearia.modelo;


import java.util.Calendar;

public class Cliente {
	private int id;
	private String doc;
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
	
	public String getDoc() {
		return doc;
	}
	public void setDoc(String cpf) {
		this.doc = cpf;
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
	public void setSexo(String sexo) {
		if (sexo.contains("feminino")) 
		{
			this.sexo = "f";
		}
		else if (sexo.contains("masculino")) 
		{
			this.sexo = "m";
		}
		else 
		{
			this.sexo = "0";
		}
				
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
		if (parameter.equals("masculino"))
		{
			this.setSexo("m");
		}
		else if (parameter.equals("feminino"))
		{
			this.setSexo("m");
		}
		else
		{
			this.setSexo("0");
		}
		
	}
}
