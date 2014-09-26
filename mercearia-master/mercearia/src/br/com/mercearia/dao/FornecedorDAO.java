package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercearia.modelo.Fornecedor;

public class FornecedorDAO {
	private Connection connection;

	public boolean adiciona(Fornecedor fornecedor) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "insert into fornecedor "
				+ "(nome, cnpj, telefone, email, endereco)"
				+ " values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, fornecedor.getNome());
			ps.setLong(2, fornecedor.getCnpj());
			ps.setLong(3, fornecedor.getTelefone());
			ps.setString(4, fornecedor.getEmail());
			ps.setString(5, fornecedor.getEndereco());

			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}

	public List<Fornecedor> busca(String nome) {
		connection = new Conexao().getConnection();
		String sql = "select * from fornecedor where nome like ?";
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(Integer.parseInt(rs.getString("id")));
				fornecedor.setNome(rs.getString("nome"));
				listaFornecedor.add(fornecedor);
			}
			return listaFornecedor;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFornecedor;
	}

	public boolean edita(Fornecedor f) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update fornecedor set nome= ?, cnpj= ?, telefone= ?, email= ?, endereco= ? where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, f.getNome());
			ps.setLong(2, f.getCnpj());
			ps.setLong(3, f.getTelefone());
			ps.setString(4, f.getEmail());
			ps.setString(5, f.getEndereco());
			ps.setInt(6, f.getId());
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public boolean exclui(int id) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "delete from fornecedor where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
		}
		return bool;
	}

	public List<Fornecedor> busca(String palavraChave, String parametro) {
		connection = new Conexao().getConnection();
		int i = 0;
		String sql;
		if (parametro.equals("nome")) {
			sql = "select * from fornecedor where nome like ?";
		} else if (parametro.equals("cnpj")) {
			sql = "select * from fornecedor where cnpj like ?";
			//i++;
		} else {
			return null;
		}

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps = this.connection.prepareStatement(sql);
			palavraChave = ("%" + palavraChave + "%");
			if (i == 0) {
				ps.setString(1, palavraChave);
			} else {
				try {
					i = Integer.parseInt(palavraChave);
					ps.setInt(1, i);
				} catch (RuntimeException e) {
					System.out.println("Falha na conversão da Palavra Chave");
					return null;
				}
			}
			ResultSet rs = ps.executeQuery();
			List<Fornecedor> listaF = new ArrayList<Fornecedor>();
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(Integer.parseInt(rs.getString("id")));
				f.setNome(rs.getString("nome"));
				try {
					f.setCnpj(Long.parseLong(rs.getString("cnpj")));
				} catch (RuntimeException e) {
				}
				try {
					f.setTelefone(rs.getLong("telefone"));
				} catch (RuntimeException e) {
				}
				f.setEmail(rs.getString("email"));
				f.setEndereco(rs.getString("endereco"));

				listaF.add(f);
			}
			return listaF;
		} catch (SQLException e) {
			return null;
		}
	}

}
