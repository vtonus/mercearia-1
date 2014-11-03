package br.com.mercearia.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RelatorioD {
	private ArrayList<Integer> qtd;
	private ArrayList<String> nome;
	
	private float abriu;
	private float fechou;
	private float cartao;
	private float dinheiro;
	private float prazo;
	
	private ArrayList<Calendar> listaCalendar;
	private ArrayList<Float> venda;//24 horas
	
	private ArrayList<Float> valor;//30 dias
	private float mmensal;
	
	//----------------------
	
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
	public ArrayList<Calendar> getListaCalendar() {
		return listaCalendar;
	} 
	public void setListaCalendar(ArrayList<Calendar> listaCalendar1) {
		this.listaCalendar = listaCalendar1;
	}
	public List<Float> getVenda() {
		return venda;
	}
	public void setVenda(ArrayList<Float> valor) {
		this.venda = valor;
	}

	public ArrayList<Integer> getQtd() {
		return qtd;
	}
	public void setQtd(ArrayList<Integer> qtd) {
		this.qtd = qtd;
	}
		
	public List<String> getNome() {
		return nome;
	}
	public void setNome(ArrayList<String> nome) {
		this.nome = nome;
	}
	
	public List<Float> getValor() {
		return valor;
	}
	public void setValor(ArrayList<Float> lista) {
		this.valor = lista;
	}
}
