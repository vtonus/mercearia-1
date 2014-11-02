package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class BuscaFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String palavraChave = request.getParameter("palavraChave");
		String parametro = request.getParameter("parametro");
		List<Funcionario> listaF = new ArrayList<Funcionario>();
		listaF = fdao.busca(palavraChave, parametro);
		int i = 0;
		String dataNascimento;
		boolean bool = false;
		for (Funcionario f : listaF) {
			dataNascimento = "";
			
			try{
				dataNascimento = Conversao.calendarEmTexto(f
					.getDataNascimento());
			}catch(RuntimeException e){}
			String telefone;
			if (f.getTelefone()==0){
				telefone ="";
			}
			else telefone=""+f.getTelefone();
			if (f != null) {
				bool = true;
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(
								  "<input type=\"hidden\" id=\"cpf" + i
								+ "\" value=\"" + f.getCpf() + "\">"
								+ "<input type=\"hidden\" id=\"nome" + i 
								+ "\" value=\"" + f.getNome() + "\">"
								+ "<input type=\"hidden\" id=\"telefone" + i
								+ "\" value=\"" + telefone + "\">"
								+ "<input type=\"hidden\" id=\"email" + i
								+ "\" value=\"" + f.getEmail() + "\">"
								+ "<input type=\"hidden\" id=\"dataNascimento" + i
								+ "\" value=\"" + dataNascimento + "\">");
			}
			i++;
		}
		if (!bool) {
			response.setStatus(400);
		}
	}
}
