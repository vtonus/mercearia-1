package br.com.mercearia.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.com.mercearia.dao.RelatorioDAO;
import br.com.mercearia.modelo.Produto;
import br.com.mercearia.modelo.RelatorioD;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class Diario extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String tipo = request.getParameter("tipo");
		String dia = request.getParameter("dia");
		Calendar calendar = Calendar.getInstance(); 
		try{
			calendar = Conversao.textoHEmData(dia);
		}catch(ParseException e){}
		RelatorioDAO rdao = new RelatorioDAO();
		RelatorioD rd = new RelatorioD();
		rd = rdao.busca(calendar);
		int i = 0;
		float total;
		response.setCharacterEncoding("utf-8");
		String output = "";
		for (Calendar c : rd.getListaCalendar()) {
			output.concat(" <input type=\"hidden\" id=\"dia" + i + "\" "
					+ " value=\"" + Conversao.calendarEmTexto(c) + "\">"
					+ " <input type=\"hidden\" id=\"venda" + i + "\" "
					+ " value=\"" + rd.getVenda(i) + "\">");
			total += rd.getVenda(i);
			i++;
		}
		output.concat(" <input type=\"hidden\" id=\"mmensal\"" + " value=\""
				+ total + "\">");
		i = 0;
		while (rd.getQtd(i) > 0){
			output.concat(" <input type=\"hidden\" id=\"nome" + i + "\" "
					+ " value=\"" + rd.getNome(i) + "\">"
					+ " <input type=\"hidden\" id=\"qtd" + i + "\" "
					+ " value=\"" + rd.getQtd(i++) + "\">"
			);
		}
		output.concat(" <input type=\"hidden\" id=\"abriu\" "
				+ " value=\"" + rd.getAbriu() + "\">"
				+ " <input type=\"hidden\" id=\"fechou\""
				+ " value=\"" + rd.getFechou() + "\">"
				+ " <input type=\"hidden\" id=\"cartao\" "
				+ " value=\"" + rd.getCartao() + "\">"
				+ " <input type=\"hidden\" id=\"dinheiro\" "
				+ " value=\"" + rd.getDinheiro() + "\">"
				+ " <input type=\"hidden\" id=\"prazo\" "
				+ " value=\"" + rd.getPrazo() + "\">"
		);
		i=0;
		for (int j=0 ; j<24; j++){
			output.concat(" <input type=\"hidden\" id=\"valor"+j+"\""
					+ " value=\"" + rd.getValor(j) + "\">"
			);		
		}
		response.getWriter().write(output);
	}
}