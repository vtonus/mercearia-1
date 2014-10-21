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
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto produto = new Produto();
		if(!(request.getParameter("id") == "")){
			try{
				produto.setId(Long.parseLong(request.getParameter("id")));
			}catch(NumberFormatException e){
				response.getWriter().write("parametro id incorreto, insira somente numeros.");
				response.setStatus(501);
				return;
			}
		}
		produto.setNome(request.getParameter("nome"));
		
		if(!(request.getParameter("valorMin") == "")){
			try{
				produto.setValorMax(Float.parseFloat(request.getParameter("valorMin")));
			}catch(NumberFormatException e){
				response.getWriter().write("parametro valorMin incorreto, insira somente numeros.");
				response.setStatus(501);
				return;
			}
		}
		
		if(!(request.getParameter("valorMax") == "")){
			try{
				produto.setValorMin(Float.parseFloat(request.getParameter("valorMax")));
			}catch(NumberFormatException e){
				response.getWriter().write("parametro valorMax incorreto, insira somente numeros.");
				response.setStatus(501);
				return;
			}
		}
		
		if(request.getParameter("fabricante") == ""){
		}
		else{
			produto.setFabricante(request.getParameter("fabricante"));
		}
		try
		{
			produto.setValidade(Conversao.textoEmData(request.getParameter("validade")));
		}catch(ParseException e){}
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pdao.busca(produto);
		int i = 0;
		for (Produto p : listaProduto) {
			String validade = ""; 
			try {
				validade = Conversao.calendarEmTexto(produto.getValidade());	
			}catch(NullPointerException e){}
			if (produto != null) {
				response.getWriter().write(
								  "<input type=\"hidden\" id=\"id" + i
								+ "\" value=\"" + p.getId() + "\">"
								+ "<input type=\"hidden\" id=\"nome" + i
								+ "\" value=\"" + p.getNome() + "\">"
								+ "<input type=\"hidden\" id=\"valor" + i 
								+ "\" value=\"" + p.getValor() + "\">"
								+ "<input type=\"hidden\" id=\"fabricante" + i
								+ "\" value=\"" + p.getFabricante() + "\">"
								+ "<input type=\"hidden\" id=\"quantidade" + i
								+ "\" value=\"" + p.getQtd() + "\">"
								+ "<input type=\"hidden\" id=\"estoque" + i
								+ "\" value=\"" + p.getEstoque() + "\">"
								+ "<input type=\"hidden\" id=\"validade" + i
								+ "\" value=\"" + validade + "\">");
			}
			i++;
		}
		response.setStatus(200);
		return;

	}
}
