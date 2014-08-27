package br.com.mercearia.logic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.util.Conversao;

public class NovoClienteLogic implements Logic {
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		//Conversao.teste(request.getParameter("dataNascimento"));
		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setEmail(request.getParameter("email"));
		String cpf ="";
		if (request.getParameter("doc") != null
				&& request.getParameter("doc") != "") {
			cpf = request.getParameter("doc");
		}
		long telefone = 0;
		if (request.getParameter("telefone") != null
				&& request.getParameter("telefone") != "") {
			telefone = Long.parseLong(request.getParameter("telefone"));
		}
		String sexo = request.getParameter("sexo");

		// if (request.getParameter("dataNascimento") != null){
		cliente.setDataNascimento(Conversao.textoEmData(request
					.getParameter("dataNascimento")));
		cliente.setSexo(sexo);
		cliente.setDoc(cpf);
		cliente.setTelefone(telefone);

		ClienteDAO dao = new ClienteDAO();
		dao.adiciona(cliente);
		request.setAttribute("cliente", "cliente");
		RequestDispatcher rd = request
				.getRequestDispatcher("NovoCliente.jsp");
		rd.forward(request, response);
	}
}