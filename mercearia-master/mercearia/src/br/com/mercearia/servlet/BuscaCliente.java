package br.com.mercearia.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.Conexao;
import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class BuscaCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//String tipo = request.getParameter("tipo");
		String palavraChave = request.getParameter("palavraChave");
		String parametro = request.getParameter("parametro");
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		listaCliente = cdao.busca(palavraChave, parametro);
		int i = 0;
		String dataNascimento;
		for (Cliente cliente : listaCliente) {
			dataNascimento = "";
			
			try{
				dataNascimento = Conversao.calendarEmTexto(cliente
					.getDataNascimento());
			}catch(RuntimeException e){}
			String telefone;
			if (cliente.getTelefone()==0){
				telefone ="";
			}
			else telefone=""+cliente.getTelefone();
			if (cliente != null) {
				response.getWriter().write(
								  "<input type=\"hidden\" id=\"id" + i
								+ "\" value=\"" + cliente.getId() + "\">"
								+ "<input type=\"hidden\" id=\"cpf" + i
								+ "\" value=\"" + cliente.getCpf() + "\">"
								+ "<input type=\"hidden\" id=\"nome" + i 
								+ "\" value=\"" + cliente.getNome() + "\">"
								+ "<input type=\"hidden\" id=\"telefone" + i
								+ "\" value=\"" + telefone + "\">"
								+ "<input type=\"hidden\" id=\"email" + i
								+ "\" value=\"" + cliente.getEmail() + "\">"
								+ "<input type=\"hidden\" id=\"sexo" + i
								+ "\" value=\"" + cliente.getSexoC() + "\">"
								+ "<input type=\"hidden\" id=\"dataNascimento" + i
								+ "\" value=\"" + dataNascimento + "\">");
			}
			i++;
		}
	}
}
