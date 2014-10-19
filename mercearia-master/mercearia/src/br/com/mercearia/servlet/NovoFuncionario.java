package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;
import br.com.mercearia.util.ValidaCPF;

@SuppressWarnings("serial")
public class NovoFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Funcionario f = new Funcionario();
		if (request.getParameter("nome").length() > 40 || request.getParameter("nome").length() < 8)
		{
			response.getWriter().write("nome invalido, este deve conter entre 8 e 40 caracteres");
			response.setStatus(501);
			return;
		}
		
		f.setNome(request.getParameter("nome"));
		
		try {
			String cpf = request.getParameter("doc");
			if (ValidaCPF.isCPF(cpf)) {
				f.setCpf(cpf);
			} else {
				response.getWriter().write("CPF invalido, utilize somente numeros");
				response.setStatus(501);
				return;
			}
		} catch (NullPointerException e) {
		}
		
		if (request.getParameter("usuario").length() > 30 || request.getParameter("nome").length() < 4)
		{
			response.getWriter().write("usuario invalido, este deve conter entre 4 e 30 caracteres");
			response.setStatus(501);
			return;
		}
		f.setUsuario(request.getParameter("usuario"));
		
		if (request.getParameter("senha").length() > 12 || request.getParameter("nome").length() < 4)
		{
			response.getWriter().write("senha invalida, este deve conter entre 4 e 12 caracteres");
			response.setStatus(501);
			return;
		}
		
		f.setSenha(request.getParameter("senha"));
		
		try {
			if (request.getParameter("telefone") == ""){
				f.setTelefone(0);
			}
			else f.setTelefone(Long.parseLong(request.getParameter("telefone")));

			
		} catch (NullPointerException e) {f.setTelefone(0);
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
    	f.setEmail(request.getParameter("email"));
		
		try {
			f.setDataNascimento(Conversao.textoHEmData(request
					.getParameter("dtn")));
			System.out.println("Foi....");
		}catch (NullPointerException e) {}
		catch (ParseException e){
			response.getWriter().write("data de nascimento inválida");
			response.setStatus(501);
			return;
		}
		
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		if (fdao.adiciona(f)){
			String data="";
			try{
				Calendar calendar = Calendar.getInstance();
				calendar = f.getDataNascimento();
				data = Conversao.calendarEmTexto(calendar);
			}catch(RuntimeException e){}
			aud.setFunc_id(func_id);
			aud.setDados("cpf: "+f.getCpf()+", nome: "+f.getNome()+", usuario: "+f.getUsuario()+", senha: "+ f.getSenha()+", telefone: "+f.getTelefone()+", dataNascimento: "+data);
			aud.setAcao(0);
			aud.setTabela(7);
			
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}