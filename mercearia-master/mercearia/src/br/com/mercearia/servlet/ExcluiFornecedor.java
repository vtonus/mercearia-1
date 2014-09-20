package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FornecedorDAO;

@SuppressWarnings("serial")
public class ExcluiFornecedor extends HttpServlet {
	FornecedorDAO fdao = new FornecedorDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		if (fdao.exclui(Integer.parseInt(request.getParameter("id"))))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
