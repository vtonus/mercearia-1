package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Produto;

@SuppressWarnings("serial")
public class BuscaCodigoProduto extends HttpServlet {
	ProdutoDAO pdao = new ProdutoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long c = Long.parseLong(request.getParameter("produto"));
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pdao.buscaCodigoProduto(c);
		for (Produto produto : listaProduto) {
			response.getWriter().write(
					"<input type=\"hidden\" id=\"nomeAC\""
					+ "value=\"" + produto.getNome() + "\">"
					+ "<input type=\"hidden\" id=\"valorAC\""
					+ "value=\"" + produto.getValor() + "\">");
		}
	}
}
