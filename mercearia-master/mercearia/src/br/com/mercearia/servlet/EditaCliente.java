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
		cliente.setEndereco(request.getParameter("end"));
		cliente.setSexoC(request.getParameter("sexo"));
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
