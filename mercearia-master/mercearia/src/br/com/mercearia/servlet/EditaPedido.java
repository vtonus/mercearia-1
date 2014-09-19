package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.modelo.Fornecedor;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class EditaPedido extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		//Testando..................................
		
		
		PedidoDAO pedao = new PedidoDAO();
		Pedido pedido = new Pedido();
		Fornecedor fornecedor = new Fornecedor();
		Funcionario funcionario = new Funcionario();
		fornecedor.setId(Integer.parseInt(request.getParameter("idForn")));
		pedido.setDescricao(request.getParameter("desc"));
		funcionario.setCpf(Long.parseLong(request.getParameter("funcionario")));
		
		try {
		Calendar datahora = Calendar.getInstance();
		datahora = Conversao.textoEmDataHoraLocal(request.getParameter("datahora"));
		pedido.setDataHora(datahora);
		}catch(ParseException e)
		{
			response.setStatus(500);
			System.out.println("Falha na conversão da data.");
			return;
		}
		
		pedido.setFornecedor(fornecedor);
		pedido.setFuncionario(funcionario);

		if(pedao.edita(pedido))
		{
			response.setStatus(200);
		}
		else
		{
			response.setStatus(500);
		}
	}
}
