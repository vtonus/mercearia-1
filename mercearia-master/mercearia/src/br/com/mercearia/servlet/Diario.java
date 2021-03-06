package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.RelatorioDAO;
import br.com.mercearia.modelo.RelatorioD;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class Diario extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String tipo = request.getParameter("tipo");
		String dia = request.getParameter("dia");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar = Conversao.textoHEmData(dia);
		} catch (ParseException e) {
		}
		RelatorioDAO rdao = new RelatorioDAO();
		RelatorioD rd;
		rd = rdao.buscaDiario(calendar);
		int i = 0;
		response.setCharacterEncoding("utf-8");
		String output = new String();
		for (Calendar c : rd.getListaCalendar()) {
			if (!(c == null)) {
				output = output.concat(" <input type=\"hidden\" id=\"dia" + i + "\" "
				+ " value=\"" + Conversao.calendarEmTexto(c) + "\">"
						+ " <input type=\"hidden\" id=\"venda" + i + "\" "
						+ " value=\"" + rd.getValor().get(i) + "\">");
				i++;
			}
		}
		output = output.concat(" <input type=\"hidden\" id=\"mmensal\"" + " value=\""
				+ rd.getMmensal() + "\">");
		i = 0;
		while (i < rd.getQtd().size()) {
			System.out.println("Entrou....");
			output = output.concat(" <input type=\"hidden\" id=\"nome" + i + "\" "
					+ " value=\"" + rd.getNome().get(i) + "\">"
					+ " <input type=\"hidden\" id=\"qtd" + i + "\" "
					+ " value=\"" + rd.getQtd().get(i++) + "\">");
		}
		output = output.concat(" <input type=\"hidden\" id=\"abriu\" " + " value=\""
				+ rd.getAbriu() + "\">"
				+ " <input type=\"hidden\" id=\"fechou\"" + " value=\""
				+ rd.getFechou() + "\">"
				+ " <input type=\"hidden\" id=\"cartao\" " + " value=\""
				+ rd.getCartao() + "\">"
				+ " <input type=\"hidden\" id=\"dinheiro\" " + " value=\""
				+ rd.getDinheiro() + "\">"
				+ " <input type=\"hidden\" id=\"prazo\" " + " value=\""
				+ rd.getPrazo() + "\">");
		i = 0;
		System.out.println("tamanho eh..."+rd.getValor().size());
		for (int j = 0; j < 24; j++) {
			output = output.concat(" <input type=\"hidden\" id=\"valor" + j + "\""
					+ " value=\"" + rd.getVenda().get(j) + "\">");
		}
		System.out.println("output �: "+output);
		response.getWriter().write(output);
	}
}