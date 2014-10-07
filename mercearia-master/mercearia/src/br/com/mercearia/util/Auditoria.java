package br.com.mercearia.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.Conexao;

@SuppressWarnings("serial")
public class Auditoria extends HttpServlet{
	//Acao - 0,Ins 1,Edi 2,Exc - 
	//Tabela - cliente,0 compra,1 compraproduto,2 fabricante,3 fornecedor,4 funcionario,5 pedido,6 produto,7 produtopedido,8
	
	HttpServletRequest request;
	
	
	private Connection connection;
	private String func_id;
	private int acao;
	private int tabela;
	private String dados;

	
	public String getDados() {
		return dados;
	}
	public void setDados(String dados) {
		this.dados = dados;
	}
	public String getFunc_id() {
		return func_id;
	}
	public void setFunc_id(String func_id) {
		this.func_id = func_id;
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

	public boolean adiciona() {
		connection = new Conexao().getConnection();
		boolean bool = false;

		String sql = "insert into auditoria (cpf_funcionario, datahora, acao, tabela, dados)"
				+ " values(?, NOW(), ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, this.getFunc_id());
			ps.setInt(2, this.getAcao());
			ps.setInt(3, this.getTabela());
			ps.setString(4, this.getDados());
			
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}	
}