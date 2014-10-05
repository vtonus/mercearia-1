package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.CompraDAO;
import br.com.mercearia.util.Auditoria;

@SuppressWarnings("serial")
public class DeletaCompra extends HttpServlet {
	CompraDAO cdao = new CompraDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String funcId = (String) session.getAttribute("usuarioCpf");
		int id = Integer.parseInt(request.getParameter("id"));
		if (cdao.exclui(id))
		{
			Auditoria aud = new Auditoria();
			aud.setAcao(2);
			aud.setFunc_id(funcId);
			aud.setDados("id: "+id);
			aud.setTabela(3);
			aud.adiciona();
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
