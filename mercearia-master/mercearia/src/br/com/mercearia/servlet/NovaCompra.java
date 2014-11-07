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
import br.com.mercearia.dao.CompraDAO;
import br.com.mercearia.dao.CompraProdutoDAO;
import br.com.mercearia.dao.ProdutoDAO;
import br.com.mercearia.modelo.Compra;
import br.com.mercearia.modelo.CompraProduto;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.util.Auditoria;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class NovaCompra extends HttpServlet {
	ClienteDAO cdao = new ClienteDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ClienteDAO cldao = new ClienteDAO();
		response.setCharacterEncoding("utf-8");
	
		Auditoria aud = new Auditoria();
		HttpSession session = request.getSession();
		String func_id = (String) session.getAttribute("usuarioCpf");

		JSONObject myobj = new JSONObject("{\"produto\":"
				+ request.getParameter("produto") + "}");
		JSONArray produtos = myobj.getJSONArray("produto");
		List<Produto> listaProduto = new ArrayList<Produto>();
		float totalCompra = 0;

		boolean bool = false;
		
		synchronized (listaProduto) {
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
					totalCompra += (produto.getValor() * produto.getQtd());

					listaProduto.add(produto);

					bool = true;
				} else
					break;
			}
		}
		
		if (bool) {
			CompraDAO cdao = new CompraDAO();
			Compra compra = new Compra();
			compra.setValor(totalCompra);

			session = request.getSession();
			compra.setFuncionarioId((String) session.getAttribute("usuarioCpf"));
			compra.setFuncId((String) session.getAttribute("usuarioCpf"));
			compra.setMetodo(Integer.parseInt(request.getParameter("metodoPag")));
			
			int id = cdao.adiciona(compra);
			Calendar calendar = Calendar.getInstance();
			aud.setFunc_id(func_id);
			aud.setDados("id: "+id+", datahora: "+Conversao.calendarCEmTexto(calendar)+", valor: "+compra.getValor()+", id_funcionario: "+func_id+", id_cliente: "+compra.getClienteNome());
			aud.setAcao(0);
			aud.setTabela(3);
			aud.adiciona();
			CompraProdutoDAO cpdao = new CompraProdutoDAO();
			CompraProduto cp = new CompraProduto();
			ProdutoDAO pdao = new ProdutoDAO();
			for (Produto p : listaProduto) {
				cp.setCompraId(id);
				cp.setProduto(p);
				cpdao.adiciona(cp);
				pdao.atualizaQtd(p.getId(), -p.getQtd());
				aud.setDados("id_produto: "+p.getId()+", id_compra: "+id+", valor"+p.getValor()+", qtd: "+p.getQtd());
				aud.setAcao(0);
				aud.setTabela(4);
				aud.adiciona();
			}
			response.setStatus(200);
			
		} else
			response.setStatus(500);
	}
}