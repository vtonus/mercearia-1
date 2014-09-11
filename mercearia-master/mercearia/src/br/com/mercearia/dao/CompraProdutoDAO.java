package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercearia.modelo.Compra;
import br.com.mercearia.modelo.CompraProduto;
import br.com.mercearia.modelo.Produto;

public class CompraProdutoDAO {
	private Connection connection;

	public void adiciona(CompraProduto compraProduto) {
		connection = new Conexao().getConnection();

		String sql = "insert into compraproduto "
				+ "(id_produto, id_compra, valor, qtd)"
				+ " values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, compraProduto.getProduto().getId());
			ps.setInt(2, compraProduto.getCompraId());
			ps.setFloat(3, compraProduto.getProduto().getValor());
			ps.setInt(4, compraProduto.getProduto().getQtd());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CompraProduto> buscaCompraProduto(int i) {
		connection = new Conexao().getConnection();

		String sql = "select id_produto, id_compra, cp.valor, cp.qtd, p.nome "
				+ " from compraproduto cp inner join produto p on (cp.id_produto = p.id)"
				+ "where cp.id_compra = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			List<CompraProduto> listaCp = new ArrayList<CompraProduto>();
			while (rs.next()) {
				Produto produto = new Produto();
				CompraProduto cp = new CompraProduto();
				Compra compra = new Compra();
				compra.setId(rs.getInt("id_compra"));
				produto.setId(rs.getLong("id_produto"));
				cp.setValor(rs.getFloat("valor"));
				cp.setQtd(rs.getInt("qtd"));
				produto.setNome(rs.getString("nome"));
				cp.setCompra(compra);
				cp.setProduto(produto);

				listaCp.add(cp);
			}
			return listaCp;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
