package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.CompraDAO;

@SuppressWarnings("serial")
public class DeletaCompra extends HttpServlet {
	CompraDAO cdao = new CompraDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		if (cdao.exclui(id))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}