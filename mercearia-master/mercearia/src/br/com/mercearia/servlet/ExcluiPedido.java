package br.com.mercearia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.dao.ProdutoPedidoDAO;
import br.com.mercearia.modelo.ProdutoPedido;
import br.com.mercearia.util.Auditoria;

@SuppressWarnings("serial")
public class ExcluiPedido extends HttpServlet {
	PedidoDAO pedao = new PedidoDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		int id = Integer.parseInt(request.getParameter("id"));
		ProdutoPedidoDAO ppdao = new ProdutoPedidoDAO();
		if(ppdao.exclui(id)){
		if (pedao.exclui(id))
		{
			aud.setFunc_id(func_id);
			aud.setDados("id: "+id);
			aud.setAcao(2);
			aud.setTabela(8);
			aud.adiciona();
			
			response.setStatus(200);
		}
		else{
			response.setStatus(501);
			}
		}
		else
		{
			response.setStatus(501);
		}
	}
}
