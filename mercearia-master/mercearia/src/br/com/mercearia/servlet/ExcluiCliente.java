package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;

@SuppressWarnings("serial")
public class ExcluiCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		if (cdao.exclui(id))
		{
			response.setStatus(200);
			System.out.println("Opera��o exclui cliente realizada com sucesso.");
		}
		else
		{
			response.setStatus(500);
		}	
	}
}
