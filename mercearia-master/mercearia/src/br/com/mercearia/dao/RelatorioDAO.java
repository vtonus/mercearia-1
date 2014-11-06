package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.mercearia.modelo.RelatorioD;
import br.com.mercearia.modelo.RelatorioPeriodo;
import br.com.mercearia.util.Conversao;

public class RelatorioDAO {
	private Connection connection;

	/*
	 * public int adiciona(Cliente cliente) { connection = new
	 * Conexao().getConnection(); int bool = 0; String sql =
	 * "insert into cliente " +
	 * "(cpf, nome, telefone, sexo, email, dataNascimento, endereco)" +
	 * " values (?, ?, ?, ?, ?, ?, ?)";
	 * 
	 * try { PreparedStatement ps = connection.prepareStatement(sql);
	 * System.out.
	 * println(cliente.getCpf()+".\n"+cliente.getCpf()+".\n"+cliente.getNome
	 * ()+".\n"
	 * +cliente.getTelefone()+".\n"+cliente.getSexo()+".\n"+cliente.getEmail
	 * ()+".\n"+cliente.getDataNascimento()); ps.setString(1, cliente.getCpf());
	 * ps.setString(2, cliente.getNome()); ps.setLong(3, cliente.getTelefone());
	 * ps.setString(4, cliente.getSexo()); ps.setString(5, cliente.getEmail());
	 * try { ps.setDate(6, new Date(cliente.getDataNascimento()
	 * .getTimeInMillis())); } catch(RuntimeException e){ ps.setNull(6,
	 * java.sql.Types.DATE); } ps.setString(7, cliente.getEndereco());
	 * ps.execute(); ps.close(); ps =
	 * connection.prepareStatement("SELECT LAST_INSERT_ID()"); ResultSet rs =
	 * ps.executeQuery(); rs.next(); bool = rs.getInt("last_insert_id()");
	 * ps.close(); connection.close(); } catch (SQLException e)
	 * {e.printStackTrace();} return bool; }
	 */

