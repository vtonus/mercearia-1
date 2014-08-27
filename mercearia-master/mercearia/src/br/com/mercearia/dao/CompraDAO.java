package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.modelo.Compra;
import br.com.mercearia.modelo.CompraProduto;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

public class CompraDAO {
	private Connection connection;

	public int adicionaC(Compra compra) {
		connection = new Conexao().getConnection();
		int retorno = 0;

		String sql = "insert into compra "
				+ "(datahora, valor, id_funcionario, id_cliente)"
				+ " values (NOW(), ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setFloat(1, compra.getValor());
			ps.setLong(2, compra.getFuncionario().getCpf());
			ps.setInt(3, compra.getCliente().getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			rs.next();
			retorno = rs.getInt("last_insert_id()");
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public int adiciona(Compra compra) {
		int retorno = 0;
		connection = new Conexao().getConnection();

		String sql = "insert into compra "
				+ "(datahora, valor, id_funcionario)" + " values (NOW(), ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setFloat(1, compra.getValor());
			ps.setLong(2, compra.getFuncionario().getCpf());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			rs.next();
			retorno = rs.getInt("last_insert_id()");
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<Compra> buscaCompra(Compra compra) {
		connection = new Conexao().getConnection();
		Calendar calendar;
		Boolean[] boo = new Boolean[5];
		for (int j = 0; j < 5; j++) {
			boo[j] = false;
		}
		// id, dataHoraIni, dataHoraFim, id_funcionario, id_cliente

		int i = 0;
		String sql = "select id, valor, cl.nome, fu.nome, datahora from compra co " +
						"inner join cliente cl on (co.id_cliente = cl.id_cliente) "+
						"inner join funcionario fu on (fu.cpf = co.id_funcionario) ";
		try {
			if (compra.getId() > 0) {
				boo[0] = true;
				sql = sql.concat("where id = ? ");
				i = 1;
			}
		} catch (RuntimeException e) {
		}

		try {
			if (compra.getFuncionarioNome().trim().length() > 0) {
				boo[1] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("fu.nome like ? ");
			}
		} catch (RuntimeException e) {
		}

		try {
			if (compra.getClienteNome().trim().length() > 0) {
				boo[2] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("cl.nome like ? ");
			}
		} catch (RuntimeException e) {
		}

		try {
			calendar = Calendar.getInstance();
			calendar = compra.getHoraIni();
			boo[3] = true;
			if (i == 0) {
				sql = sql.concat("where ");
				i = 2;
			} else {
				sql = sql.concat("and ");
				i = 2;
			}
		} catch (NullPointerException e) {
		}

		try {
			calendar = Calendar.getInstance();
			calendar = compra.getHoraFim();

			boo[4] = true;
			if (i == 0) {
				sql = sql.concat("where datahora <= ? ");
				i = 3;
			}

			else if (i == 1) {
				sql = sql.concat("and datahora <= ? ");
				i = 3;
			} else {
				sql = sql.concat("datahora between ? and ? ");
				i = 3;
			}
		} catch (NullPointerException e) {
		}
		if (i == 2) {
			sql = sql.concat("validade >= ?");
		}
		System.out.println(sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			i = 1;
			if (boo[0]) {
				ps.setLong(i, compra.getId());
				i++;
			}
			if (boo[1]) {
				ps.setString(i, "%" + compra.getFuncionarioNome() + "%");
				i++;
			}
			if (boo[2]) {
				ps.setString(i, "%" + compra.getClienteNome() + "%");
				i++;
			}
			if (boo[3]) {
				ps.setTimestamp(i, Conversao.dateEmTimestamp(compra.getHoraIni().getTime()));
				i++;
			}
			if (boo[4]) {
				ps.setTimestamp(i, Conversao.dateEmTimestamp(compra.getHoraFim().getTime()));
				i++;
			}
			//System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
			while (rs.next()) {
				compra.setId(rs.getInt("id"));
				compra.setFuncionarioNome(rs.getString("fu.nome"));
				compra.setClienteNome(rs.getString("cl.nome"));
				compra.setValor(rs.getFloat("valor"));
				calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("datahora"));
				compra.setHora(calendar);
				compras.add(compra);
			}
			ps.close();
			connection.close();
			return compras;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean exclui(int id) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "delete from compra where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}

}
