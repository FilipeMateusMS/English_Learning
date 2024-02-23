package com.controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.dados.RegistroDAO;
import com.dados.SistemaDAO;
import com.visual.TelaDiretorio;

public class Registros 
{	
	private static boolean createDirectoryIfNotExists( String dsCaminhoPasta ) throws IOException
	{
	    File diretorio = new File( SistemaDAO.getDS_CAMINHO_ARQ_PROP() );
	    
	    if( !diretorio.exists() ) 
		{
	    	new File( SistemaDAO.getDsDiretorioArqProp() ).mkdirs();
		    return true;
		}
	    return false;
	}
	
	private static String obterCaminhoDados() throws Exception
	{
		String lCaminhoArquivoProp = SistemaDAO.getDS_CAMINHO_ARQ_PROP();
		createDirectoryIfNotExists( lCaminhoArquivoProp );
		
		File arquivo = new File( lCaminhoArquivoProp );
		
		if( !arquivo.exists() ) 
		{
		    try 
		    {
		        arquivo.createNewFile();
		      		        
		        FileWriter escritor = new FileWriter(arquivo);
		        escritor.write( "dsCaminhoArquivoDados=" );
		        escritor.close();
		    } 
		    catch (Exception e) 
		    {
		        Log.ErrorAndExit( e, null );
		    }
		}
		Properties prop = new Properties();
        FileInputStream file = new FileInputStream( lCaminhoArquivoProp );
        prop.load( file );
        file.close();

        String dsCaminhoArquivo = prop.getProperty( "dsCaminhoArquivoDados" );
        
		return dsCaminhoArquivo;
	}
	
	public static List<RegistroDAO> obter() throws Exception 
	{
		String dsCaminhoDados = obterCaminhoDados();
		
		if( dsCaminhoDados == null || dsCaminhoDados.isEmpty() || ! new File( dsCaminhoDados ).exists() )
		{
			TelaDiretorio.run();
			while( dsCaminhoDados == null || dsCaminhoDados.isEmpty() )
			{
				Thread.currentThread().notify();
			}
		}
		SistemaDAO.setDsCaminhoArquivoDados(dsCaminhoDados);
		List<RegistroDAO> registros = obterTodosRegistros( dsCaminhoDados );
		List<RegistroDAO> registrosSelecionados = selecionarRegistros( 5, registros );
		return registrosSelecionados;
	}
	
	public static void acertou( RegistroDAO pRegistro ) throws IOException
	{
		File inputFile = new File( SistemaDAO.getDsCaminhoArquivoDados() );
        File tempFile = new File( "temp.csv" );

        BufferedReader reader = new BufferedReader( new FileReader( inputFile ) );
        BufferedWriter writer = new BufferedWriter( new FileWriter( tempFile ) );

        String currentLine;

        while( ( currentLine = reader.readLine() ) != null ) 
        {
            // Dividir a linha em colunas
            String[] columns = currentLine.split( ";" );
            // Verificar se � a linha correta
            if( columns[0].equals( pRegistro.getDsPalavraEn() ) ) 
            {
                // Alterar o valor da coluna
                columns[2] = String.valueOf( Integer.valueOf( columns[2] ) + 1 );
            }
             
            // Juntar as colunas de volta em uma linha
            writer.write(String.join( ";", columns ) + System.getProperty( "line.separator" ));
        }
        writer.close(); 
        reader.close(); 

        // Deletar o arquivo original
        inputFile.delete();
        // Renomear o arquivo tempor�rio para o nome original
       tempFile.renameTo( inputFile );
    }
	
	public static List<RegistroDAO> selecionarRegistros( int qtRegistros, List<RegistroDAO> pRegistros ) throws IOException 
	{
		List<RegistroDAO> registros = pRegistros;
	    List<Integer> indices = new ArrayList<>();
	    
	    int qtTamanhoTabela = registros.size();
	    for (int i = 0; i < qtRegistros; i++) 
	    {
	    	Random bGerador = new Random();
	    	int bNumero = bGerador.nextInt( qtTamanhoTabela );
	    	if( registros.get( i ).getQtAcertos() >= 10 )
	    	{
	    		i--;
	    		continue;
	    	}
	    	if( !indices.contains( bNumero ) ) 
	    	{
	    		indices.add( bNumero );
	    		continue;
	    	}
	    	i--;
	    }
	    
	    List<RegistroDAO> registrosSelecionados = new ArrayList<>();
	    RegistroDAO registroSelecionado = new RegistroDAO();
	   	for( int i = 0; i < qtRegistros; i++ ) 
	   	{
	   		registroSelecionado = registros.get( indices.get( i ) );
	   		registrosSelecionados.add( registroSelecionado );
	   	}   
	   	return registrosSelecionados;
	}
	
	public static List<RegistroDAO> obterTodosRegistros( String pNmArquivo ) throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader( SistemaDAO.getDsCaminhoArquivoDados() ) );
	    String linha;
	    List<RegistroDAO> registros = new ArrayList<>();
	    while( ( linha = br.readLine() ) != null ) 
	    {
	        String[] campos = linha.split( ";" );
	        String nmEn = campos[0];
	        if( nmEn == null || nmEn.isEmpty() ) continue;
	        String nmPor = campos[1];
	        if( nmPor == null || nmPor.isEmpty() ) continue;
	        String qtAcertos = campos[2];
	        if( qtAcertos == null || qtAcertos.isEmpty() ) continue;
	        
	        registros.add( new RegistroDAO( campos[0], campos[1],campos[2] ) );
	    }
	    
	    br.close();
	    return registros;
	}
}
