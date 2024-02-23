package com.visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controle.Log;
import com.controle.Registros;
import com.dados.RegistroDAO;
import com.dados.RespostaCertaDAO;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class TelaPrincipal  
{
	private static JFrame oTelaPrincipal;
	private static JPanel contentPane;
	private static JTextField oDsPergunta;
	
	public static JFrame getTelaPrincipal()
	{
		return oTelaPrincipal;
	}

	public static void main(String[] args)
	{		
		EventQueue.invokeLater( new Runnable() 
		{
			public void run() 
			{
				gerarRespostas();
			}
		});
	}
	
	public static void gerarRespostas()
	{
		try 
		{					
			Random geradorNumero = new Random();
			List<RegistroDAO> registros = Registros.obter();
			
			int indiceAleatorio = geradorNumero.nextInt( registros.size() );
			criarTelaPrincipal( registros, indiceAleatorio );
		} 
		catch ( Exception e ) 
		{
			Log.ErrorAndExit(e, null );
		}
	}
	public static void criarTelaPrincipal(List<RegistroDAO> registros,  int pIndiceCerto ) 
	{
		oTelaPrincipal = TelaModelo.criar( "Tela Principal" );
		oTelaPrincipal.setVisible( true );
	
		RespostaCertaDAO.setDsRespostaCerta(registros.get( pIndiceCerto ) );	
        oTelaPrincipal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		contentPane.setLayout( new BorderLayout( 0, 0 ) );
		oTelaPrincipal.setContentPane( contentPane );
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
				
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount( 5 );
		comboBox.setBackground( Color.CYAN );
		
		Random geradorRandomico = new Random();
		int opcao = geradorRandomico.nextInt( 2 );
		
		for (int i = 0; i < registros.size(); i++)
		{
			if( opcao == 1 ) comboBox.addItem( registros.get( i ).getDsPalavraPor().toUpperCase() );
			else
				comboBox.addItem( registros.get( i ).getDsPalavraEn().toUpperCase() );
		}
		
		comboBox.setBounds(0, 40, 184, 20);
		panel.add(comboBox);
		
		oDsPergunta = new JTextField( );
			
		if( opcao == 1 )
		{
			String palavraEN = registros.get( pIndiceCerto ).getDsPalavraEn();
			oDsPergunta.setText( ("'"+palavraEN+"' significa:").toUpperCase()  );
		}
		else
		{
			String palavraPO = registros.get( pIndiceCerto ).getDsPalavraPor();
			oDsPergunta.setText( ("'"+palavraPO+"' em inglês:").toUpperCase()  );
		}
		
		oDsPergunta.setFont(new Font("Tahoma", Font.BOLD, 13));
		oDsPergunta.setBackground(Color.CYAN);
		oDsPergunta.setEditable(false);
		oDsPergunta.setColumns(10);
		oDsPergunta.setBounds(0, 0, 184, 39);
		panel.add(oDsPergunta);
		//oDsPergunta.setColumns(10);
				
		JButton btEnviar = new JButton("Enviar");
		btEnviar.setBackground(new Color(0, 255, 0));
		btEnviar.setBounds(46, 117, 89, 23);
		panel.add(btEnviar);
		btEnviar.setFont(new Font("Arial", Font.PLAIN, 18));
		oTelaPrincipal.setVisible( true );
		btEnviar.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed( ActionEvent pActionEvent ) 
            {
            	String repostaCerta = opcao == 1 ? RespostaCertaDAO.getDsRespostaCerta().getDsPalavraPor() : RespostaCertaDAO.getDsRespostaCerta().getDsPalavraEn();
            	
            	if( repostaCerta.toUpperCase().equals( comboBox.getSelectedItem() ) )
            	{
            		try 
            		{
            			oTelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            			TelaAcertou.criarTelaAcertou();	
					} 
            		catch (IOException e2) 
            		{
						Log.ErrorAndExit(e2, null);
					}
            	}
            	else
            	{
            		oTelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		oTelaPrincipal.setVisible(false);
            		TelaErrou.run( repostaCerta );
            	}           	
            }
        });
		//oTelaPrincipal.setSize(200, 200);
		oTelaPrincipal.setVisible( true );
	}
}
