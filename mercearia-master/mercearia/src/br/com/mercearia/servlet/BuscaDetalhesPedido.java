package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ProdutoPedidoDAO;
import br.com.mercearia.modelo.ProdutoPedido;

@SuppressWarnings("serial")
public class BuscaDetalhesPedido extends HttpServlet {
	//...
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ProdutoPedidoDAO ppdao = new ProdutoPedidoDAO();
		
		int i = Integer.parseInt(request.getParameter("id"));
		List<ProdutoPedido> listaPp = new ArrayList<ProdutoPedido>();
		listaPp = ppdao.buscaPedido(i);
		i=0;
		for (ProdutoPedido pp : listaPp)
		{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
				"<input type=\"hidden\" value=\"" + pp.getProduto().getNome() + "\" id=\"nome_prod"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + pp.getProduto().getQtd() + "\" id=\"qtd_prod"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + pp.getProduto().getValor() + "\" id=\"valor_produto"+ i + "\">"
				);
		}
	}
}
