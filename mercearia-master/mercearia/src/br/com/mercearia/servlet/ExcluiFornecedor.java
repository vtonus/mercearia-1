package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FornecedorDAO;
import br.com.mercearia.util.Auditoria;

@SuppressWarnings("serial")
public class ExcluiFornecedor extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FornecedorDAO fdao = new FornecedorDAO();
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		{
			if (fdao.exclui(Integer.parseInt(request.getParameter("id")))) {
				response.setStatus(200);
				aud.setFunc_id(func_id);
				aud.setDados("id: "+Integer.parseInt(request.getParameter("id")));
				aud.setAcao(2);
				aud.setTabela(6);
				aud.adiciona();
			} else {
				response.setStatus(500);
			}
		}
	}
}