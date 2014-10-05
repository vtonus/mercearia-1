package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.JSON.JSONArray;
import org.JSON.JSONException;
import org.JSON.JSONObject;

import br.com.mercearia.dao.PedidoDAO;
import br.com.mercearia.dao.ProdutoPedidoDAO;
import br.com.mercearia.modelo.Pedido;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.modelo.ProdutoPedido;
import br.com.mercearia.util.Auditoria;

@SuppressWarnings("serial")
public class EditaDetalhesPedido extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_Id = (String) session.getAttribute("usuarioCpf");
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
			} else {
				break;
			}
		}

		if (bool) {
			pe.setId(Integer.parseInt(request.getParameter("idPedido")));
			Pedido peBack = new Pedido();
			peBack = pedao.busca(pe.getId());
			pe.setValor(totalPedido);
			if (pedao.editaValor(pe)) {
				aud.setFunc_id(func_Id);
				aud.setDados("id_pedido: "+pe.getId() +"valor: "+ pe.getValor());
				aud.setAcao(1);
				aud.setTabela(8);
				aud.adiciona();				
				
				List<ProdutoPedido> ppBack = new ArrayList<ProdutoPedido>();
				ppBack = ppdao.buscaPedido(pe.getId());
				if (ppdao.exclui(pe.getId())) {
	
					aud.setFunc_id(func_Id);
					aud.setDados("id_pedido: "+pe.getId());
					aud.setAcao(2);
					aud.setTabela(10);
					aud.adiciona();
					for (Produto p : listaProduto) {
						pp.setPedido(pe);
						pp.setProduto(p);
						if (ppdao.adiciona(pp)) {
							aud.setFunc_id(func_Id);
							aud.setDados("id_produto: "+pp.getProduto().getId()+", id_pedido: "+pp.getPedido().getId()+", qtd: "+pp.getProduto().getQtd()+", valor: "+pp.getProduto().getValor());
							aud.setAcao(0);
							aud.setTabela(10);
							aud.adiciona();
						}
						else
						{
							ppdao.exclui(pe.getId());
							aud.setDados("id_pedido: "+pe.getId());
							aud.setAcao(2);
							aud.setTabela(10);
							aud.adiciona();
							for (ProdutoPedido ppBackup : ppBack) {
								ppdao.adiciona(ppBackup);
								aud.setDados("id_produto: "+ppBackup.getProduto().getId()+", id_pedido: "+ppBackup.getPedido().getId()+", qtd: "+ppBackup.getProduto().getQtd()+", valor: "+ppBackup.getProduto().getValor());
								aud.setAcao(0);
								aud.setTabela(10);
								aud.adiciona();
							}
							pedao.editaValor(peBack);
							aud.setDados("id: "+peBack.getId()+", valor: "+peBack.getValor());
							aud.setAcao(1);
							aud.setTabela(8);
							aud.adiciona();
							break;							
						}
					}
				} else {
					pedao.editaValor(peBack);
					aud.setDados("id: "+peBack.getId()+", valor: "+peBack.getValor());
					aud.setAcao(1);
					aud.setTabela(8);
					aud.adiciona();
					response.setStatus(500);
					return;
				}
			}
		} else {
			response.setStatus(500);
		}
		response.setStatus(200);
	}
}