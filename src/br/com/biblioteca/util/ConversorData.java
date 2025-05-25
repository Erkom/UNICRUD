package br.com.biblioteca.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitária para conversão e manipulação de datas.
 * 
 * @author N688-Ambiente de dados
 * @version 1.0
 */
public class ConversorData {
    
    private static final SimpleDateFormat SDF_DATA = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_DATA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat SDF_SQL = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date converterStringParaData(String dataStr) {
        if (dataStr == null || dataStr.trim().isEmpty()) {
            return null;
        }
        
        try {
            return SDF_DATA.parse(dataStr);
        } catch (ParseException e) {
            System.err.println("Erro ao converter string para data: " + e.getMessage());
            return null;
        }
    }
    
    public static String converterDataParaString(Date data) {
        if (data == null) {
            return "";
        }
        
        return SDF_DATA.format(data);
    }
    
    public static String converterDataHoraParaString(Date data) {
        if (data == null) {
            return "";
        }
        
        return SDF_DATA_HORA.format(data);
    }
    
    public static String converterDataParaSQL(Date data) {
        if (data == null) {
            return "";
        }
        
        return SDF_SQL.format(data);
    }
    
    public static Date adicionarDias(Date data, int dias) {
        if (data == null) {
            return null;
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        
        return cal.getTime();
    }
    
    public static long calcularDiferencaDias(Date dataInicio, Date dataFim) {
        if (dataInicio == null || dataFim == null) {
            return 0;
        }
        
        long diff = dataFim.getTime() - dataInicio.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
    
    public static Date obterDataAtual() {
        return new Date();
    }
    
    public static String obterDataAtualFormatada() {
        return converterDataParaString(new Date());
    }
    
    public static String obterDataHoraAtualFormatada() {
        return converterDataHoraParaString(new Date());
    }
}
