package com.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

import com.dados.SistemaDAO;

public class Log 
{
	private static final String oDsCaminhoArquivoDefault = SistemaDAO.getDsCaminhoErro();
	
	public static void Error( Exception e, String pDsCaminhoArquivo ) throws Exception
	{
		if( pDsCaminhoArquivo == null || pDsCaminhoArquivo.isEmpty() ) pDsCaminhoArquivo = oDsCaminhoArquivoDefault;
		escreverArquivo( e, "Erro - Ocorreu um erro", pDsCaminhoArquivo );
        
		System.err.println( e );
		throw e;
	}
	public static void ErrorAndExit( Exception e, String pDsCaminhoArquivo )
	{
		if( pDsCaminhoArquivo == null || pDsCaminhoArquivo.isEmpty() ) pDsCaminhoArquivo = oDsCaminhoArquivoDefault;
		escreverArquivo( e, "Erro - Ocorreu um erro e o sistema foi finalizado", pDsCaminhoArquivo );
        
		System.err.println( e );
		System.exit( 1 );
	}
	public static void Warning( Exception e, String pDsCaminhoArquivo )
	{
		if( pDsCaminhoArquivo == null || pDsCaminhoArquivo.isEmpty() ) pDsCaminhoArquivo = oDsCaminhoArquivoDefault;
		escreverArquivo( e, "Alerta - Ocorreu um erro reversível,", pDsCaminhoArquivo );
		System.err.println( e );
	}
	
	private static void escreverArquivo( Exception e, String dsCabecalho, String pDsCaminhoArquivo )
	{
		try
		{
			LocalDate bLocalDate = LocalDate.now();
			LocalTime bLocalTime = LocalTime.now();
			
			StackTraceElement[] stackTrace = e.getStackTrace();
			String stack = "";
			for (StackTraceElement stackTraceElement : stackTrace)
			{
				stack += stackTraceElement.toString() + "\n";
			}
			
			String texto =
			"=================================================================================================\n"
			+ bLocalDate + " as " +bLocalTime + " - " + dsCabecalho + "\n"
			+ e.getMessage() + "\n"
			+ "Stack Trace: \n"
			+ stack
			+ "=================================================================================================\n";

			File arquivo = new File( pDsCaminhoArquivo );
			if( !arquivo.exists() ) 
			{
				int ultimaBarra = pDsCaminhoArquivo.lastIndexOf( "\\" ) + 1;
				
				String diretorio = pDsCaminhoArquivo.substring(0 , ultimaBarra);
				
				new File( diretorio ).mkdirs();
				new File( pDsCaminhoArquivo ).createNewFile();
			}
			
			OutputStreamWriter fileOut = new OutputStreamWriter(new FileOutputStream(arquivo, true), StandardCharsets.ISO_8859_1);

			fileOut.write(texto);
			fileOut.flush();
			fileOut.close();
		} catch (Exception t)
		{
			System.out.println(pDsCaminhoArquivo);
			t.printStackTrace();
		}
	}
}
