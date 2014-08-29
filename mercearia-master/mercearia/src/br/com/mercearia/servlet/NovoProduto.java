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
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setNome(request.getParameter("nome"));
		try {
			produto.setValor(Float.parseFloat(request.getParameter("valor")));
		} catch (RuntimeException e) {
		}

		try {
			produto.setFabricante(request.getParameter("fabricante"));
		} catch (RuntimeException e) {
		}

		try {
			produto.setQtd(Integer.parseInt(request.getParameter("qtd")));
		} catch (RuntimeException e) {
		}
		
		try {
			produto.setValidade(Conversao.textoEmData(request
					.getParameter("validade")));
		} catch (ParseException | RuntimeException e) {
		}

		try {
			produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
		} catch (RuntimeException e) {
		}
		
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