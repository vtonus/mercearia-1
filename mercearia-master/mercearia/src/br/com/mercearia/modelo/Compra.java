package br.com.mercearia.modelo;

import java.util.Calendar;

public class Compra {
	private int id;
	private int metodo;
	private Calendar hora;
	private Calendar horaIni;
	private Calendar horaFim;
	private float valor;
	private Funcionario funcionario;	
	private String funcionarioId;
	private String funcionarioNome;
	private String funcId;

	public int getMetodo() {
		return metodo;
	}

	public void setMetodo(int metodo) {
		this.metodo = metodo;
	}

	
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public String getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	private String clienteNome;
	private Cliente cliente;

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public Calendar getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(Calendar horaIni) {
		this.horaIni = horaIni;
	}

	public Calendar getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Calendar horaFim) {
		this.horaFim = horaFim;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
