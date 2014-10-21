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
	public Produto produto = new Produto();
	ProdutoDAO pdao = new ProdutoDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		response.setStatus(200);
		return;
	}
}
