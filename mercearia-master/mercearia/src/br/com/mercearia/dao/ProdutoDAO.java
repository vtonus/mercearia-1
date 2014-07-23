package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.mercearia.faces.NovoProdutoBean;
import br.com.mercearia.modelo.Produto;

public class ProdutoDAO {
	private Connection connection;

	public Produto busca(long id) {
		connection = new Conexao().getConnection();

		String sql = "select * from produto where id = ?";

		try 
		{
			Produto produto = new Produto();
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(" id do produto buscado eh " + id);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setQtd(rs.getInt("qtd"));
				try 
				{
					produto.setValidade(rs.getDate("val_max"));
				}
				catch (SQLException e)
				{
					System.out.println("Deu pau na data do produto.");
				}
				produto.setValor(rs.getFloat("valor"));
				produto.setId(rs.getLong("id"));
			}
			ps.close();
			connection.close();
			return produto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> listaFabricantes() {
		connection = new Conexao().getConnection();

		String sql = "select fabricante from produto";

		try 
		{
			List<String> fabricantes = new ArrayList<String>();
			String fabricante = "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fabricante = rs.getString("fabricante");
				fabricantes.add(fabricante);
			}
			ps.close();
			connection.close();
			return fabricantes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> listaNomes() {
		connection = new Conexao().getConnection();

		String sql = "select nome from produto";

		try 
		{
			List<String> nomes = new ArrayList<String>();
			String nome = "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nome = rs.getString("nome");
				nomes.add(nome);
			}
			ps.close();
			connection.close();
			return nomes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public void adiciona(NovoProdutoBean produto) {
		connection = new Conexao().getConnection();
		//
		String sql = "insert into produto values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setLong(1, produto.getId());
			ps.setFloat(3, produto.getValor());
			ps.setString(2, produto.getNome());
			try {
				ps.setString(4, produto.getFabricante());
			} catch (SQLException e) {
			}
			try {
				ps.setInt(5, produto.getQuantidade());
			} catch (SQLException e) {
			}
			try {
				ps.setDate(6, new Date(produto.getValidade().getTime()));
			} catch (RuntimeException e) {
				e.printStackTrace();
				ps.setNull(6, Types.DATE);
			}
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}