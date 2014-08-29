package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class BuscaProduto extends HttpServlet {
	public Produto produto;
	ProdutoDAO pdao = new ProdutoDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		produto.setId(Long.parseLong(request.getParameter("id")));
		produto.setNome(request.getParameter("nome"));
		produto.setValorMax(Float.parseFloat(request.getParameter("valorMin")));
		produto.setValorMin(Float.parseFloat(request.getParameter("valorMax")));
		produto.setFabricante(request.getParameter("fabricante"));
		try
		{
			produto.setValidade(Conversao.textoEmData(request.getParameter("validade")));
		}catch(ParseException e){}
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pdao.busca(produto);
		int i = 0;
		for (Produto produto : listaProduto) {
			String validade = ""; 
			try {
				validade = Conversao.calendarEmTexto(produto.getValidade());	
			}catch(NullPointerException e){}
			if (produto != null) {
				response.getWriter().write(
								  "<input type=\"hidden\" id=\"id" + i
								+ "\" value=\"" + produto.getId() + "\">"
								+ "<input type=\"hidden\" id=\"nome" + i
								+ "\" value=\"" + produto.getNome() + "\">"
								+ "<input type=\"hidden\" id=\"valor" + i 
								+ "\" value=\"" + produto.getValor() + "\">"
								+ "<input type=\"hidden\" id=\"fabricante" + i
								+ "\" value=\"" + produto.getFabricante() + "\">"
								+ "<input type=\"hidden\" id=\"quantidade" + i
								+ "\" value=\"" + produto.getQtd() + "\">"
								+ "<input type=\"hidden\" id=\"estoque" + i
								+ "\" value=\"" + produto.getEstoque() + "\">"
								+ "<input type=\"hidden\" id=\"validade" + i
								+ "\" value=\"" + validade + "\">");
			}
			i++;
		}
	}
}
