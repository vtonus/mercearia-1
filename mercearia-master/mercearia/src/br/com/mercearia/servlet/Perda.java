package br.com.mercearia.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mercearia.dao.RelatorioDAO;
import br.com.mercearia.modelo.RelatorioPerda;
import br.com.mercearia.util.Conversao;

@SuppressWarnings("serial")
public class Perda extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String mes1 = "";
		switch (request.getParameter("mes1")) {
		case "janeiro":
			mes1 = "01";
			break;
		case "fevereiro":
			mes1 = "02";
			break;
		case "mar�o":
			mes1 = "03";
			break;
		case "abril":
			mes1 = "04";
			break;
		case "maio":
			mes1 = "05";
			break;
		case "junho":

			mes1 = "06";
			break;
		case "julho":

			mes1 = "07";
			break;
		case "agosto":

			mes1 = "08";
			break;
		case "setembro":

			mes1 = "09";
			break;
		case "outubro":

			mes1 = "10";
			break;
		case "novembro":
			mes1 = "11";
			break;
		case "dezembro":
			mes1 = "12";
			break;
		}
		if (mes1.length() < 2) {
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no mes1");
			response.setStatus(500);
			return;
		}
		String mes2 = "";
		switch (request.getParameter("mes2")) {
		case "janeiro":
			mes2 = "01";
			break;
		case "fevereiro":
			mes2 = "02";
			break;
		case "mar�o":
			mes2 = "03";
			break;
		case "abril":
			mes2 = "04";
			break;
		case "maio":
			mes2 = "05";
			break;
		case "junho":

			mes2 = "06";
			break;
		case "julho":

			mes2 = "07";
			break;
		case "agosto":

			mes2 = "08";
			break;
		case "setembro":

			mes2 = "09";
			break;
		case "outubro":

			mes2 = "10";
			break;
		case "novembro":
			mes2 = "11";
			break;
		case "dezembro":
			mes2 = "12";
			break;
		}
		if (mes2.length() < 2) {
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no mes2");
			response.setStatus(501);
			return;
		}

		String data1 = ("20" + request.getParameter("ano1") + "-" + mes1 + "-01");
		if (!(data1.length() == 10)) {
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no data1");
			response.setStatus(501);
			return;
		}

		Calendar c1;
		Calendar c2;

		try {
			c1 = Conversao.textoHEmData(data1);
		} catch (ParseException e) {
			e.printStackTrace();
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no data1");
			response.setStatus(501);
			return;
		}

		String data2 = ("20" + request.getParameter("ano2") + "-" + mes2 + "-01");
		if (!(data2.length() == 10)) {
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no data2");
			response.setStatus(501);
			return;
		}

		try {
			c2 = Conversao.textoHEmData(data2);
		} catch (ParseException e) {
			e.printStackTrace();
			response.getWriter().write(
					"Erro grave, recarregue a pagina e tente novamente.");
			System.out.println("Erro no data2");
			response.setStatus(501);
			return;
		}

		int ano1 = (Integer.parseInt(request.getParameter("ano1")));
		int ano2 = (Integer.parseInt(request.getParameter("ano2")));
		int imes1 = Integer.parseInt(mes1);
		int imes2 = Integer.parseInt(mes2);

		int cont = 0;

		if (ano1 > ano2) {
			response.getWriter()
					.write("Erro, data inserida inv�lida. data1 deve ser maior que data2");
			System.out.println("Erro com os anos");
			response.setStatus(500);
			return;
		}
		if ((ano2 - ano1) > 1) {
			response.getWriter()
					.write("Erro, datas inseridas inv�lidas. data1 deve ser maior que data2 em no m�ximo 12 meses");
			System.out.println("Erro com as datas, soma maior que 12");
			response.setStatus(501);
			return;
		} else if (ano1 == ano2 && imes1 >= imes2) {
			response.getWriter()
					.write("Erro leve, data inserida inv�lida. data1 deve ser maior que data2");
			System.out.println("Erro com os meses");
			response.setStatus(501);
			return;
		} else if (ano1 < ano2 && 12 < (imes2 + 12 - imes1)) {
			response.getWriter()
					.write("Erro leve, datas inseridas inv�lidas. data1 deve ser maior que data2 em no m�ximo 12 meses");
			System.out.println("Erro com as datas, soma maior que 12");
			response.setStatus(501);
			return;
		} else if (ano1 == ano2) {
			cont = imes2 - imes1;
		} else {
			cont = (imes2 + 12 - imes1);
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @ @ @ @
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		RelatorioDAO rdao = new RelatorioDAO();
		RelatorioPerda rp = rdao.buscaPerda(c1, c2);
		String output = "";
		int i = 0;

		try {
			for (String s : rp.getLista_nome()) {
				output = output.concat(" <input type=\"hidden\" id=\"nome"
						+ i++ + "\" " + " value=\"" + s + "\">");
			}
		} catch (NullPointerException e) {
			output = output.concat(" <input type=\"hidden\" id=\"nome0\""
					+ " value=\"-\">");
		}
		i = 0;
		try {
			for (Integer in : rp.getLista_qtdMotivo()) {
				output = output.concat(" <input type=\"hidden\" id=\"qtdMotivo"
						+ i++ + "\" " + " value=\"" + in + "\">");
			}
		} catch (NullPointerException e) {
			output = output.concat(" <input type=\"hidden\" id=\"qtdMotivo0\""
					+ " value=\"-\">");
		}

		i = 0;

		for (Calendar c : rp.getLista_mes()) {
			output = output.concat(" <input type=\"hidden\" id=\"mes" + i++
					+ "\" " + " value=\"" + Conversao.calendarEmTexto(c)
					+ "\">");
		}

		i = 0;
		for (Float f : rp.getLista_valor()) {
			output = output.concat(" <input type=\"hidden\" id=\"valor" + i++
					+ "\" " + " value=\"" + f.toString() + "\">");
		}
	}
}