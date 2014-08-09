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

	public void adiciona(Cliente cliente) {
		connection = new Conexao().getConnection();
		//
		String sql = "insert into cliente "
				+ "(doc, nome, telefone, sexo, email, dataNascimento)"
				+ " values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, cliente.getDoc());
			ps.setString(2, cliente.getNome());
			ps.setLong(3, cliente.getTelefone());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getEmail());
			ps.setDate(6, new Date(cliente.getDataNascimento()
					.getTimeInMillis()));
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> busca(String palavraChave, String parametro) {
		connection = new Conexao().getConnection();
		int i = 0;
		String sql;
		System.out.println(parametro);
		if (parametro.equals("nome"))
		{
			sql = "select * from cliente where nome like ?";
			i = 1;
			System.out.println("primeira");
		}
		else if (parametro.equals("cpf"))
		{
			sql = "select * from cliente where doc like ?";
			i = 2;
			System.out.println("segunda");
		}
		else
		{
			sql = "select * from cliente where telefone like ?";
			i = 2;			
			System.out.println("terceiraa");
		}
		
		System.out.println(i);

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps = this.connection.prepareStatement(sql);
			/*
			 * switch (i) { case 1: { ps.setString(1, palavraChave); } default:
			 * { Long x = Long.parseLong(palavraChave); ps.setLong(1, x); } }
			 */
			palavraChave = ("%" + palavraChave + "%");
			
			ps.setString(1, palavraChave);
			ResultSet rs = ps.executeQuery();
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			System.out.println("Antes " + sql + palavraChave);
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				System.out.println("La vai " + cliente.getNome());
				cliente.setDoc(rs.getString("doc"));
				cliente.setTelefone(Long.parseLong(rs.getString("telefone")));
				cliente.setSexo(rs.getString("sexo"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				cliente.setDataNascimento(calendar);
				cliente.setEmail(rs.getString("email"));
				listaCliente.add(cliente);
			}
			System.out.println("depois");
			return listaCliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
