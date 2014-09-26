package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.FornecedorDAO;
import br.com.mercearia.modelo.Fornecedor;

@SuppressWarnings("serial")
public class BuscaFornecedor extends HttpServlet {
	FornecedorDAO fdao = new FornecedorDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String palavraChave = request.getParameter("palavraChave");
		String parametro = request.getParameter("parametro");
		List<Fornecedor> listaF = new ArrayList<Fornecedor>();
		listaF = fdao.busca(palavraChave, parametro);
		int i = 0;

		for (Fornecedor f : listaF) {
			System.out.println("Achou mais um");
			String cnpj;
			if (f.getCnpj() == 0) {
				cnpj = "";
			} else
				cnpj = "" + f.getCnpj();

			String telefone;
			if (f.getTelefone() == 0) {
				telefone = "";
			} else {
				telefone = ("" + f.getTelefone());
			}
			System.out.println(telefone);
			if (f != null) {
				response.getWriter().write(
						"<input type=\"hidden\" id=\"id" + i + "\" value=\""
								+ f.getId() + "\">"
								+ "<input type=\"hidden\" id=\"nome" + i
								+ "\" value=\"" + f.getNome() + "\">"
								+ "<input type=\"hidden\" id=\"cnpj" + i
								+ "\" value=\"" + cnpj + "\">"
								+ "<input type=\"hidden\" id=\"telefone" + i
								+ "\" value=\"" + telefone + "\">"
								+ "<input type=\"hidden\" id=\"email" + i
								+ "\" value=\"" + f.getEmail() + "\">"
								+ "<input type=\"hidden\" id=\"endereco" + i
								+ "\" value=\"" + f.getEndereco() + "\">");
			}
			i++;
		}
	}
}
