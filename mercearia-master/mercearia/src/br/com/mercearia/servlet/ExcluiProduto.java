package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoDAO;

@SuppressWarnings("serial")
public class ExcluiProduto extends HttpServlet {
	ProdutoDAO pdao = new ProdutoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		long id = Long.parseLong(request.getParameter("id"));
		if (pdao.exclui(id))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}	
	}
}
