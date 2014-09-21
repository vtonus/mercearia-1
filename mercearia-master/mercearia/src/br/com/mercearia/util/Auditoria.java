package br.com.mercearia.util;

import org.primefaces.component.calendar.Calendar;

import br.com.mercearia.dao.Conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Auditoria {
	
	private Connection connection;
	private int func_id;
	private Calendar datahora;
	private int acao;
	private int tabela;
	private String presente;
	private String passado;

	
	public int getFunc_id() {
		return func_id;
	}
	public void setFunc_id(int func_id) {
		this.func_id = func_id;
	}
	public Calendar getDatahora() {
		return datahora;
	}
	public void setDatahora(Calendar datahora) {
		this.datahora = datahora;
	}
	public int getAcao() {
		return acao;
	}
	public void setAcao(int acao) {
		this.acao = acao;
	}
	public int getTabela() {
		return tabela;
	}
	public void setTabela(int tabela) {
		this.tabela = tabela;
	}
	public String getPresente() {
		return presente;
	}
	public void setPresente(String presente) {
		this.presente = presente;
	}
	public String getPassado() {
		return passado;
	}
	public void setPassado(String passado) {
		this.passado = passado;
	}
	
/*	public boolean adiciona() {
		connection = new Conexao().getConnection();
		String sql = "insert into auditoria (cpf_funcionario, datahora, acao, tabela, presente, passado)"
				+ " values()";
		//"id, cpf_funcionario, datahora, acao, tabela, presente, passado";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setLong(3, cliente.getTelefone());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getEmail());
			ps.setDate(6, new Date(cliente.getDataNascimento()
					.getTimeInMillis()));
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}*/	
}