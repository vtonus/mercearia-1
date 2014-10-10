package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

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
public class NovoCliente extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setEmail(request.getParameter("email"));
		try {
			String cpf = request.getParameter("doc");
			if (ValidaCPF.isCPF(cpf)) {
				cliente.setCpf(cpf);
			} else {
				response.getWriter().write("CPF invalido");
				response.setStatus(501);
				return;
			}
		} catch (NullPointerException e) {
		}

		try {
			cliente.setSexoC(request.getParameter("sexo"));
		} catch (RuntimeException e) {
			response.getWriter().write("\nsexo inválido");
		}
		try {
			cliente.setDataNascimento(Conversao.textoHEmData(request
					.getParameter("dtn")));
		} catch (ParseException e) {
			response.getWriter().write("\ndata de nascimento inválida");
		} catch (NullPointerException e) {
		}
		try {
			cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
		} catch (RuntimeException e) {
		}

		cliente.setEndereco(request.getParameter("endereco"));

		ClienteDAO dao = new ClienteDAO();
		int j = dao.adiciona(cliente);
		if (j > 0) {
			Calendar calendar = Calendar.getInstance();
			String data = "";
			try {
				calendar = cliente.getDataNascimento();
				data = Conversao.calendarEmTexto(calendar);
				System.out.println("Aqui esta----"+ data);
			} catch (RuntimeException e) {
			}
			aud.setFunc_id(func_id);
			aud.setDados("id: " + j + ", cpf: " + cliente.getCpf()
					+ ", nome: " + cliente.getNome() + ", telefone: "
					+ cliente.getTelefone() + ", endereco: "
					+ cliente.getEndereco() + ", sexo: " + cliente.getSexoC()
					+ ", email: " + cliente.getEmail() + ", dataNascimento: "
					+ data);
			aud.setAcao(0);
			aud.setTabela(2);
			aud.adiciona();
			response.setStatus(200);
		} else {
			response.setStatus(500);
		}
	}
}