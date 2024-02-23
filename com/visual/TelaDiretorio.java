package com.visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.controle.Log;
import com.dados.SistemaDAO;

public class TelaDiretorio  
{
	private static JFrame oTelaDiretorio;
	private static JPanel contentPane;
	private static JTextField txtSelecioneOArquivo;
	
	public static void run()
	{		
		EventQueue.invokeLater( new Runnable() 
		{
			public void run() 
			{
				try 
				{
					obterDiretorio();
				} 
				catch ( Exception e ) 
				{
					Log.ErrorAndExit(e, null);
				}
			}
		});
		return;			
	}
	
	public static void obterDiretorio() 
	{
		oTelaDiretorio = TelaModelo.criar( "Diretorio" );
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) 
		{
			Log.Warning( e, null );
		}
		
		oTelaDiretorio.setBounds(250, 250, 400, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		oTelaDiretorio.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtSelecioneOArquivo = new JTextField();
		txtSelecioneOArquivo.setEditable(false);
		txtSelecioneOArquivo.setBounds(10, 5, 245, 20);
		txtSelecioneOArquivo.setText("Selecione o arquivo .csv para ser utilizado");
		panel.add(txtSelecioneOArquivo);
		txtSelecioneOArquivo.setColumns(10);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(272, 4, 89, 23);
		panel.add(btnSelecionar);
		btnSelecionar.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(0, 36, 440, 254);
				//panel.add(fileChooser);
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "Arquivo .csv", "csv");
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setAcceptAllFileFilterUsed(false);
				int returnVal = fileChooser.showOpenDialog(null);
		        if( returnVal == JFileChooser.APPROVE_OPTION ) 
		        {
		           SistemaDAO.setDsCaminhoArquivoDados( fileChooser.getSelectedFile().getAbsolutePath() );
		           try 
		           {
		        	   setarWorkspace( SistemaDAO.getDsCaminhoArquivoDados() );
		           } 
		           catch( Exception e )
		           {
		        	   Log.ErrorAndExit( e, null );
		           }
		           TelaPrincipal.main( null);	
		           oTelaDiretorio.setVisible( false );}
				}
		});
		oTelaDiretorio.setVisible(true);     
	}
	public static void setarWorkspace( String pNewCaminho ) throws Exception 
	{
		try
		{
			Properties prop = new Properties();
            prop.setProperty( "dsCaminhoArquivoDados", pNewCaminho );
            prop.store(new FileOutputStream( new File( SistemaDAO.getDS_CAMINHO_ARQ_PROP() ) ), null);
		}
		catch( Exception e )
		{
			Log.Error(e, null);
		}
	}
}
