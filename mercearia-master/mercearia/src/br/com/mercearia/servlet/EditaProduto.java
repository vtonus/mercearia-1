package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class EditaProduto extends HttpServlet {
	ProdutoDAO pdao = new ProdutoDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		Produto produto = new Produto();
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setNome(request.getParameter("nome"));
		produto.setValor(Float.parseFloat(request.getParameter("valor")));
		produto.setFabricante(request.getParameter("fabricante"));
		produto.setQtd(Integer.parseInt(request.getParameter("qtd")));
		produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
		try
		{
			produto.setValidade(Conversao.textoEmData(request.getParameter("validade")));
		}catch(ParseException e){}
		if(pdao.edita(produto))
		{
			aud.setFunc_id(func_id);
			String data="";
			try{
				data = Conversao.calendarEmTexto(produto.getValidade());	
			}catch(NullPointerException e){}
			
			aud.setDados("id: "+produto.getId()+", nome: "+produto.getNome()+", valor: "+produto.getValor()+", fabricante: "+produto.getFabricante()+", qtd:"+produto.getQtd()+", estoque: "+produto.getEstoque()+", validade: "+data);	
			aud.setAcao(1);
			aud.setTabela(9);
			aud.adiciona();
			
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
