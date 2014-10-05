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
public class EditaFornecedor extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();				
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");
		
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
			aud.setFunc_id(func_id);
			aud.setDados("id: "+f.getId()+", nome: "+f.getNome()+", cnpj: "+f.getCnpj()+", endereco: "+f.getEndereco()+", telefone: "+f.getTelefone()+", email: "+f.getEmail());
			aud.setAcao(1);
			aud.setTabela(6);
			aud.adiciona();
		}
		else
		{
			response.setStatus(500);
		}
	}
}
