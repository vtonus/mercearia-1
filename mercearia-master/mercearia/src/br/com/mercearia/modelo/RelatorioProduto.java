package br.com.mercearia.modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class RelatorioProduto {
	private ArrayList<String> lista_motivo = new ArrayList<String>();
	private ArrayList<Integer> lista_qtdMotivo = new ArrayList<Integer>();
	private ArrayList<Calendar> lista_mes = new ArrayList<Calendar>();
	private ArrayList<Float> lista_valor = new ArrayList<Float>();
	private float vendat;
	private float invest;
	private float perdat;
	private float saldot;
	
	public ArrayList<String> getLista_motivo() {
		return lista_motivo;
	}
	public void setLista_motivo(ArrayList<String> lista_motivo) {
		this.lista_motivo = lista_motivo;
	}
	public ArrayList<Integer> getLista_qtdMotivo() {
		return lista_qtdMotivo;
	}
	public void setLista_qtdMotivo(ArrayList<Integer> lista_qtdMotivo) {
		this.lista_qtdMotivo = lista_qtdMotivo;
	}
	public ArrayList<Calendar> getLista_mes() {
		return lista_mes;
	}
	public void setLista_mes(ArrayList<Calendar> lista_mes) {
		this.lista_mes = lista_mes;
	}
	public ArrayList<Float> getLista_valor() {
		return lista_valor;
	}
	public void setLista_valor(ArrayList<Float> lista_valor) {
		this.lista_valor = lista_valor;
	}
	public float getVendat() {
		return vendat;
	}
	public void setVendat(float vendat) {
		this.vendat = vendat;
	}
	public float getInvest() {
		return invest;
	}
	public void setInvest(float invest) {
		this.invest = invest;
	}
	public float getPerdat() {
		return perdat;
	}
	public void setPerdat(float perdat) {
		this.perdat = perdat;
	}
	public float getSaldot() {
		return saldot;
	}
	public void setSaldot(float saldot) {
		this.saldot = saldot;
	}
	
}
