package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.modelo.Cliente;

@SuppressWarnings("serial")
public class BuscaNomeCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String palavra = request.getParameter("cliente");
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		listaCliente = cdao.busca(palavra, "nome");
		int i = 0;
		for (Cliente c: listaCliente) 
		{
			if (c != null) 
			{
				response.getWriter().write(
						"<input type=\"hidden\" id=\"nome" + i + "\" "
						+ "value=\""+ c.getNome() +"\">"
						+ "<input type=\"hidden\" id=\"cpf" + i + "\" "
						+ "value=\""+ c.getDoc() +"\">"
						+ "<input type=\"hidden\" id=\"dataNascimento" + i + "\" "
						+ "value=\""+ c.getDataNascimento() +"\">");
			}
			i++;
		}
	}
}