package br.com.mercearia.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Conversao {
	static Calendar dataRetorno;

	public static void subtracaoCalendar(Calendar dia, String menos){
		int menosano = Integer.parseInt(menos.substring(0,2));
		int menosmes = Integer.parseInt(menos.substring(3,5));
		int menosdia = Integer.parseInt(menos.substring(6,8)); 
		dia.add(Calendar.DATE, -menosdia);
		dia.add(Calendar.MONTH, -menosmes);
		dia.add(Calendar.YEAR, -menosano);
		return;
	}
	
	public static void adicaoCalendar(Calendar dia, String mais){
		int maisano = Integer.parseInt(mais.substring(0,2));
		int maismes = Integer.parseInt(mais.substring(3,5));
		int maisdia = Integer.parseInt(mais.substring(6,8));
		dia.add(Calendar.DATE, maisdia);
		dia.add(Calendar.MONTH, maismes);
		dia.add(Calendar.YEAR, maisano);
		return;
	}
	
	
	
	public static String calendarEmTexto(Calendar calendar) {
		Date date = calendar.getTime();
		String resposta = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return resposta;
	}
	
	public static String calendarCEmTexto(Calendar calendar) {
		Date date = calendar.getTime();
		String resposta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return resposta;
	}
	
	public static Calendar textoHEmData(String textoEmData)
			throws ParseException {
		try{
			textoEmData = textoEmData.substring(8,10)+"/"+textoEmData.substring(5,7)+"/"+textoEmData.substring(0,4);
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(textoEmData);
			dataRetorno = Calendar.getInstance();
			dataRetorno.setTime(date);
			return dataRetorno;
		}catch(StringIndexOutOfBoundsException e){}
		return null;
	}
	
	public static Calendar textoEmData(String textoEmData)
			throws ParseException {
		//textoEmData = textoEmData.substring(8,10)+"/"+textoEmData.substring(5,7)+"/"+textoEmData.substring(0,4);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(textoEmData);
		dataRetorno = Calendar.getInstance();
		dataRetorno.setTime(date);
		return dataRetorno;
	}
	
	public static Calendar dateEmCalendar(Date dec)
			throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(dec);
		return c;
	}

	public static Calendar textoEmDataHora(String textoEmData)
			throws ParseException {
		try{
			textoEmData = textoEmData.substring(8,10)+"/"+textoEmData.substring(5,7)+"/"+textoEmData.substring(0,4);
		}catch(StringIndexOutOfBoundsException e){throw new ParseException(null, 0);}
		Date date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
				.parse(textoEmData);
		dataRetorno = Calendar.getInstance();
		dataRetorno.setTime(date);
		return dataRetorno;
	}

	public static Calendar textoEmDataHoraLocal(String textoEmData)
			throws ParseException {
		//2014-12-31T23%3A59
		textoEmData = textoEmData.substring(8,10)+"/"+textoEmData.substring(5,7)+"/"+textoEmData.substring(0,4) + 
		" " + textoEmData.substring(11,13) + ":" + textoEmData.substring(14,16) + ":" + textoEmData.substring(17,19); 
		Date date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
				.parse(textoEmData);
		dataRetorno = Calendar.getInstance();
		dataRetorno.setTime(date);
		return dataRetorno;
	}

	
	public static Calendar getCurrentTime() {
		Calendar calendar;
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = (Date) formatter.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Timestamp dateEmTimestamp(Date dataUtil) {
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//System.out.println("Data util " + df.format(dataUtil));
		Timestamp timeStamp = new Timestamp(dataUtil.getTime());
		//System.out.println("Data sql " + df.format(timeStamp));
		return timeStamp;
	}
}
