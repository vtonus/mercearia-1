package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.faces.NovoProdutoBean;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

public class ProdutoDAO {
	private Connection connection;
	@SuppressWarnings("unused")
	private Calendar calendar;
	boolean bool = false;

	public Produto busca(long id) {
		connection = new Conexao().getConnection();

		String sql = "select * from produto where id = ?";

		try {
			Produto produto = new Produto();
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(" id do produto buscado eh " + id);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setQtd(rs.getInt("qtd"));
				try {
					produto.setValidade(rs.getDate("val_max"));
				} catch (SQLException e) {
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

		try {
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

		try {
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

	public List<Produto> procura(NovoProdutoBean npd) 
	{
		connection = new Conexao().getConnection();
		Boolean[] boo = new Boolean[9];
		for (int j = 0; j < 9; j++) 
		{
			boo[j] = false;
		}
		// codigo, nome, valormin, valormax,fabricante, qtdmin, qtdmax,
		// validademin, validademax

		int i = 0;
		String sql = "select * from produto ";

		try 
		{
			if (npd.getId() > 0) 
			{
				boo[0] = true;
				sql = sql.concat("where id = ? ");
				i = 1;
			}
		} 
		catch (NullPointerException e) 
		{
		}

		try
		{
			if (npd.getCompletandoN().trim().length() > 0) 
			{
			
				boo[1] = true;
				if (i == 0) 
				{
					sql = sql.concat("where ");
					i = 1;
				}
				else 
				{
					sql = sql.concat("and ");
				}
				sql = sql.concat("nome like ? ");
			}
		}
		catch(NullPointerException e){}

		try {
				if (npd.getValorMin() > 0) {
					boo[2] = true;
					if (i == 0) {
						sql = sql.concat("where ");
						i = 1;
					} else {
						sql = sql.concat("and ");
					}
					sql = sql.concat("valor >= ? ");
				}
			} catch (NullPointerException e) {}

			try {
				if (npd.getValorMax() > 0) {
					boo[3] = true;
					if (i == 0) {
						sql = sql.concat("where ");
						i = 1;
					} else {
						sql = sql.concat("and ");
					}
					sql = sql.concat("valor <= ? ");
				}
			} catch (NullPointerException e) {
			}

			if (npd.getCompletandoF().trim().length() > 0) {
				if (i == 0) {
					boo[4] = true;
					sql = sql.concat("where ");
					i = 1;
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("fabricante like ? ");
			}
			try {
				if (npd.getQuantidadeMin() > 0) {
					boo[5] = true;
					if (i == 0) {
						sql = sql.concat("where ");
						i = 1;
					} else {
						sql = sql.concat("and ");
					}
					sql = sql.concat("qtd >= ? ");
				}
			} catch (NullPointerException e) {
			}
			try {
				if (npd.getQuantidadeMax() > 0) {
					boo[6] = true;
					if (i == 0) {
						sql = sql.concat("where ");
						i = 1;
					} else {
						sql = sql.concat("and ");
					}
					sql = sql.concat("qtd <= ? ");
				}
			} catch (NullPointerException e) {
			}

			try {
				calendar = Calendar.getInstance();
				calendar = Conversao.dateEmCalendar(npd.getValidadeMin());
				boo[7] = true;
				if (i == 0) {
					sql = sql.concat("where ");
					i = 2;
				} else {
					sql = sql.concat("and ");
					i = 2;
				}
			} catch (ParseException | NullPointerException e) {}

			try {
				calendar = Calendar.getInstance();
				calendar = Conversao.dateEmCalendar(npd.getValidadeMin());
				boo[8] = true;
				if (i == 0) {
					sql = sql.concat("where validade <= ?");
					i = 3;
				}

				else if (i == 1) {
					sql = sql.concat("and validade <= ?");
					i = 3;
				} else {
					sql = sql.concat("validade between ? and ?");
					i = 3;
				}
			} catch (ParseException | NullPointerException e) {}
			if (i == 2) {
				sql = sql.concat("validade >= ?");
			}
			System.out.println(sql);
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				i = 1;
				if (boo[0]) {
					ps.setLong(i, npd.getId());
					i++;
				}
				if (boo[1]) {
					ps.setString(i, "%"+npd.getNome()+"%");
					i++;
				}
				if (boo[2]) {
					ps.setFloat(i, npd.getValorMin());
					i++;
				}
				if (boo[3]) {
					ps.setFloat(i, npd.getValorMax());
					i++;
				}
				if (boo[4]) {
					ps.setString(i, "%"+npd.getFabricante()+"%");
					i++;
				}
				if (boo[5]) {
					ps.setInt(i, npd.getQuantidadeMin());
					i++;
				}
				if (boo[6]) {
					ps.setInt(i, npd.getQuantidadeMax());
					i++;
				}
				if (boo[7]) {
					ps.setDate(i, new Date(npd.getValidadeMin().getTime()));
					i++;
				}
				if (boo[8]) {
					ps.setDate(i, new Date(npd.getValidadeMax().getTime()));
				}
				System.out.println(sql);
				ResultSet rs = ps.executeQuery();
				List<Produto> produtos = new ArrayList<Produto>();
				Produto produto = new Produto();
				while (rs.next()) {
					produto.setNome(rs.getString("nome"));
					produto.setQtd(rs.getInt("qtd"));
					try {
						produto.setValidade(rs.getDate("validade"));
					} catch (SQLException e) {}
					
					produto.setValor(rs.getFloat("valor"));
					produto.setId(rs.getLong("id"));
					produto.setFabricante(rs.getString("fabricante"));
					System.out.println(produto.getNome());
					produtos.add(produto);
					produto = new Produto();
				}
				ps.close();
				connection.close();
				return produtos;
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