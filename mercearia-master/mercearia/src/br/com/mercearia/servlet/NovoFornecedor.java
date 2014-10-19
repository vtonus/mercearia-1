package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FornecedorDAO;
import br.com.mercearia.modelo.Fornecedor;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.ValidaCNPJ;
import br.com.mercearia.util.ValidaCPF;

@SuppressWarnings("serial")
public class NovoFornecedor extends HttpServlet {
	FornecedorDAO fdao = new FornecedorDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Fornecedor f = new Fornecedor();
		
		if (request.getParameter("nome").trim().length() > 3 && request.getParameter("nome").length() < 50){
			f.setNome(request.getParameter("nome"));
		}
		else{
			response.getWriter().write("nome do fornecedor invalido, este deve ter entre 3 e 50 caracteres");
			response.setStatus(501);
			return;
		}
		try {
			String cnpj = request.getParameter("cnpj");
			if (ValidaCNPJ.isCNPJ(cnpj)) {
				f.setCnpj(cnpj);
			} else {
				response.getWriter().write("CNPJ invalido, utilize somente numeros");
				response.setStatus(501);
				return;
			}
		} catch (NullPointerException e) {
		}
		
		try {
			if (request.getParameter("telefone") == ""){
				f.setTelefone(0);
			}
			else f.setTelefone(Long.parseLong(request.getParameter("telefone")));

			
		} catch (NullPointerException e) {
			f.setTelefone(0);
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
			response.getWriter().write("email do fornecedor invalido, este deve estar no formato \"exemplo@exemplo.com\"");
			response.setStatus(501);
			return;
		}
		}
    	f.setEmail(request.getParameter("email"));

    	if (request.getParameter("endereco").length() > 6 && request.getParameter("endereco").length() < 50)
    	{
    		f.setEndereco(request.getParameter("endereco"));
    	}
    	else if (request.getParameter("endereco").isEmpty())
    	{
    		f.setEndereco(request.getParameter("endereco"));
    	}
    	else 
    	{
			response.getWriter().write("endereco do fornecedor invalido, este deve conter entre 6 e 50 caracteres");
			response.setStatus(501);
			return;    		
    	}
    	
		FornecedorDAO fdao = new FornecedorDAO();
		if (fdao.adiciona(f)) {
			aud.setFunc_id(func_id);
			aud.setDados("id: "+ f.getId()+", nome: "+f.getNome()+", cnpj: "+ f.getCnpj()+", endereco: "+f.getEndereco()+", telefone: "+f.getTelefone()+", email: "+f.getEmail());
			aud.setAcao(0);
			aud.setTabela(6);
			aud.adiciona();
			
			response.setStatus(200);
		} else {
			response.setStatus(500);
		}
	}
}