package br.com.mercearia.modelo;

import java.util.Calendar;

public class Pedido {
	private int id;
	private float valor;
	private String descricao;
	private Fornecedor fornecedor;
	private Calendar dataHora;
	private Funcionario funcionario;
	private Calendar dataMax;
	private Calendar dataMin;
	private int fornecedorId;
	private long funcionarioId;
	
	
	public int getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	public long getFuncionarioId() {
		return funcionarioId;
	}
	public void setFuncionarioId(long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public Calendar getDataMax() {
		return dataMax;
	}
	public void setDataMax(Calendar dataMax) {
		this.dataMax = dataMax;
	}
	public Calendar getDataMin() {
		return dataMin;
	}
	public void setDataMin(Calendar dataMin) {
		this.dataMin = dataMin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float  getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Calendar getDataHora() {
		return dataHora;
	}
	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

 
}
