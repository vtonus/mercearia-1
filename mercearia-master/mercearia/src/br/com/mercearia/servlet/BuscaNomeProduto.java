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
public class BuscaNomeProduto extends HttpServlet {
	ProdutoDAO pdao = new ProdutoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String palavra = request.getParameter("produto");
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pdao.procuraNomeProduto(palavra);
		int i=0;
		for (Produto p: listaProduto) {
			if (p != null) {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(
						  "<input type=\"hidden\" id=\"valor" + i + "\" "
						+ "value=\"" + p.getValor() + "\">"
						+ "<input type=\"hidden\" id=\"id" + i + "\" "
						+ "value=\"" + p.getId() + "\">"
						+ "<input type=\"hidden\" id=\"nome" + i + "\" "
						+ "value=\"" + p.getNome() + "\">");						
			}
			i++;
		}
	}
}
