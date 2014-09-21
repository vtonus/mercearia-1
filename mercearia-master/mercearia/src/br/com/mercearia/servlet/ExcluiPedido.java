package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.PedidoDAO;

@SuppressWarnings("serial")
public class ExcluiPedido extends HttpServlet {
	PedidoDAO pedao = new PedidoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		if (pedao.exclui(id))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
