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

import br.com.mercearia.modelo.Produto;

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
			Calendar calendar = Calendar.getInstance();
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setQtd(rs.getInt("qtd"));
				try {
					calendar.setTime(rs.getDate("validade"));
					produto.setValidade(calendar);
				} catch (SQLException | RuntimeException e) {
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

	public List<Produto> procuraNomeProduto(String nome) {
		connection = new Conexao().getConnection();

		String sql = "select * from produto where nome like ?";

		try {
			List<Produto> produtos = new ArrayList<Produto>();
			Produto produto = new Produto();
			PreparedStatement ps = connection.prepareStatement(sql);
			nome = ("%"+nome+"%");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getFloat("valor"));
				produto.setId(rs.getLong("id"));
				produtos.add(produto);
			}
			ps.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> buscaCodigoProduto(Long codigo) {
		connection = new Conexao().getConnection();

		String sql = "select nome, valor from produto where id like ?";

		try {
			List<Produto> produtos = new ArrayList<Produto>();
			Produto produto = new Produto();
			PreparedStatement ps = connection.prepareStatement(sql);
			String cod = ("%"+codigo+"%");
			ps.setString(1, cod);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getFloat("valor"));
				produtos.add(produto);
			}
			ps.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Produto> busca(Produto produto) 
	{
		connection = new Conexao().getConnection();
		Boolean[] boo = new Boolean[9];
		  for (int j = 0; j < 9; j++) 
		{
			boo[j] = false;
		}
		
		int i = 0;
		String sql = "select * from produto ";

		try 
		{
			if (produto.getId() > 0) 
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
			if (produto.getNome().trim().length() > 0) 
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
				if (produto.getValorMin() > 0) {
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
				if (produto.getValorMax() > 0) {
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

			if (produto.getFabricante().trim().length() > 0) {
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
				calendar = Calendar.getInstance();
				calendar = produto.getValidade();
				boo[5] = true;
				if (i == 0) {
					sql = sql.concat("where ");
				} else {
					sql = sql.concat("and ");
				}
				sql = sql.concat("validade < ?");
			} catch (NullPointerException e) {}

						try {
							
				PreparedStatement ps = connection.prepareStatement(sql);
				i = 1;
				if (boo[0]) {
					ps.setLong(i, produto.getId());
					i++;
				}
				if (boo[1]) {
					ps.setString(i, "%"+produto.getNome()+"%");
					i++;
				}
				if (boo[2]) {
					ps.setFloat(i, produto.getValorMin());
					i++;
				}
				if (boo[3]) {
					ps.setFloat(i, produto.getValorMax());
					i++;
				}
				if (boo[4]) {
					ps.setString(i, "%"+produto.getFabricante()+"%");
					i++;
				}
				if (boo[5]) {
					ps.setDate(i, new Date (produto.getValidade().getTimeInMillis()));
					i++;
				}
				ResultSet rs = ps.executeQuery();
				List<Produto> produtos = new ArrayList<Produto>();
				Calendar calendar = Calendar.getInstance();
				while (rs.next()) {
					produto = new Produto();
					produto.setNome(rs.getString("nome"));
					produto.setQtd(rs.getInt("qtd"));
					try {
						calendar.setTime(rs.getDate("validade"));
						produto.setValidade(calendar);
					} catch (SQLException | RuntimeException e) {}
					produto.setValor(rs.getFloat("valor"));
					produto.setId(rs.getLong("id"));
					produto.setFabricante(rs.getString("fabricante"));
					produto.setEstoque(rs.getInt("estoque"));
					produtos.add(produto);
				}
				ps.close();
				connection.close();
				return produtos;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
	

	public boolean adiciona(Produto produto) {
		connection = new Conexao().getConnection();
		//
		String sql = "insert into produto values (?, ?, ?, ?, ?, ?, ?)";

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
				ps.setInt(5, produto.getQtd());
			} catch (SQLException e) {
			}
			try {
				ps.setDate(7, new Date(produto.getValidade().getTimeInMillis()));
			} catch (RuntimeException e) {
				ps.setNull(7, Types.DATE);
			}
			try
			{
				ps.setInt(6, produto.getEstoque());
			}
			catch(SQLException e)
			{
			}
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	public boolean edita(Produto produto) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update produto set id= ?, nome= ?, valor= ?, fabricante= ?, qtd= ?, estoque= ?, validade= ? where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setLong(1, produto.getId());
			ps.setString(2, produto.getNome());
			ps.setFloat(3, produto.getValor());
			ps.setString(4, produto.getFabricante());
			ps.setInt(5, produto.getQtd());
			ps.setInt(6, produto.getEstoque());
			ps.setDate(7, new Date(produto.getValidade().getTimeInMillis()));
			ps.setLong(8, produto.getId());
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}
	public boolean exclui(long id) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "delete from produto where id = ?";

		try { 
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
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