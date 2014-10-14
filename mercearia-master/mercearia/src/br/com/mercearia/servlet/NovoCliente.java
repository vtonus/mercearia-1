package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;
import br.com.mercearia.util.ValidaCPF;

@SuppressWarnings("serial")
public class NovoCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Cliente cliente = new Cliente();
		if (request.getParameter("nome").trim().length() > 5 && request.getParameter("nome").length() < 40){
			cliente.setNome(request.getParameter("nome"));
		}
		else{
			response.getWriter().write("nome do cliente invalido, este deve ter entre 5 e 40 caracteres");
			response.setStatus(501);
			return;
		}
		
		String email = request.getParameter("email");
		if (!email.isEmpty()){
		if (email.length() > 50 || (email.length() < 10)){
			response.getWriter().write("email invalido, este deve conter entre 10 e 50 caracteres.");
			response.setStatus(501);
			return;			
		}
		boolean bool = false; 
		for( int i=0; i<email.length(); i++ ) {
		    if( email.charAt(i) == '@' ) {
		    	bool = true;
		    	break;
		    } 
		}
		if (!bool){
			response.getWriter().write("email invalido, este deve estar no formato \"exemplo@exemplo.com\"");
			response.setStatus(501);
			return;
		}
		}
    	cliente.setEmail(request.getParameter("email"));
		
		
		
		try {
			String cpf = request.getParameter("doc");
			if (ValidaCPF.isCPF(cpf) || cpf.isEmpty()) {
				cliente.setCpf(cpf);
			} else {
				response.getWriter().write("CPF invalido, utilize somente numeros");
				response.setStatus(501);
				return;
			}
		} catch (NullPointerException e) {
		}

		try {
			cliente.setSexoC(request.getParameter("sexo"));
		} catch (RuntimeException e) {
			response.getWriter().write("\nsexo inválido");
		}
		try {
			cliente.setDataNascimento(Conversao.textoHEmData(request
					.getParameter("dtn")));
		} catch (ParseException e) {
			response.getWriter().write("data de nascimento inválida");
		} catch (NullPointerException e) {
		}
		try {
			if (request.getParameter("telefone") == ""){
				cliente.setTelefone(0);
			}
			else cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
			
			
		} catch (NullPointerException e) {cliente.setTelefone(0);
		} catch (NumberFormatException e) {
			response.getWriter().write("telefone do cliente invalido, este deve conter somente com numeros");
			response.setStatus(501);
			return;
		}
		
		if ((request.getParameter("endereco").trim().length() > 5 && request.getParameter("nome").length() < 60) || request.getParameter("endereco").isEmpty()){
			cliente.setEndereco(request.getParameter("endereco"));
		}
		else{
			response.getWriter().write("endereco do cliente invalido, este deve ter entre 5 e 60 caracteres");
			response.setStatus(501);
			return;
		}
		
		ClienteDAO dao = new ClienteDAO();
		int j = dao.adiciona(cliente);
		if (j > 0) {
			Calendar calendar = Calendar.getInstance();
			String data = "";
			try {
				calendar = cliente.getDataNascimento();
				data = Conversao.calendarEmTexto(calendar);
			} catch (RuntimeException e) {
			}
			aud.setFunc_id(func_id);
			aud.setDados("id: " + j + ", cpf: " + cliente.getCpf()
					+ ", nome: " + cliente.getNome() + ", telefone: "
					+ cliente.getTelefone() + ", endereco: "
					+ cliente.getEndereco() + ", sexo: " + cliente.getSexoC()
					+ ", email: " + cliente.getEmail() + ", dataNascimento: "
					+ data);
			aud.setAcao(0);
			aud.setTabela(2);
			aud.adiciona();
			response.setStatus(200);
		} else {
			response.setStatus(500);
		}
	}
}