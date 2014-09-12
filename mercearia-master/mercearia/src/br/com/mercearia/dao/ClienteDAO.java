package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mercearia.modelo.Cliente;





public class ClienteDAO {
	private Connection connection;

	public boolean adiciona(Cliente cliente) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "insert into cliente "
				+ "(cpf, nome, telefone, sexo, email, dataNascimento)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setLong(3, cliente.getTelefone());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getEmail());
			try {
			ps.setDate(6, new Date(cliente.getDataNascimento()
					.getTimeInMillis()));
			} catch(RuntimeException e){
				ps.setNull(6, java.sql.Types.DATE);
			}
			ps.setString(7, cliente.getEndereco());
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}

	public List<Cliente> busca(String palavraChave, String parametro) {
		connection = new Conexao().getConnection();
		int i = 0;
		String sql;
		if (parametro.equals("nome")) {
			sql = "select * from cliente where nome like ?";
		} else if (parametro.equals("cpf")) {
			sql = "select * from cliente where cpf like ?";
		} else if (parametro.equals("id")) {
			sql = "select * from cliente where id = ?";
			i = 1;
		} else {
			sql = "select * from cliente where telefone like ?";
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
				} catch (RuntimeException e) {
					throw new RuntimeException(e);
				}
				ps.setInt(1, i);
			}
			ResultSet rs = ps.executeQuery();
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(Integer.parseInt(rs.getString("id")));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				try{
					cliente.setTelefone(Long.parseLong(rs.getString("telefone")));
				}catch(RuntimeException e){}
				try{
				cliente.setSexo(rs.getString("sexo"));
				} catch(RuntimeException e){}
				try{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				cliente.setDataNascimento(calendar);
				} catch(RuntimeException e){}
				cliente.setEmail(rs.getString("email"));
				listaCliente.add(cliente);
			}
			return listaCliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean exclui(int id) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "delete from cliente where id = ?";

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
	
	public boolean edita(Cliente cliente) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update cliente set cpf= ?, nome= ?, telefone= ?, sexo = ?, email= ?, dataNascimento=? where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setLong(3, cliente.getTelefone());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getEmail());
			
			try 
			{
				ps.setDate(6, new Date(cliente.getDataNascimento()
						.getTimeInMillis()));
			} catch(NullPointerException e){}
			
			ps.setInt(7, cliente.getId());
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
