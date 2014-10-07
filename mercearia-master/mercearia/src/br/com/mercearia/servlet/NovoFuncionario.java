package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

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
public class NovoFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

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
			String data="";
			try{
				Calendar calendar = Calendar.getInstance();
				calendar = f.getDataNascimento();
				data = Conversao.calendarEmTexto(calendar);
			}catch(RuntimeException e){}
			aud.setFunc_id(func_id);
			aud.setDados("cpf: "+f.getCpf()+", nome: "+f.getNome()+", usuario: "+f.getUsuario()+", senha: "+ f.getSenha()+", telefone: "+f.getTelefone()+", dataNascimento: "+data);
			aud.setAcao(0);
			aud.setTabela(7);
			
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}