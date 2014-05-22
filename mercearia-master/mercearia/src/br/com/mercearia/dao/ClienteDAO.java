package br.com.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

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

			ps.setLong(1, cliente.getDoc());
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

	public Cliente busca(String palavraChave, String parametro) {
		connection = new Conexao().getConnection();

		String sql = "select * from cliente where nome = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps = this.connection.prepareStatement(sql);
			//ps.setString(1, parametro);
			ps.setString(1, palavraChave);
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Cliente cliente = new Cliente();
			System.out.println("Parametro: "+ parametro + " - PC: "+palavraChave);
			//cliente.setId(rs.getInt("id_cliente"));
			cliente.setNome(rs.getString("nome"));
			cliente.setDoc(rs.getLong("doc"));
			cliente.setTelefone(rs.getInt("telefone"));
			cliente.setSexo(rs.getString("sexo"));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(rs.getDate("dataNascimento"));
			cliente.setDataNascimento(calendar);
			cliente.setEmail(rs.getString("email"));
			System.out.println("Parametro: "+ parametro + " - PC: "+palavraChave+" -  Resultado: "+ cliente.getNome());
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
