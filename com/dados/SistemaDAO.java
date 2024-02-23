package com.dados;

import java.nio.file.Paths;
import java.time.LocalDate;

public class SistemaDAO 
{
	private static final String DS_CAMINHO_ARQ_PROP = "\\src\\recursos\\config\\workspace.properties";
	private static final String DS_DIRETORIO_ARQ_PROP = "\\src\\recursos\\config";
	private static String dsCaminhoArquivo;
	private static final String dsCaminhoArqErro = "\\src\\recursos\\log\\Error";
	private static final String DS_CAMIHO_ICONE = "/recursos/imagens/Icone_Traducao.png";
	private static final String dsPath = Paths.get( "" ).toAbsolutePath().toString();
	
	public static String getDsDiretorioArqProp() 
	{
		return DS_DIRETORIO_ARQ_PROP;
	}

	public static String getDsCamihoIcone() 
	{
		return DS_CAMIHO_ICONE;
	}

	public static String getDS_CAMINHO_ARQ_PROP() 
	{
		return dsPath + DS_CAMINHO_ARQ_PROP;
	}

	public static String getDsCaminhoArquivoDados() 
	{
		return dsCaminhoArquivo;
	}

	public static void setDsCaminhoArquivoDados(String dsCaminhoArquivo) 
	{
		SistemaDAO.dsCaminhoArquivo = dsCaminhoArquivo;
	}

	public static String getDsCaminhoErro() 
	{
		return dsPath + dsCaminhoArqErro + LocalDate.now() + ".txt";
	}

}
