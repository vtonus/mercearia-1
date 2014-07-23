package br.com.mercearia.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mercearia.dao.ProdutoDAO;

@ManagedBean
public class NovoProdutoBean {
	private float valorMin;
	private float valorMax;
	private long id;
	private String nome;
	private float valor;
	private Date validade;
	private int quantidade;
	private String fabricante;
	private String completando;
	
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
	
	public List<String> listaFabricantes(String query) {
		ProdutoDAO pdao = new ProdutoDAO();
		List<String> results = new ArrayList<String>();
		results = pdao.listaFabricantes();
		return results;
	}

	public List<String> listaNomes(String query) {
		ProdutoDAO pdao = new ProdutoDAO();
		List<String> results = new ArrayList<String>();
		results = pdao.listaNomes();
		return results;
	}
	
	public String getCompletando() {
		return completando;
	}

	public void setCompletando(String completando) {
		this.completando = completando;
	}
}
