package br.com.mercearia.modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class RelatorioPeriodo {

	private ArrayList<String> nomep;
	private ArrayList<Float> valorp;
	
	private ArrayList<String> nomef;
	private ArrayList<Float> qtdf;
	
	private ArrayList<Float> valor;
	private ArrayList<Float> valorf;

	private ArrayList<Calendar> meses;
	
	public ArrayList<String> getNomep() {
		return nomep;
	}
	public void setNomep(ArrayList<String> nomep) {
		this.nomep = nomep;
	}
	public ArrayList<String> getNomef() {
		return nomef;
	}
	public void setNomef(ArrayList<String> nomef) {
		this.nomef = nomef;
	}
	public ArrayList<Float> getQtdf() {
		return qtdf;
	}
	public void setQtdf(ArrayList<Float> qtdf) {
		this.qtdf = qtdf;
	}
	public ArrayList<Calendar> getMeses() {
		return meses;
	}
	public void setMeses(ArrayList<Calendar> meses) {
		this.meses = meses;
	}
	public ArrayList<Float> getValor() {
		return valor;
	}
	public void setValor(ArrayList<Float> valor) {
		this.valor = valor;
	}
	public ArrayList<Float> getValorf() {
		return valorf;
	}
	public void setValorf(ArrayList<Float> valorf) {
		this.valorf = valorf;
	}
	public ArrayList<Float> getValorp() {
		return valorp;
	}
	public void setValorp(ArrayList<Float> valorp) {
		this.valorp = valorp;
	}
	
	
}