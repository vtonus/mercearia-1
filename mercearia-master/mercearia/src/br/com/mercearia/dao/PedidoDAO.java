package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.modelo.Fornecedor;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.util.Conversao;

public class PedidoDAO {
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
	public int adiciona(Pedido pedido) {
		connection = new Conexao().getConnection();
		int retorno = 0;
		String sql = "insert into pedido (valor, descricao, fornecedor, funcionario, datahora) values (?, ?, ?, ?, NOW())";
		//
		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setFloat(1, pedido.getValor());
			ps.setString(2, pedido.getDescricao());
			ps.setInt(3, pedido.getFornecedor().getId());
			ps.setString(4, pedido.getFuncionario().getCpf());
			ps.execute();
			ps.close();
			ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			rs.next();
			retorno = rs.getInt("last_insert_id()");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean editaValor(Pedido pedido) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update pedido set valor = ? where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(2, pedido.getId());
			ps.setFloat(1, pedido.getValor());
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public boolean edita(Pedido pedido) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update pedido set fornecedor = ?, descricao = ?, funcionario = ?, valor = ? where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, pedido.getFornecedor().getId());
			ps.setString(2, pedido.getDescricao());
			ps.setString(3, pedido.getFuncionario().getCpf());
			ps.setFloat(4, pedido.getValor());
			ps.setInt(5, pedido.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public List<Pedido> busca(Pedido pe) {
		connection = new Conexao().getConnection();
		Boolean[] boo = new Boolean[5];
		Calendar calendar;
		for (int j = 0; j < 4; j++) {
			boo[j] = false;
		}

		int i = 0;

		String sql = "select id, descricao, fo.nome, fo.id, fu.nome, fu.cpf, datahora from pedido pe"
				+ "inner join funcionario fu on (co.funcionario = fu.cpf)"
				+ "inner join fornecedor fo on (co.fornecedor = fo.id)";

		// id valor descricao fornecedor funcionario datahora

		try {
			if (pe.getFornecedor().getNome().trim().length() > 0) {
				boo[0] = true;
				sql = sql.concat("where fo.nome like ? ");
				i = 1;
			}
		} catch (NullPointerException e) {
		}

		try {
			if (pe.getFuncionario().getNome().trim().length() > 0) {
				boo[1] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("fu.nome like ? ");
			}
		} catch (NullPointerException e) {
		}

		try {
			calendar = pe.getDataMin();
			if (calendar.isLenient()) {
				boo[2] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("datahora > ?");
			}
		} catch (NullPointerException e) {
		}

		try {
			calendar = pe.getDataMin();
			if (calendar.isLenient()) {
				boo[3] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("datahora < ?");
			}
		} catch (NullPointerException e) {
		}

		try {
			if (pe.getId() > 0) {
				boo[4] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("pe.id = ? ");
			}
		} catch (NullPointerException e) {
		}

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			i = 1;
			if (boo[0]) {
				ps.setString(i, "%" + pe.getFornecedor().getNome() + "%");
				i++;
			}
			if (boo[1]) {
				ps.setString(i, "%" + pe.getFuncionario().getNome() + "%");
				i++;
			}
			if (boo[2]) {
				ps.setTimestamp(i,
						Conversao.dateEmTimestamp(pe.getDataMin().getTime()));
				i++;
			}
			if (boo[3]) {
				ps.setTimestamp(i,
						Conversao.dateEmTimestamp(pe.getDataMin().getTime()));
				i++;
			}
			if (boo[4]) {
				ps.setInt(i, pe.getId());
				i++;
			}

			ResultSet rs = ps.executeQuery();
			List<Pedido> listaPe = new ArrayList<Pedido>();
			calendar = Calendar.getInstance();
			while (rs.next()) {
				pe = new Pedido();
				Funcionario fu = new Funcionario();
				Fornecedor fo = new Fornecedor();
				fu.setNome(rs.getString("fu.nome"));
				fu.setCpf(rs.getString("fu.cpf"));
				fo.setNome(rs.getString("fo.nome"));
				fo.setId(rs.getInt("id"));
				pe.setFornecedor(fo);
				pe.setFuncionario(fu);
				pe.setId(rs.getInt("id"));
				pe.setValor(rs.getFloat("valor"));
				pe.setDescricao(rs.getString("descricao"));
				calendar.setTime(rs.getDate("datahora"));
				listaPe.add(pe);
			}
			ps.close();
			connection.close();
			return listaPe;
		} catch (SQLException e) {
		}
		return null;
	}

	public Pedido busca(int id) {
		connection = new Conexao().getConnection();

		String sql = "select * from pedido where id = ?";

		try {
			Pedido pedido = new Pedido();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Calendar calendar = Calendar.getInstance();
			while (rs.next()) {

				// id valor descricao fornecedor funcionario datahora

				pedido.setId(rs.getInt("pe.id"));
				pedido.setDescricao(rs.getString("pe.descricao"));
				pedido.setFornecedorId(rs.getInt("fo.id"));
				pedido.setFuncionarioId(rs.getLong("fu.cpf"));
				try {
					calendar.setTime(rs.getDate("pe.datahora"));
					pedido.setDataHora(calendar);
				} catch (RuntimeException e) {
					System.out.println("Deu pau na data do produto.");
				}
			}
			ps.close();
			connection.close();
			return pedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean exclui(int id) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "delete from pedido where id = ?";

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

}
