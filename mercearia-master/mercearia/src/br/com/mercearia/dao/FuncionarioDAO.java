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

import br.com.mercearia.modelo.Funcionario;

public class FuncionarioDAO {
	String sql;
	PreparedStatement ps;
	private Connection connection;

	public boolean adiciona(Funcionario funcionario) {
		connection = new Conexao().getConnection();
		boolean bool = false;

		sql = "insert into funcionario "
				+ "(cpf, nome, usuario, senha, email, telefone, dataNascimento)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		try {
			ps = connection.prepareStatement(sql);

			ps.setString(1, funcionario.getCpf());
			ps.setString(2, funcionario.getNome());
			ps.setString(3, funcionario.getUsuario());
			ps.setString(4, funcionario.getSenha());
			try {
				ps.setLong(6, funcionario.getTelefone());
			} catch (RuntimeException e) {
			}
			try {
				ps.setLong(5, funcionario.getTelefone());
			} catch (RuntimeException e) {
			}

			ps.setDate(7, new Date(funcionario.getDataNascimento()
					.getTimeInMillis()));
			ps.execute();
			bool = true;
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bool;
	}

	public Funcionario checaLogin(String usuario, String senha) {
		connection = new Conexao().getConnection();
		Funcionario funcionario = new Funcionario();
		sql = "select * from funcionario" + " where usuario=? and senha=?";

		try {
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setNome(rs.getString("nome"));
				ps.close();
				connection.close();
				return funcionario;
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return funcionario;
	}

	public List<Funcionario> getLista() {
		connection = new Conexao().getConnection();
		sql = "select * from funcionario";
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCpf(rs.getString("cpf"));
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(rs.getDate("dataNascimento"));
					funcionario.setDataNascimento(calendar);
				} catch (SQLException e) {
				}
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				try {
					funcionario.setTelefone(rs.getLong("telefone"));
				} catch (SQLException e) {
				}
				listaFuncionario.add(funcionario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaFuncionario;
	}

	public Funcionario busca(String usuario) {
		connection = new Conexao().getConnection();
		sql = "select * from funcionario where usuario = ?";
		Funcionario funcionario = new Funcionario();
		System.out.println("DAO: " + usuario);
		try {
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			rs.next();
			funcionario.setCpf(rs.getString("cpf"));
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				funcionario.setDataNascimento(calendar);
			} catch (SQLException e) {
				System.out.println("Erro na busca da data.");
			}
			funcionario.setNome(rs.getString("nome"));
			System.out.println(funcionario.getCpf());
			funcionario.setUsuario(rs.getString("usuario"));
			try {
				funcionario.setTelefone(rs.getLong("telefone"));
			} catch (SQLException e) {
			}
			return funcionario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	public List<String> buscaNomeFuncionario(String nome) {
		connection = new Conexao().getConnection();

		String sql = "select * from funcionario where nome like ?";

		try {
			List<String> nomes = new ArrayList<String>();
			PreparedStatement ps = connection.prepareStatement(sql);
			nome = ("%" + nome + "%");
			ps.setString(1, nome);
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

	public Funcionario buscaId(String id) {
		connection = new Conexao().getConnection();
		sql = "select * from funcionario where cpf = ?";
		Funcionario f = new Funcionario();
		try {
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				f.setDataNascimento(calendar);
			} catch (SQLException e) {
				System.out.println("Erro na busca da data.");
			}
			f.setNome(rs.getString("nome"));
			f.setUsuario(rs.getString("usuario"));
			try {
				f.setTelefone(rs.getLong("telefone"));
			} catch (SQLException e) {
			}
			f.setCpf(id);
			return f;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean edita(Funcionario f) {
		connection = new Conexao().getConnection();
		boolean bool = false;
		String sql = "update funcionario set nome= ?, telefone= ?, email=?, dataNascimento=?, sexo=? where cpf=?";
		//CORRIGIR....
		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, f.getNome());
			ps.setLong(2, f.getTelefone());
			ps.setString(3, f.getEmail());

			try {
				ps.setDate(4, new Date(f.getDataNascimento().getTimeInMillis()));
			} catch (NullPointerException e) {
				ps.setNull(4, Types.DATE);
			}
			ps.setString(5, f.getCpf());
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
		String sql = "delete from funcionario where id = ?";

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

	public List<Funcionario> busca(String palavraChave, String parametro) {
		connection = new Conexao().getConnection();
		int i = 0;
		String sql;
		long telefone = 0;
		if (parametro.equals("nome")) {
			sql = "select * from funcionario where nome like ?";
		} else if (parametro.equals("cpf")) {
			sql = "select * from funcionario where cpf like ?";
		} else if (parametro.equals("email")) {
			sql = "select * from funcionario where email like ?";
		} else if (parametro.equals("telefone")) {
			sql = "select * from funcionario where telefone = ?";
			i = 1;

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
					telefone = Long.parseLong(palavraChave);
				} catch (RuntimeException e) {
					return null;
				}
				ps.setLong(1, telefone);
			}
			ResultSet rs = ps.executeQuery();
			List<Funcionario> listaF = new ArrayList<Funcionario>();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setCpf(rs.getString("cpf"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(Long.parseLong(rs.getString("telefone")));
				f.setEmail(rs.getString("email"));
				System.out.println("Dataa...."+rs.getDate("dataNascimento")+"Nome....."+rs.getString("nome"));

				try{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataNascimento"));
				f.setDataNascimento(calendar);
				} catch (SQLException e) {
				}

				listaF.add(f);
			}
			return listaF;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}