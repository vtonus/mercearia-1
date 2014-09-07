package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovoCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setCpf(request.getParameter("doc"));
		try{
		cliente.setSexoC(request.getParameter("sexo"));
		}catch(RuntimeException e){response.getWriter().write("\nsexo inválido");}
		try {
			cliente.setDataNascimento(Conversao.textoHEmData(request
					.getParameter("dtn")));
		} catch (ParseException e){response.getWriter().write("\ndata de nascimento inválida");} 
		catch (NullPointerException e) {}
		try {
			cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
		} catch (RuntimeException e) {}

		cliente.setEndereco(request.getParameter("endereco"));
		
		ClienteDAO dao = new ClienteDAO();
		if (dao.adiciona(cliente))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}