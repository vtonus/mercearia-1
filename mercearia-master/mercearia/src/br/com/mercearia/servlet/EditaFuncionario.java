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
public class EditaFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Funcionario f = new Funcionario();
		f.setCpf(request.getParameter("cpf"));
		f.setNome(request.getParameter("nome"));
		f.setUsuario(request.getParameter("usuario"));
		f.setSenha(request.getParameter("senha"));
		try{
			f.setTelefone(Long.parseLong(request.getParameter("telefone")));
		}catch(NullPointerException e){f.setTelefone(0);}
		f.setEmail(request.getParameter("email"));
		
		try{
			f.setDataNascimento(Conversao.textoEmData(request.getParameter("dataNascimento")));
		}catch (NullPointerException e){}
		catch(ParseException e){response.getWriter().write(" data de nascimento inválida.");}
		
		if(fdao.edita(f))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
