package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.JSON.JSONArray;
import org.JSON.JSONObject;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.dao.ProdutoPedidoDAO;
import br.com.mercearia.modelo.Fornecedor;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.modelo.ProdutoPedido;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovoPedido extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ClienteDAO cldao = new ClienteDAO();

		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");
		aud.setFunc_id(func_id);;

		
		Float totalPedido = null;

		JSONObject myobj = new JSONObject("{\"produto\":"
				+ request.getParameter("produto") + "}");
		JSONArray produtos = myobj.getJSONArray("produto");

		List<Produto> listaProduto = new ArrayList<Produto>();

		boolean bool = false;
		for (int i = 0; i < produtos.length(); i++) {
			String jsonstr = "{\"produto\":" + produtos.get(i) + "}";
			myobj = new JSONObject(jsonstr);
			JSONArray jsonarray = myobj.getJSONArray("produto");
			if ((Integer.parseInt((String) jsonarray.get(1).toString())) > 0) {
				Produto produto = new Produto();
				produto.setId(Long.parseLong((String) jsonarray.get(0)
						.toString()));
				produto.setQtd(Integer.parseInt((String) jsonarray.get(1)
						.toString()));
				produto.setValor(Float.parseFloat((String) jsonarray.get(2)
						.toString()));
				totalPedido = +(produto.getValor() * produto.getQtd());

				listaProduto.add(produto);
				bool = true;
			} else
				break;
		}
		if (bool) {
			PedidoDAO pdao = new PedidoDAO();
			Pedido pedido = new Pedido();
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(Integer.parseInt(request.getParameter("idForn")));
			pedido.setFornecedor(fornecedor);
			FuncionarioDAO fdao = new FuncionarioDAO();
			session = request.getSession();
			pedido.setFuncionario(fdao.busca((String) session
					.getAttribute("usuarioCpf")));
			if (!(request.getParameter("desc").trim().length() < 60)){
				response.getWriter().write("descricao do pedido invalido, este deve ter no maximo 60 caracteres");
				response.setStatus(501);
				return;
			}
			pedido.setDescricao("desc");
			pedido.setValor(totalPedido);
			if (pedido.getValor()>999999){
				response.getWriter().write("valor da compra excedeu o limite de 999.999 reais");
				response.setStatus(501);
				return;
			}
			int id = pdao.adiciona(pedido);
			aud.setFunc_id(func_id);
			Calendar calendar = Calendar.getInstance();
			aud.setDados("id: "+id+", valor: "+pedido.getValor()+", descricao: "+pedido.getDescricao()+", fornecedor: "+pedido.getFornecedor().getId()+", funcionario: "+pedido.getFuncId()+", datahora: "+ Conversao.calendarCEmTexto(calendar));
			aud.setAcao(0);
			aud.setTabela(8);
			aud.adiciona();
			ProdutoPedidoDAO ppdao = new ProdutoPedidoDAO();
			ProdutoPedido pp = new ProdutoPedido();
			for (Produto p : listaProduto) {
				pp.setPedidoId(id);
				pp.setProduto(p);
				ppdao.adiciona(pp);
				aud.setDados("id_produto: "+p.getId()+", id_pedido "+id+", qtd: "+p.getQtd()+", valor: "+p.getValor());
				aud.setAcao(0);
				aud.setTabela(8);
				aud.adiciona();
			}
			response.setStatus(200);
		} else
			response.setStatus(500);
	}
}