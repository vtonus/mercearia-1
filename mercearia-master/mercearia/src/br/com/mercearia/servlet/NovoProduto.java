package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovoProduto extends HttpServlet {
	ProdutoDAO pdao = new ProdutoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		boolean bool = false;
		try{
		produto.setId(Long.parseLong(request.getParameter("id")));
		} catch(NullPointerException | NumberFormatException e){
			response.getWriter().write("Codigo de barras invalido");
			response.setStatus(500);
			return;
		}
		produto.setNome(request.getParameter("nome"));
		try {
			produto.setValor(Float.parseFloat(request.getParameter("valor")));
		} catch (NumberFormatException e) {
			response.getWriter().write("Valor do produto invalido");
			response.setStatus(500);
			return;
		}

		try {
			produto.setFabricante(request.getParameter("fabricante"));
		} catch (RuntimeException e) {
		}
		

		try {
			produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
		} catch (NumberFormatException e) {
			response.getWriter().write("Estoque do produto invalido");
			response.setStatus(500);
			return;
		}
		catch(NullPointerException e){}
		
		ProdutoDAO pdao = new ProdutoDAO();
		
		bool = pdao.adiciona(produto);
		if (bool)
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}