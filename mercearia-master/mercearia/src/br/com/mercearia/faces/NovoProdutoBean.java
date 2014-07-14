package br.com.mercearia.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NovoProdutoBean {
	private String nome;
	private float valor;
	private Date validade;
	private int quantidade;
	private String fabricante;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void cadastrar(){
		System.out.println("Entrou aque");
		System.out.format("Foi gravado as seguintes info.. \nnome: %s, \nfabricante: %s, \nquantidade: %d, \nvalidade: %s, \nvalor: %f \n Wow!!",this.getNome(),this.getFabricante(),this.getQuantidade(),this.getValidade(),this.getValor());
	}
}
