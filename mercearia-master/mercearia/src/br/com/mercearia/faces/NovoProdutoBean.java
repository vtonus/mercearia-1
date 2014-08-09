package br.com.mercearia.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Produto;

@ManagedBean
public class NovoProdutoBean {
	private long id;
	private String nome, fabricante, completandoF, completandoN;
	private float valor, valorMin, valorMax;
	private Date validade, validadeMin, validadeMax;
	private int quantidade, quantidadeMin, quantidadeMax;

	
	public Date getValidadeMin() {
		return validadeMin;
		
	}

	public void setValidadeMin(Date validadeMin) {
		this.validadeMin = validadeMin;
	}

	public Date getValidadeMax() {
		return validadeMax;
	}

	public void setValidadeMax(Date validadeMax) {
		this.validadeMax = validadeMax;
	}

	public int getQuantidadeMin() {
		return quantidadeMin;
	}

	public void setQuantidadeMin(int quantidadeMin) {
		this.quantidadeMin = quantidadeMin;
	}

	public int getQuantidadeMax() {
		return quantidadeMax;
	}

	public void setQuantidadeMax(int quantidadeMax) {
		this.quantidadeMax = quantidadeMax;
	}

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

	public String getCompletandoF() {
		return completandoF;
	}

	public void setCompletandoF(String completando) {
		this.completandoF = completando;
	}

	public String getCompletandoN() {
		return completandoN;
	}

	public void setCompletandoN(String completando) {
		this.completandoN = completando;
	}

	// Other Methods

	public void cadastrar() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.adiciona(this);
	}

	public String procurar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.procura(this);
		ExternalContext context =
				FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request =
				(HttpServletRequest)context.getRequest();
		HttpServletResponse response =
				(HttpServletResponse)context.getResponse();
		request.setAttribute("lista", produtos);
		
		
		return "NovoProduto";
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
}