package br.com.mercearia.faces;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import br.com.mercearia.dao.ProdutoDAO;

@ManagedBean
public class NovoProdutoBean {
	private long id;
	private String nome;
	private float valor;
	private Date validade;
	private int quantidade;
	private String fabricante;
	
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
		ProdutoDAO dao = new ProdutoDAO();
		dao.adiciona(this);
	}
	
	public Lista<String> complete(String query) {
	Lista<String> results = new ArrayList<String>();
	for (int i = 0; i < 10; i++)
	results.add(query + i);
	return results;
}
