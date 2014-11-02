package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.CompraDAO;
import br.com.mercearia.modelo.Compra;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class BuscaCompra extends HttpServlet {
	
	CompraDAO codao = new CompraDAO();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//FuncionarioDAO fdao = new FuncionarioDAO();
		//ClienteDAO cldao = new ClienteDAO();
		Compra compra = new Compra();
		
		try{
			compra.setId(Integer.parseInt(request.getParameter("id")));
		}
		catch(RuntimeException e){}
		
		try{
			compra.setHoraIni(Conversao.textoHEmData(request.getParameter("dataHoraIni")));
		}
		catch(RuntimeException | ParseException e){System.out.println("Data ini error");}
		
		try{
			compra.setHoraFim(Conversao.textoHEmData(request.getParameter("dataHoraFim")));
		}
		catch(RuntimeException | ParseException e){System.out.println("Data ini error");}
		
		try{
			compra.setFuncionarioNome(request.getParameter("funcionario"));
		}
		catch(NullPointerException e){}
		
		try{
		compra.setClienteNome(request.getParameter("cliente"));
		}
		catch(NullPointerException e){}
		
		List<Compra> listaCompras = new ArrayList<Compra>();
		listaCompras = codao.buscaCompra(compra);

		int i = 0;
		for (Compra c : listaCompras) {
			if (c != null) {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(
						"<input type=\"hidden\" id=\"id" + i + "\" "
						+ "value=\""+ c.getId() +"\">"
						+ "<input type=\"hidden\" id=\"datahora" + i + "\" "
						+ "value=\""+ Conversao.calendarCEmTexto(c.getHora()) +"\">"
						+ "<input type=\"hidden\" id=\"valor" + i + "\" "
						+ "value=\""+ c.getValor() +"\">"
						+ "<input type=\"hidden\" id=\"funcionario" + i + "\" "
						+ "value=\""+ c.getFuncionarioNome() +"\">"
						+ "<input type=\"hidden\" id=\"cliente" + i + "\" "
						+ "value=\""+ c.getClienteNome() +"\">"
						);
			}
			i++;
		}
	}
}
