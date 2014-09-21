package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovoFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Funcionario f = new Funcionario();
		f.setNome(request.getParameter("nome"));
		f.setCpf(request.getParameter("cpf"));
		f.setUsuario(request.getParameter("usuario"));
		f.setSenha(request.getParameter("senha"));
		try {
			f.setTelefone(Long.parseLong(request.getParameter("telefone")));
		} catch (RuntimeException e) {f.setTelefone(0);}
		f.setEmail(request.getParameter("email"));
		try {
			f.setDataNascimento(Conversao.textoHEmData(request
					.getParameter("dataNascimento")));
		} catch (ParseException e){response.getWriter().write("\ndata de nascimento inválida");} 
		catch (NullPointerException e) {}
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		if (fdao.adiciona(f))		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}