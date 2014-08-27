package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FuncionarioDAO;

@SuppressWarnings("serial")
public class BuscaNomeFuncionario extends HttpServlet {
	FuncionarioDAO fdao = new FuncionarioDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String palavra = request.getParameter("funcionario");
		List<String> listaFuncionario = new ArrayList<String>();
		listaFuncionario = fdao.buscaNomeFuncionario(palavra);
		int i=0;
		for (String f: listaFuncionario) {
			if (f != null) 
			{
				response.getWriter().write(
						"<input type=\"hidden\" id=\"nome" + i + "\" "
								+ "value=\""+ f +"\">");
			}
		}
	}
}
