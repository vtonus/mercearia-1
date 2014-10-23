package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.modelo.Fornecedor;
import br.com.mercearia.modelo.Funcionario;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class BuscaPedido extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean bool = false;

		PedidoDAO pdao = new PedidoDAO();
		Pedido pedido = new Pedido();
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(request.getParameter("forn"));
		pedido.setFornecedor(fornecedor);
		Funcionario fu = new Funcionario();
		fu.setNome(request.getParameter("func"));
		System.out.println(request.getParameter("func"));
		pedido.setFuncionario(fu);
		if (!(request.getParameter("id").trim().length() == 0)) {
			try {
				pedido.setId(Integer.parseInt(request.getParameter("id")));
			} catch (RuntimeException e) {
				response.getWriter().write(
						"insira somente numeros no campo Id.");
				response.setStatus(501);
				return;
			}
		}
			try {
				Calendar calendar = Calendar.getInstance();
				calendar = Conversao.textoHEmData(request
						.getParameter("dataMin"));
				pedido.setDataMin(calendar);
			} catch (ParseException | StringIndexOutOfBoundsException e) {
			}
			try {
				Calendar calendar1 = Calendar.getInstance();
				calendar1 = Conversao.textoHEmData(request
						.getParameter("dataMax"));
				pedido.setDataMax(calendar1);
			} catch (ParseException | StringIndexOutOfBoundsException e) {
			}
			List<Pedido> listaPe = pdao.busca(pedido);
			int i = 0;
			for (Pedido p : listaPe) {
				if (p != null) {
					bool = true;
					response.getWriter()
							.write("<input type=\"hidden\" id=\"id"
									+ i
									+ "\" "
									+ "value=\""
									+ p.getId()
									+ "\">"
									+ "<input type=\"hidden\" id=\"valorTotal"
									+ i
									+ "\" "
									+ "value=\""
									+ p.getValor()
									+ "\">"
									+ "<input type=\"hidden\" id=\"descricao"
									+ i
									+ "\" "
									+ "value=\""
									+ p.getDescricao()
									+ "\">"
									+ "<input type=\"hidden\" id=\"fornecedor"
									+ i
									+ "\" "
									+ "value=\""
									+ p.getFornecedor().getNome()
									+ "\">"
									+ "<input type=\"hidden\" id=\"funcionario"
									+ i
									+ "\" "
									+ "value=\""
									+ p.getFuncionario().getNome()
									+ "\">"
									+ "<input type=\"hidden\" id=\"datahora"
									+ i
									+ "\" "
									+ "value=\""
									+ Conversao.calendarEmTexto(p.getDataHora())
									+ "\">"

							);
				}
				i++;
			}
			if (bool) {
				response.setStatus(200);
			} else {
				response.setStatus(500);
			}
		}
	}
