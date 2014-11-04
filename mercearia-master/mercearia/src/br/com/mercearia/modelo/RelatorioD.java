package br.com.mercearia.modelo;

import java.util.Calendar;
import java.util.List;

public class RelatorioD {
	private int qtd[];
	private float abriu;
	private float fechou;
	private float cartao;
	private float dinheiro;
	private float prazo;
	private List<Calendar> listaCalendar;
	private String nome[];
	private float venda[];
	private float valor[];
	private float mmensal;
	
	public float getMmensal() {
		return mmensal;
	}
	public void setMmensal(float mmensal) {
		this.mmensal = mmensal;
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
	public float getVenda(int c) {
		return venda[c];
	}
	public void setVenda(int c, float valor) {
		this.venda[c] = valor;
	}

	public float getQtd(int c) {
		return qtd[c];
	}
	public void setQtd(int c, int q) {
		this.venda[c] = q;
	}
	
	
	public String getNome(int c) {
		return nome[c];
	}
	public void setNome(int c, String nome) {
		this.nome[c] = nome;
	}
	
	public float getValor(int c) {
		return valor[c];
	}
	public void setValor(int c, float v) {
		this.valor[c] = v;
	}
	
	
}
