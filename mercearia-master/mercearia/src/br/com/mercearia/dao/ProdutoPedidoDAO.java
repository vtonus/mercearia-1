package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.modelo.ProdutoPedido;

public class ProdutoPedidoDAO {
	private Connection connection;

	/*
	 * public Produto busca(long id) { connection = new
	 * Conexao().getConnection();
	 * 
	 * String sql = "select * from produto where id = ?";
	 * 
	 * try { Produto produto = new Produto(); PreparedStatement ps =
	 * connection.prepareStatement(sql);
	 * System.out.println(" id do produto buscado eh " + id); ps.setLong(1, id);
	 * ResultSet rs = ps.executeQuery(); while (rs.next()) {
	 * produto.setNome(rs.getString("nome")); produto.setQtd(rs.getInt("qtd"));
	 * Calendar calendar = Calendar.getInstance();
	 * calendar.setTime(rs.getDate("val_max")); produto.setVal_max(calendar);
	 * calendar.setTime(rs.getDate("val_min")); produto.setVal_min(calendar);
	 * produto.setValor(rs.getFloat("valor")); produto.setId(rs.getLong("id"));
	 * } ps.close(); connection.close(); return produto; } catch (SQLException
	 * e) { throw new RuntimeException(e); } }
	 */
	public boolean adiciona(ProdutoPedido produtoPedido) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "insert into produtoPedido values (?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setLong(1, produtoPedido.getProduto().getId());
			ps.setInt(2, produtoPedido.getPedidoId());
			ps.setInt(3, produtoPedido.getProduto().getQtd());
			ps.setFloat(4, produtoPedido.getProduto().getValor());
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
		}
		return bool;
	}

	public boolean exclui(int i) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		try {
			String sql = "delete from produtopedido where id_pedido=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, i);
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

/*	public boolean edita(ProdutoPedido pp) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		try{
			
		
		String sql = "delete from produtopedido where id_pedido=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, pp.getPedidoId());
		ps.execute();
		ps.close();

		try {
			ps = connection.prepareStatement("insert into produtopedido values(?, ?, ?, ?");

			ps.setLong(1, pp.getProduto().getId());
			ps.setInt(2, pp.getPedidoId());
			ps.setInt(3, pp.getProduto().getQtd());
			ps.setFloat(4, pp.getProduto().getValor());
			
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
*/
	public List<ProdutoPedido> buscaPedido(int i) {
		connection = new Conexao().getConnection();
		String sql = "select pp.qtd, pp.valor, p.nome, p.id from produtopedido pp "
				+ "inner join produto p on (pp.id_produto = p.id) "
				+ "where id_pedido = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			List<ProdutoPedido> listaPp = new ArrayList<ProdutoPedido>();
			while (rs.next()) {
				ProdutoPedido pp = new ProdutoPedido();
				Produto pr = new Produto();
				pr.setNome(rs.getString("p.nome"));
				pr.setQtd(rs.getInt("pp.qtd"));
				pr.setValor(rs.getFloat("pp.valor"));
				pr.setId(rs.getLong("p.id"));
				Pedido pe = new Pedido();
				pe.setId(i);
				pp.setPedido(pe);
				listaPp.add(pp);
			}
			return listaPp;
		} catch (SQLException e) {
		}
		return null;
	}

}
