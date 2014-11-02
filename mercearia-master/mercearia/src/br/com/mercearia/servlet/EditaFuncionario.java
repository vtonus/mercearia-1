package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class EditaFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

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
			f.setDataNascimento(Conversao.textoHEmData(request.getParameter("dataDeNascimento")));
		}catch (NullPointerException e){}
		catch(ParseException e){response.getWriter().write(" data de nascimento inválida.");}
		
		if(fdao.edita(f))
		{
			response.setStatus(200);
			aud.setFunc_id(func_id);
			aud.setDados("cpf: "+f.getCpf()+", nome: "+f.getNome()+", usuario: "+f.getUsuario()+", senha: "+f.getSenha()+", telefone: "+f.getTelefone()+", dataNascimento: "+Conversao.calendarEmTexto(f.getDataNascimento())+", email: "+f.getEmail());
			aud.setAcao(1);
			aud.setTabela(7);
			aud.adiciona();
		}
		else
		{
			response.setStatus(500);
		}
	}
}
