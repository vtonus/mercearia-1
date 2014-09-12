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
import org.JSON.JSONObject;

import br.com.mercearia.dao.ClienteDAO;
import br.com.mercearia.dao.CompraDAO;
import br.com.mercearia.dao.CompraProdutoDAO;
import br.com.mercearia.dao.FuncionarioDAO;
import br.com.mercearia.modelo.Compra;
import br.com.mercearia.modelo.CompraProduto;
import br.com.mercearia.modelo.Produto;

@SuppressWarnings("serial")
public class NovoPedido extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ClienteDAO cldao = new ClienteDAO();

		JSONObject myobj = new JSONObject("{\"produto\":"
				+ request.getParameter("produto") + "}");
		JSONArray produtos = myobj.getJSONArray("produto");
		List<Produto> listaProduto = new ArrayList<Produto>();
		float totalPedido = 0;
		boolean bool = false;
		for (int i = 0; i < produtos.length(); i++) {
			String jsonstr = "{\"produto\":" + produtos.get(i) + "}";
			myobj = new JSONObject(jsonstr);
			JSONArray jsonarray = myobj.getJSONArray("produto");
			if ((Integer.parseInt((String) jsonarray.get(2).toString())) > 0) {
				Produto produto = new Produto();
				produto.setQtd(Integer.parseInt((String) jsonarray.get(2)
						.toString()));
				produto.setId(Long.parseLong((String) jsonarray.get(4)
						.toString()));
				produto.setValor(Float.parseFloat((String) jsonarray.get(1)
						.toString()));
				totalCompra = + (produto.getValor()*produto.getQtd());
				System.out.println(produto.getValor());
				listaProduto.add(produto);
				bool = true;
			} else
				break;
		}
		if (bool) {
			CompraDAO cdao = new CompraDAO();
			Compra compra = new Compra();
			compra.setValor(totalCompra);
			FuncionarioDAO fdao = new FuncionarioDAO();
			HttpSession session = request.getSession();
			compra.setFuncionario(fdao.busca((Long) session.getAttribute("usuarioCpf")));
			int id = cdao.adiciona(compra);
			CompraProdutoDAO cpdao = new CompraProdutoDAO();
			CompraProduto cp = new CompraProduto();
			for (Produto p : listaProduto){
				cp.setCompraId(id);
				cp.setProduto(p);
				cpdao.adiciona(cp);
			}
			response.setStatus(200);
		}
		else response.setStatus(500);
	}
}