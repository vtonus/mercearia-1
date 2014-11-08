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
import br.com.mercearia.modelo.RelatorioPerda;
import br.com.mercearia.modelo.RelatorioPeriodo;
import br.com.mercearia.modelo.RelatorioProduto;
import br.com.mercearia.util.Conversao;

public class RelatorioDAO {
	private Connection connection;

	public RelatorioD buscaDiario(Calendar dia) {
		RelatorioD rd = new RelatorioD();
		ArrayList<Calendar> listaC = new ArrayList<Calendar>();
		connection = new Conexao().getConnection();

		Integer[] intlist = new Integer[30];
		for (int i = 0; i < 30; i++) {
			intlist[i] = -1;
		}
		int x = 0;
		for (int i = 0; i < 30; i++) {

			try {
				PreparedStatement ps = connection
						.prepareStatement("select id from caixa where "
								+ "DATE(datahora) = DATE_SUB( ? , INTERVAL ? DAY)"
								+ "AND operacao = 0");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					intlist[x] = i;

					x++;
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Float> valor = new ArrayList<Float>();
		for (int i = 0; i < 30; i++) {
			System.out.println("..." + i);
			if (intlist[i] >= 0) {
				try {

					PreparedStatement ps = connection
							.prepareStatement("select SUM(valor) AS total, DATE(datahora) as data from compra where DATE(datahora) = DATE_SUB( ? , INTERVAL ? DAY)");

					ps.setDate(1, new Date(dia.getTimeInMillis()));
					ps.setInt(2, intlist[i]);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						valor.add(rs.getFloat("total"));
						Calendar calendar = Calendar.getInstance();
						try {
							calendar = Conversao.textoHEmData(rs
									.getString("data"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						listaC.add(calendar);
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				Calendar calendar = (Calendar) dia.clone();
				if (i <= 9) {
					Conversao.subtracaoCalendar(calendar, "00-00-0" + i);
				} else {
					Conversao.subtracaoCalendar(calendar, "00-00-" + i);
				}
				valor.add((float) 0);
				listaC.add(calendar);
			}
		}
		rd.setValor(valor);
		rd.setListaCalendar(listaC);

		try {
			PreparedStatement ps = connection
					.prepareStatement("select AVG(valor) AS media from compra where id DATE(datahora) between DATE(DATE_SUB( ? , INTERVAL ? DAY)) and ?");

			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, 29);

			ps.setDate(3, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			rs.next();
			rd.setMmensal(rs.getFloat("media"));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			rd.setMmensal((float) 0);
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select p.nome, SUM(cp.qtd) as total from compraproduto cp inner join  produto p on (cp.id_produto = p.id) inner join compra c on (cp.id_compra = c.id) where DATE(c.datahora) = ? group by p.id order by cp.qtd desc limit 15");

			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();

			ArrayList<String> al2 = new ArrayList<String>();
			ArrayList<Integer> al3 = new ArrayList<Integer>();

			while (rs.next()) {
				al2.add(new String(rs.getString("p.nome")));
				al3.add(new Integer(rs.getInt("total")));
				System.out.println("chegou aqui" + rs.getInt("total"));
			}
			rd.setNome(al2);
			rd.setQtd(al3);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<Float> al1 = new ArrayList<Float>();
		for (int i = 0; i < 24; i++) {
			Float froat = new Float(0);
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) as total from compra where datahora between ? and ? ");

				String data1 = Conversao.calendarEmTexto(dia);
				String data2 = Conversao.calendarEmTexto(dia);
				if (i <= 9) {
					data1 = data1 + (" 0" + i + ":00:00");
					data2 = data2 + (" 0" + i + ":59:59");
				} else {
					data1 = data1 + (" " + i + ":00:00");
					data2 = data2 + (" " + i + ":59:59");
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

				ps.setTimestamp(1, Conversao.dateEmTimestamp(c1.getTime()));
				ps.setTimestamp(2, Conversao.dateEmTimestamp(c2.getTime()));

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getFloat("total") > 0) {
						froat = rs.getFloat("total");
						al1.add(froat);

					} else {
						al1.add(froat);

					}
				} else {
					al1.add(froat);

				}
				ps.close(); // connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}
		rd.setVenda(al1);
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
			rd.setAbriu((float) 0);
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
			rd.setFechou((float) 0);
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(valor) as total, metodo from compra where DATE(datahora) = ? group by metodo order by metodo asc");

			ps.setDate(1, new Date(dia.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			rd.setCartao(0);
			rd.setDinheiro(0);
			rd.setPrazo(0);
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rd;

	}

	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

	public RelatorioPeriodo buscaPeriodo(Calendar dia, int cont) {
		RelatorioPeriodo rp = new RelatorioPeriodo();

		ArrayList<String> nomesp = new ArrayList<String>();
		ArrayList<Float> valoresp = new ArrayList<Float>();
		ArrayList<String> nomesf = new ArrayList<String>();
		ArrayList<Float> valores = new ArrayList<Float>();
		ArrayList<Float> valoresf = new ArrayList<Float>();
		ArrayList<Float> qtdsf = new ArrayList<Float>();

		connection = new Conexao().getConnection();
		Calendar dia2 = Calendar.getInstance();
		dia2 = (Calendar) dia.clone();
		Conversao.adicaoCalendar(dia2, "00-01-00");
		for (int i = 0; i <= cont; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) AS total from compra where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(DATE_SUB(?, INTERVAL ? MONTH))");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ps.setDate(3, new Date(dia2.getTimeInMillis()));
				ps.setInt(4, i);
				ResultSet rs = ps.executeQuery();
				rs.next();
				System.out.println("valor " + rs.getFloat("total"));
				valores.add(new Float(rs.getFloat("total")));
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				valores.add(new Float(0));
			}
		}
		rp.setValor(valores);

		for (int i = 0; i <= cont; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(valor) AS total from pedido where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(DATE_SUB(?, INTERVAL ? MONTH))");
				ps.setDate(1, new Date(dia.getTimeInMillis()));
				ps.setInt(2, i);
				ps.setDate(3, new Date(dia2.getTimeInMillis()));
				ps.setInt(4, i);
				ResultSet rs = ps.executeQuery();
				rs.next();
				valoresf.add(rs.getFloat("total"));
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		rp.setValorf(valoresf);

		try {
			PreparedStatement ps = connection
					.prepareStatement("select p.nome, SUM(cp.valor) AS total from compraproduto cp inner join  compra co on (cp.id_compra = co.id) inner join produto p on (cp.id_produto = p.id) where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?) group by cp.id_produto order by total ASC LIMIT 15");
			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, cont);
			Conversao.adicaoCalendar(dia2, "00-01-00");
			ps.setDate(3, new Date(dia2.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nomesp.add(rs.getString("p.nome"));
				valoresp.add(rs.getFloat("total"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		rp.setNomep(nomesp);
		rp.setValorp(valoresp);

		try {
			PreparedStatement ps = connection
					.prepareStatement("select f.nome, SUM(p.valor) AS total from pedido p inner join fornecedor f on (p.fornecedor = f.id) where DATE(datahora) >= DATE(DATE_SUB(?, INTERVAL ? MONTH)) and DATE(datahora) < DATE(?) group by f.id LIMIT 15");
			ps.setDate(1, new Date(dia.getTimeInMillis()));
			ps.setInt(2, cont);
			Conversao.adicaoCalendar(dia2, "00-01-00");
			ps.setDate(3, new Date(dia2.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nomesf.add(rs.getString("f.nome"));
				qtdsf.add(new Float(rs.getFloat("total")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		rp.setNomef(nomesf);
		rp.setQtdf(qtdsf);

		return rp;
	}

	public RelatorioProduto buscaProduto(Calendar diaIni, Calendar diaFim,
			int cont, long id) {
		connection = new Conexao().getConnection();

		diaFim.set(Calendar.DAY_OF_MONTH,
				diaFim.getActualMaximum(Calendar.DAY_OF_MONTH));

		RelatorioProduto rp = new RelatorioProduto();

		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(p.id) as qtd, m.nome as motivos from perda p inner join motivo m on (m.id = p.motivo) where p.produto = ? and DATE(datahora) >= DATE(?) and DATE(datahora) <= DATE(?) group by p.motivo");
			ps.setLong(1, id);
			ps.setDate(2, new Date(diaIni.getTimeInMillis()));
			ps.setDate(3, new Date(diaFim.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rp.getLista_motivo().add(rs.getString("motivos"));
				rp.getLista_qtdMotivo().add(rs.getInt("qtd"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Calendar novoDiaIni = (Calendar) diaIni.clone();
		for (int i = 0; i <= cont; i++) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select SUM(cp.valor) as total from compra c inner join compraproduto cp on (c.id = cp.id_compra) where cp.id_produto = ? and DATE(datahora) >= DATE(?) and DATE(datahora) <= DATE(?)");
				ps.setLong(1, id);
				ps.setDate(2, new Date(diaIni.getTimeInMillis()));
				diaIni.set(Calendar.DAY_OF_MONTH,
						diaIni.getActualMaximum(Calendar.DAY_OF_MONTH));
				ps.setDate(3, new Date(diaIni.getTimeInMillis()));

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					rp.getLista_valor().add(rs.getFloat("total"));
					rp.getLista_mes().add(diaIni);
				}
				ps.close();
				Conversao.adicaoCalendar(diaIni, "00-01-00");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(cp.valor) as total from compra c inner join compraproduto cp on (c.id = cp.id_compra) where cp.id_produto = ? and DATE(datahora) >= DATE(?) and DATE(datahora) <= DATE(?)");
			ps.setLong(1, id);
			ps.setDate(2, new Date(novoDiaIni.getTimeInMillis()));
			ps.setDate(3, new Date(diaFim.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			rs.next();
			rp.setVendat(rs.getFloat("total"));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(pp.valor) as total from pedido c inner join produtopedido pp on (p.id = pp.id_pedido) where pp.id_produto = ? and DATE(datahora) >= DATE(?) and DATE(datahora) <= DATE(?)");
			ps.setLong(1, id);
			ps.setDate(2, new Date(novoDiaIni.getTimeInMillis()));
			ps.setDate(3, new Date(diaFim.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			rs.next();
			rp.setInvest(rs.getFloat("total"));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(valor) as total from perda where produto = ? and DATE(datahora) >= DATE(?) and DATE(datahora) <= DATE(?)");
			ps.setLong(1, id);
			ps.setDate(2, new Date(novoDiaIni.getTimeInMillis()));
			ps.setDate(3, new Date(diaFim.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			rs.next();
			rp.setPerdat(rs.getFloat("total"));
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rp;
	}

	public RelatorioPerda buscaPerda(Calendar c1, Calendar c2) {

		RelatorioPerda rp = new RelatorioPerda();

		connection = new Conexao().getConnection();

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(pe.qtd) as total, pr.nome from perda pe inner join produto pr on (perda pe = pr.id) where MONTH(datahora) >= MONTH(?) and MONTH(datahora) <= MONTH(?) order by total DESC limit 15");
			ps.setDate(2, new Date(c1.getTimeInMillis()));
			ps.setDate(3, new Date(c2.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rp.getLista_nome().add(rs.getString("pr.nome"));
				rp.getLista_qtd().add(rs.getInt(rs.getInt("total")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(qtd) as total, motivo from perda where MONTH(datahora) >= MONTH(?) and MONTH(datahora) <= MONTH(?) group by motivo");
			ps.setDate(2, new Date(c1.getTimeInMillis()));
			ps.setDate(3, new Date(c2.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rp.getLista_motivo().add(rs.getString("motivo"));
				rp.getLista_qtdMotivo().add(rs.getInt("total"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = connection
					.prepareStatement("select SUM(valor) as total, datahora from perda where MONTH(datahora) >= MONTH(?) and MONTH(datahora) <= MONTH(?) group by MONTH(datahora)");
			ps.setDate(2, new Date(c1.getTimeInMillis()));
			ps.setDate(3, new Date(c2.getTimeInMillis()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("datahora"));
				rp.getLista_mes().add(calendar);

				rp.getLista_valor().add(rs.getFloat("total"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rp;
	}

}