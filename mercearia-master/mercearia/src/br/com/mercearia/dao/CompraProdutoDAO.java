package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.CompraProduto;

public class CompraProdutoDAO {
	private Connection connection;

	public void adiciona(CompraProduto compraProduto) {
		connection = new Conexao().getConnection();

		String sql = "insert into compraproduto "
				+ "(id_produto, id_compra, valor, qtd)"
				+ " values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println("8888888888" +compraProduto.getProduto().getId());
			ps.setLong(1, compraProduto.getProduto().getId());
			ps.setInt(2, compraProduto.getCompra().getId());
			ps.setFloat(3, compraProduto.getValor());
			ps.setInt(4, compraProduto.getQtd());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CompraProduto> buscaCompraProduto(int i) {
		connection = new Conexao().getConnection();


		String sql = "select " 
		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps = this.connection.prepareStatement(sql);
			palavraChave = ("%" + palavraChave + "%");
			if (i == 0) {
				ps.setString(1, palavraChave);
			} else {
				try {
					i = Integer.parseInt(palavraChave);
				} catch (RuntimeException e) {
					throw new RuntimeException(e);
				}
				ps.setInt(1, i);
			}
			ResultSet rs = ps.executeQuery();
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setDoc(rs.getString("doc"));
				cliente.setTelefone(Long.parseLong(rs.getString("telefone")));
				cliente.setSexo(rs.getString("sexo"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				cliente.setDataNascimento(calendar);
				cliente.setEmail(rs.getString("email"));
				listaCliente.add(cliente);
			}
			return listaCliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	}
}
