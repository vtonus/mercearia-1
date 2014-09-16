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
public class EditaCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();
	public String error;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(request.getParameter("cpf"));
		try{
			cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
		}catch(NullPointerException | NumberFormatException e){cliente.setTelefone(0);}
		cliente.setEmail(request.getParameter("email"));
		cliente.setEndereco(request.getParameter("endereco"));
		cliente.setSexoC(request.getParameter("sexo"));

		
		try{
			cliente.setDataNascimento(Conversao.textoEmData(request.getParameter("dataDeNascimento")));
		}catch (NullPointerException e){}
		catch(ParseException e){}
		System.out.println("id "+cliente.getId()+"\nnome "+cliente.getNome()+"\ncpf "+cliente.getCpf()+"\ntelefone "+cliente.getTelefone()+"\nemail "+cliente.getEmail()+"\nsexo "+cliente.getSexo()+"\nData de nasc: "+cliente.getDataNascimento());
		if(cdao.edita(cliente))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
