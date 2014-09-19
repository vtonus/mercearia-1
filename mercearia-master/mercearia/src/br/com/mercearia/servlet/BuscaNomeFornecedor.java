package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.FornecedorDAO;
import br.com.mercearia.modelo.Fornecedor;

@SuppressWarnings("serial")
public class BuscaNomeFornecedor extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	//testando
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String palavra = request.getParameter("nome");
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		FornecedorDAO fdao = new FornecedorDAO();
		listaFornecedor = fdao.busca(palavra);
		int i = 0;
		for (Fornecedor f: listaFornecedor) 
		{
			if (f != null) 
			{
				response.getWriter().write(
						"<input type=\"hidden\" id=\"nome" + i + "\" "
						+ "value=\""+ f.getId() +"\">"
						+ "<input type=\"hidden\" id=\"cpf" + i + "\" "
						+ "value=\""+ f.getNome() +"\">");
			}
			i++;
		}
	}
}