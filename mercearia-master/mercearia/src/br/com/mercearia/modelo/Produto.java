package br.com.mercearia.modelo;

import java.util.Calendar;

public class Produto {
	private long id;
	private float valor;
	private float valorMax;
	private float valorMin;
	public float getValorMax() {
		return valorMax;
	}

	public void setValorMax(float valorMax) {
		this.valorMax = valorMax;
	}

	public float getValorMin() {
		return valorMin;
	}

	public void setValorMin(float valorMin) {
		this.valorMin = valorMin;
	}

	private String nome;
	private String fabricante;
	private int qtd;
	private Calendar validade;
	private int estoque;

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}


	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

}
