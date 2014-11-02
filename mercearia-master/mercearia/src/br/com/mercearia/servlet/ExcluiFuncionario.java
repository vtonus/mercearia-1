package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.util.Auditoria;

@SuppressWarnings("serial")
public class ExcluiFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		int id = Integer.parseInt(request.getParameter("id"));
		if (fdao.exclui(id))
		{
			aud.setFunc_id(func_id);
			aud.setDados("id: "+id);
			aud.setAcao(2);
			aud.setTabela(7);
			aud.adiciona();
			
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
