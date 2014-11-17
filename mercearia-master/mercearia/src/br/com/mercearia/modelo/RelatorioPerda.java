package br.com.mercearia.modelo;

import java.util.ArrayList;
import java.util.Calendar;


public class RelatorioPerda {
	ArrayList<String> lista_nome = new ArrayList<String>();
	ArrayList<Integer> lista_qtd = new ArrayList<Integer>();
	
	ArrayList<String> lista_motivo = new ArrayList<String>();
	ArrayList<Integer> lista_qtdMotivo = new ArrayList<Integer>();
	
	ArrayList<Calendar> lista_mes = new ArrayList<Calendar>();
	ArrayList<Float> lista_valor = new ArrayList<Float>();
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	public ArrayList<String> getLista_nome() {
		return lista_nome;
	}
	public void setLista_nome(ArrayList<String> lista_nome) {
		this.lista_nome = lista_nome;
	}
	public ArrayList<Integer> getLista_qtd() {
		return lista_qtd;
	}
	public void setLista_qtd(ArrayList<Integer> lista_qtd) {
		this.lista_qtd = lista_qtd;
	}
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
}