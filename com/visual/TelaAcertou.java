package com.visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controle.Log;
import com.controle.Registros;
import com.dados.RespostaCertaDAO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class TelaAcertou 
{
	private static JFrame oTelaAcertou;
	private static JPanel contentPane;
	private static JTextPane txtpnParabnsVocAcertou;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater( new Runnable() 
		{
			public void run() 
			{
				try 
				{
					criarTelaAcertou();
				} 
				catch ( Exception e ) 
				{
					Log.ErrorAndExit(e, null);
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public static void criarTelaAcertou() throws IOException 
	{
		Registros.acertou( RespostaCertaDAO.getDsRespostaCerta() );
		
		TelaPrincipal.getTelaPrincipal().setVisible( false );
		
		oTelaAcertou =  TelaModelo.criar( "Acertou" );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		oTelaAcertou.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Tentar outra pergunta");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(10, 117, 154, 23);
		btnNewButton.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						oTelaAcertou.setVisible(false);
						TelaPrincipal.main( null);
					}
			
				});
		panel.add(btnNewButton);
		
		txtpnParabnsVocAcertou = new JTextPane();
		txtpnParabnsVocAcertou.setForeground(new Color(0, 0, 0));
		txtpnParabnsVocAcertou.setBackground(new Color(0, 255, 0));
		txtpnParabnsVocAcertou.setFont(new Font("Arial", Font.BOLD, 20));
		txtpnParabnsVocAcertou.setEditable(false);
		txtpnParabnsVocAcertou.setText("Parab\u00E9ns voc\u00EA acertou!");
		txtpnParabnsVocAcertou.setBounds(10, 35, 154, 81);
		panel.add(txtpnParabnsVocAcertou);
		
		JButton btnNewButton_1 = new JButton("Configurar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				TelaDiretorio.run();
				oTelaAcertou.setVisible( false );
			}
		});
		btnNewButton_1.setBounds(70, 11, 100, 20);
        oTelaAcertou.setVisible( true );
	}
}
