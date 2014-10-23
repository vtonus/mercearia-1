package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

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
public class EditaCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();
	public String error;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("nome").trim().length() > 5 && request.getParameter("nome").length() < 40){
			cliente.setNome(request.getParameter("nome"));
		}
		else{
			response.getWriter().write("nome do cliente invalido, este deve ter entre 5 e 40 caracteres");
			response.setStatus(501);
			return;
		}

		try {
			String cpf = request.getParameter("cpf");
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

		if ((request.getParameter("end").trim().length() > 5 && request.getParameter("end").length() < 60) || request.getParameter("end").isEmpty()){
			cliente.setEndereco(request.getParameter("end"));
		}
		else{
			response.getWriter().write("endereco do cliente invalido, este deve ter entre 5 e 60 caracteres");
			response.setStatus(501);
			return;
		}
				try {
			cliente.setSexoC(request.getParameter("sexo"));
		} catch (RuntimeException e) {
			response.getWriter().write("\nsexo inválido");
			response.setStatus(501);
			return;

		}

		HttpSession session = request.getSession();
		String funcId = (String) session.getAttribute("usuarioCpf");
		
		try{
			cliente.setDataNascimento(Conversao.textoHEmData(request.getParameter("dataDeNascimento")));
		}catch (NullPointerException e){}
		catch(ParseException e){}
		
		if(cdao.edita(cliente))
		{
			response.setStatus(200);
			Auditoria aud = new Auditoria();
			aud.setFunc_id(funcId);
			aud.setDados("id: "+cliente.getId()+", nome: "+cliente.getNome()+", cpf: "+cliente.getCpf()+", telefone: "+cliente.getTelefone()+", email: "+cliente.getEmail()+", endereco: "+cliente.getEndereco()+", sexo: "+cliente.getSexoC()+", dataNascimento: "+Conversao.calendarEmTexto(cliente.getDataNascimento()));
			aud.setAcao(1);
			aud.setTabela(2);
			aud.adiciona();
		}
		else
		{
			response.setStatus(500);
		}
	}
}
