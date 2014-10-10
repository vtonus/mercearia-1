package br.com.mercearia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Funcionario;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario = dao.checaLogin(usuario, senha);
		boolean bool = false;
		try{
			if (funcionario.getCpf().trim().length() > 0){
				bool = true;
			}
		}catch(NullPointerException e){}
		if (bool) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", funcionario.getNome());
			session.setAttribute("usuarioCpf", funcionario.getCpf());
			response.sendRedirect("views/Menu.jsp");
			return;
		} else {
			response.setStatus(400);
		}
	}
}
