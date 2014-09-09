package br.com.mercearia.servlet;
package org.JSON;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.CompraDAO;
import br.com.mercearia.dao.CompraProdutoDAO;
import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.Compra;
import br.com.mercearia.modelo.CompraProduto;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovaCompra extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ClienteDAO cldao = new ClienteDAO();
		ProdutoDAO pdao = new ProdutoDAO();
		Produto produto = new Produto();
		List<Produto> listaProduto= new ArrayList<Produto>();
		JSONObject my_obj = new JSONObject(request.getParameter("produto"));
		JSONArray produtos = myobj.getJSONArray("produto");
		JSONArray qtds = myobj.getJSONArray("qtd");
		for (int i = 0; i < produtos.length(); i++) {
			produto.setId(produtos.get(i));
			produto.setQtd(qtds.get(i));
			listaProduto.add(produto);
		}
		
		
		
		int j;
		
		do {
			j = 0;
			System.out.println(request.getParameter("codigo"+i)+request.getParameter("qtd"+i));
			produto = pdao.busca(Long.parseLong(request.getParameter("codigo"+i)));
			total +=((produto.getValor()) * Integer.parseInt(request
					.getParameter("qtd" + i)));
			System.out.println("--------" + i+" - " + total);
			i++;
			try {
				j = Integer.parseInt(request.getParameter("codigo" + i));
			} catch (RuntimeException e) {
			}

		} while (j > 0);
		//String cliente = request.getParameter("nomeCliente");
		Compra compra = new Compra();
		/*if (cliente != "" && cliente != null) { // ** Esse trecho eh um pequeno ajuste ... **
			ClienteDAO clidao = new ClienteDAO();
			compra.setCliente(clidao.busca(cliente, ""));
			boo = true;
		}*/
		compra.setFuncionario(funcionario);
		compra.setValor(total);
		CompraDAO comdao = new CompraDAO();
		
		if (boo) {
			compra.setId(comdao.adicionaC(compra));
		} else {
			compra.setId(comdao.adiciona(compra));
		}
		// --------------------------------------------
		i = 1;
		if (Integer.parseInt(request.getParameter("codigo1")) > 0) {
			CompraProdutoDAO cpdao = new CompraProdutoDAO();
			CompraProduto compraProduto = new CompraProduto();
			do {
				j = 0;
				produto = pdao.busca(Long.parseLong(request
						.getParameter("codigo" + i)));
				compraProduto.setProduto(produto);
				compraProduto.setCompra(compra);
				System.out.println(compraProduto.getCompra().getId());
				compraProduto.setQtd(Integer.parseInt(request
						.getParameter("qtd" + i)));
				compraProduto.setValor(produto.getValor());
				cpdao.adiciona(compraProduto);
				i++;
				try {
					j = Integer.parseInt(request.getParameter("codigo" + i));
				} catch (RuntimeException e) {
				}
			} while (j > 0);
		}
		if (dao.adiciona(cliente))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}