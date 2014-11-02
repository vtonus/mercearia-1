package br.com.mercearia.modelo;

import java.util.Calendar;
import java.util.List;

public class RelatorioD {
	private List<Produto> produtos;
	private float abriu;
	private float fechou;
	private float cartao;
	private float dinheiro;
	private float prazo;
	private List<Calendar> listaCalendar;
	private float venda[];
	private float valor[];
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public float getAbriu() {
		return abriu;
	}
	public void setAbriu(float abriu) {
		this.abriu = abriu;
	}
	public float getFechou() {
		return fechou;
	}
	public void setFechou(float fechou) {
		this.fechou = fechou;
	}
	public float getCartao() {
		return cartao;
	}
	public void setCartao(float cartao) {
		this.cartao = cartao;
	}
	public float getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(float dinheiro) {
		this.dinheiro = dinheiro;
	}
	public float getPrazo() {
		return prazo;
	}
	public void setPrazo(float prazo) {
		this.prazo = prazo;
	}
	public List<Calendar> getListaCalendar() {
		return listaCalendar;
	}
	public void setListaCalendar(List<Calendar> listaCalendar) {
		this.listaCalendar = listaCalendar;
	}
	public float[] getVenda() {
		return venda;
	}
	public void setVenda(float[] venda) {
		this.venda = venda;
	}
	public float[] getValor() {
		return valor;
	}
	public void setValor(float[] valor) {
		this.valor = valor;
	}
	
	
}
