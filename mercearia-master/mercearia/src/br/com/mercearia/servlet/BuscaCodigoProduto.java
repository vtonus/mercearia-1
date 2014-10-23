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
		Long c = null;
		try{
		c = Long.parseLong(request.getParameter("produto"));
		}catch(RuntimeException e){}
		//List<Produto> listaProduto = new ArrayList<Produto>();
		Produto p = pdao.buscaCodigoProduto(c);
		response.getWriter().write(
				"<input type=\"hidden\" id=\"nomeAC\""
				+ "value=\"" + p.getNome() + "\">"
				+ "<input type=\"hidden\" id=\"valorAC\""
				+ "value=\"" + p.getValor() + "\">");
		}
	}