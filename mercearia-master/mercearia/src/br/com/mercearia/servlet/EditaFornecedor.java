package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FornecedorDAO;
import br.com.mercearia.modelo.Fornecedor;

@SuppressWarnings("serial")
public class EditaFornecedor extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		FornecedorDAO fdao = new FornecedorDAO();
		Fornecedor f = new Fornecedor();
		f.setId(Integer.parseInt(request.getParameter("id")));
		f.setNome(request.getParameter("nome"));
		try
		{
		f.setCnpj(Long.parseLong(request.getParameter("cnpj")));
		}catch(NullPointerException e)
		{
			f.setCnpj(0);
		}
		try
		{
		f.setTelefone(Long.parseLong(request.getParameter("telefone")));
		}catch(NullPointerException e)
		{
			f.setTelefone(0);
		}
		f.setEmail(request.getParameter("email"));
		f.setEndereco(request.getParameter("endereco"));
		
		if(fdao.edita(f))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
