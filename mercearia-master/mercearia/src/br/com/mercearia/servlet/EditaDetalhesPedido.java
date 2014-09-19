
package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.JSON.JSONArray;
import org.JSON.JSONException;
import org.JSON.JSONObject;

import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.dao.ProdutoPedidoDAO;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.modelo.ProdutoPedido;

@SuppressWarnings("serial")
public class EditaDetalhesPedido extends HttpServlet {

	//NAO ESTA COMPLETO!!!
	//Testando............
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProdutoPedidoDAO ppdao = new ProdutoPedidoDAO();
		ProdutoPedido pp = new ProdutoPedido();
		PedidoDAO pedao = new PedidoDAO();
		Pedido pe = new Pedido();

		JSONArray produtos;
		JSONObject myobj;

		try {
			myobj = new JSONObject("{\"produto\":"
					+ request.getParameter("produto") + "}");
			produtos = myobj.getJSONArray("produto");
		} catch (JSONException e) {
			e.printStackTrace();
			response.setStatus(500);
			return;
		}

		float totalPedido = 0;
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
			pe.setId(Integer.parseInt(request.getParameter("idPedido")));
			pe.setValor(totalPedido);			
			
			if (pedao.editaValor(pe)) {
				if (ppdao.exclui(pe.getId())) {
					for (Produto p : listaProduto) {
						pp.setPedido(pe);
						pp.setProduto(p);
						ppdao.adiciona(pp);
					}
				}
					if (ppdao.exclui(pp.getPedidoId())) {

					}
					ppdao.adiciona(pp);
				}
			} else {
				response.setStatus(500);
			}
			response.setStatus(200);
	

	}
}