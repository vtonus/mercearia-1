package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.CompraProdutoDAO;
import br.com.mercearia.modelo.CompraProduto;

@SuppressWarnings("serial")
public class BuscaDetalhesCompra extends HttpServlet {
	
	CompraProdutoDAO cpdao = new CompraProdutoDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int i = Integer.parseInt(request.getParameter("id"));
		float subtotal;
		List<CompraProduto> listaCp = new ArrayList<CompraProduto>();
		listaCp = cpdao.buscaCompraProduto(i);
		i=0;
		for (CompraProduto cp : listaCp)
		{
			subtotal = cp.getValor() * cp.getQtd();
			response.getWriter().write(
				"<input type=\"hidden\" value=\"" + cp.getProduto().getId() + "\" id=\"id_produto"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + cp.getProduto().getNome() + "\" id=\"nome_produto"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + cp.getValor() + "\" id=\"valor_produto"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + cp.getQtd() + "\" id=\"quantidade"+ i + "\">" +
						"<input type=\"hidden\" value=\"" + subtotal + "\" id=\"subtotal"+ i + "\">"
				);
		}
	}
}
