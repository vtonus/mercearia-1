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
			response.setStatus(501);
			return;
		}
		String id = ""+produto.getId();
		if (id.length() > 13){
			response.getWriter().write("Codigo de barras invalido");
			response.setStatus(501);
			return;
		}
		if (request.getParameter("nome").length() > 40 || request.getParameter("nome").length() < 6 ){
			response.getWriter().write("nome do produto invalido, este deve ter entre 6 e 40 caracteres");
			response.setStatus(501);
			return;
		}
		produto.setNome(request.getParameter("nome"));
		try {
			produto.setValor(Float.parseFloat(request.getParameter("valor")));
		} catch (NumberFormatException e) {
			response.getWriter().write("valor do produto invalido, certifique-se de usar ponto para separar reais de centavos");
			response.setStatus(501);
			return;
		}
		if (request.getParameter("valor").length() > 6){
		response.getWriter().write("valor do produto invalido, este deve custar no maximo 9999,99 reais");
		response.setStatus(501);
		return;
		}

		
		if ((request.getParameter("fabricante").length() > 30 || request.getParameter("fabricante").length() < 3 ) && !(request.getParameter("fabricante").length() == 0)){
			response.getWriter().write("fabricante do produto invalido, este deve ter entre 3 e 40 caracteres");
			response.setStatus(501);
			return;
		}
		produto.setFabricante(request.getParameter("fabricante"));
		
		

		try {
			produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
		} catch (NumberFormatException e) {
			response.getWriter().write("estoque do produto invalido, este deve ser o maximo 999");
			response.setStatus(501);
			return;
		}
		catch(NullPointerException e){}
		
		ProdutoDAO pdao = new ProdutoDAO();
		
		try{ 
			if (!(((Produto)pdao.busca(Long.parseLong(request.getParameter("id")))).getNome().isEmpty())){
				response.getWriter().write("codigo de barras ja cadastrado.");
				response.setStatus(501);
				return;}
				}catch(RuntimeException e){}
				
			
		
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