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

@SuppressWarnings("serial")
public class NovoFornecedor extends HttpServlet {
	FornecedorDAO fdao = new FornecedorDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Fornecedor f = new Fornecedor();
		f.setNome(request.getParameter("nome"));
		try {
			f.setCnpj(Long.parseLong(request.getParameter("CNPJ")));
		} catch (NullPointerException | NumberFormatException e) {
			f.setCnpj(0);
		}

		try {
			f.setTelefone(Long.parseLong(request.getParameter("telefone")));
		} catch (NullPointerException | NumberFormatException e) {
			f.setTelefone(0);
		}
		
		f.setEmail(request.getParameter("email"));
		f.setEndereco(request.getParameter("endereco"));
		
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