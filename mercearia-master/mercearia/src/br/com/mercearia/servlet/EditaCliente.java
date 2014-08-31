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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		cliente.setNome(request.getParameter("nome"));
		cliente.setDoc(request.getParameter("cpf"));
		cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
		cliente.setEmail(request.getParameter("email"));
		cliente.setSexo(request.getParameter("sexo"));
		System.out.println("id "+cliente.getId()+"\nnome "+cliente.getNome()+"\ncpf "+cliente.getDoc()+"\ntelefone "+cliente.getTelefone()+"\nemail "+cliente.getEmail()+"\nsexo "+cliente.getSexo());
		
		try{
			cliente.setDataNascimento(Conversao.textoEmData(request.getParameter("dataDeNascimento")));
		}catch (ParseException | NullPointerException e){};
		
		if(cdao.edita(cliente))
		{
			response.setStatus(200);
			System.out.println("Operação edita cliente realizada com sucesso.");
		}
		else
		{
			response.setStatus(500);
		}
	}
}