	public RelatorioD buscaDiario(Calendar dia) {
		RelatorioD rd = new RelatorioD();
		ArrayList<Calendar> listaC = new ArrayList<Calendar>();
		connection = new Conexao().getConnection();

		int contador = 0;

		for (int i = 1; i < 31; i++) {

			try {
				PreparedStatement ps = connection
						.prepareStatement("select id from caixa where "
								+ "DATE(datahora) = DATE_SUB( ? , INTERVAL ? DAY)"
								+ "AND operacao = 2");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				if (!(rs.next())) {
					break;
				}
				contador++;
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Float> valor = new ArrayList<Float>();
		for (int i = 0; i < contador; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) AS total, DATE(datahora) as data from compra where DATE(datahora) = DATE_SUB( ? , INTERVAL ? DAY)");

				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				rs.next();
				valor.add(rs.getFloat("total"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				listaC.add(calendar);
				ps.close();
			} catch (SQLException e) {
				System.out.println("Erro grave no DAO.");
				return null;
			}
		}
		rd.setValor(valor);
		rd.setListaCalendar(listaC);
		try {
			PreparedStatement ps = connection
					.prepareStatement("select AVG(valor) AS media from compra where DATE(datahora) is between DATE(DATE_SUB( ? , INTERVAL ? DAY)) and ?");

			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, (contador - 1));
			ps.setDate(3, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			rs.next();
			rd.setMmensal(rs.getFloat("media"));
			ps.close();
		} catch (SQLException e) {
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select p.nome, SUM(cp.qtd) as total from compraproduto cp inner join  produto p on (cp.id_produto = p.id) inner join compra c on (cp.id_compra = c.id) where DATE(c.datahora) = ? group by p.id order by cp.qtd desc limit 15");

			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();

			ArrayList<String> al2 = new ArrayList<String>();
			ArrayList<Integer> al3 = new ArrayList<Integer>();

			while (rs.next()) {
				al2.add(rs.getString("p.nome"));
				al3.add(rs.getInt("total"));
			}
			rd.setNome(al2);
			rd.setQtd(al3);
			ps.close();
		} catch (SQLException e) {
		}

		for (int i = 0; i < 24; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) as total from compra where c.datahora between ? and ? ");

				String data1 = Conversao.calendarEmTexto(dia);
				String data2 = Conversao.calendarEmTexto(dia);
				if (i < 9) {
					data1.concat(" 0" + i + ":00:00");
					data2.concat(" 0" + i + ":59:59");
				} else {
					data1.concat(" " + i + ":00:00");
					data2.concat(" " + i + ":59:59");
				}
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();

				try {
					c1 = Conversao.textoEmDataHoraLocal(data1);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}

				try {
					c2 = Conversao.textoEmDataHoraLocal(data2);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}

				ps.setDate(1, new Date(c1.getTimeInMillis()));
				ps.setDate(2, new Date(c2.getTimeInMillis()));

				ResultSet rs = ps.executeQuery();
				ArrayList<Integer> al1 = new ArrayList<Integer>();
				while (rs.next()) {
					al1.add(rs.getInt("total"));
				}
				rd.setQtd(al1);
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select valor from caixa where DATE(datahora) = ? and operacao = 0");

			ps.setDate(1, new Date(dia.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();

			rs.next();
			rd.setAbriu(Float.parseFloat(rs.getString("valor")));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select valor from caixa where DATE(datahora) = ? and operacao = 1");

			ps.setDate(1, new Date(dia.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();

			rs.next();
			rd.setFechou(Float.parseFloat(rs.getString("valor")));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(valor) as total, metodo from compra where DATE(datahora) = ? group by metodo order by metodo asc");

			ps.setDate(1, new Date(dia.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				switch (rs.getInt("metodo")) {
				case 0:
					rd.setCartao(rs.getFloat("total"));
					break;
				case 1:
					rd.setDinheiro(rs.getFloat("total"));
					break;
				case 2:
					rd.setPrazo(rs.getFloat("total"));
					break;
				}
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return rd;

	}

	public RelatorioPeriodo buscaPeriodo(Calendar dia, int cont) {
		RelatorioPeriodo rp = new RelatorioPeriodo();

		ArrayList<String> nomesp = new ArrayList<String>();
		ArrayList<Float> valoresp = new ArrayList<Float>();
		ArrayList<String> nomesf = new ArrayList<String>();
		ArrayList<Float> valores = new ArrayList<Float>();
		ArrayList<Float> valoresf = new ArrayList<Float>();
		ArrayList<Float> qtdsf = new ArrayList<Float>();

		connection = new Conexao().getConnection();

		for (int i = 0; i < cont; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) AS total as data from compra where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?)");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ps.setDate(3, new Date(dia.getTimeInMillis()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				valores.add(rs.getFloat("total"));
				ps.close();
			} catch (SQLException e) {
				System.out.println("Erro grave no DAO.");
				return null;
			}
		}
		rp.setValor(valores);

		for (int i = 0; i < cont; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) AS total, as data from pedido where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?)");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ps.setDate(3, new Date(dia.getTimeInMillis()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				valoresf.add(rs.getFloat("total"));
				ps.close();
			} catch (SQLException e) {
				System.out.println("Erro grave no DAO.");
				return null;
			}
		}
		rp.setValorf(valoresf);

		try {
			PreparedStatement ps = connection
					.prepareStatement("select p.nome, SUM(cp.valor) AS total from compraproduto cp inner join  compra co on (cp.id_compra = co.id) inner join produto p on (cp.id_produto = p.id) where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?) group by cp.id_produto LIMIT 15");
			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, cont);
			ps.setDate(3, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nomesp.add(rs.getString("p.nome"));
				valoresp.add(rs.getFloat("total"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro grave no DAO.");
			return null;
		}

		rp.setNomep(nomesp);
		rp.setValorp(valoresp);

		try {
			PreparedStatement ps = connection
					.prepareStatement("select f.nome, SUM(p.valor) AS total from pedido p inner join fornecedor f on (p.fornecedor = f.id) where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?) group by p.fornecedor LIMIT 15");
			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, cont);
			ps.setDate(3, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nomesf.add(rs.getString("f.nome"));
				qtdsf.add(rs.getFloat("total"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro grave no DAO.");
			return null;
		}

		rp.setNomef(nomesf);
		rp.setQtdf(valoresf);
		
		return rp;
	}
	
}
