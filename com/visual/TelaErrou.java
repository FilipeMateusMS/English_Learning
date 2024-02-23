package com.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class TelaErrou
{

	private static JFrame oTelaErrou;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void run(String pRespostaCerta ) 
	{
		criarTelaErrou( pRespostaCerta );
		oTelaErrou.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public static void criarTelaErrou( String pRespostaCerta)
	{
		TelaPrincipal.getTelaPrincipal().setVisible( false );
		
		oTelaErrou = TelaModelo.criar( "Errou");
		
		oTelaErrou.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oTelaErrou.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		oTelaErrou.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.RED);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Tentar outra pergunta");
		btnNewButton.setBounds(10, 117, 154, 23);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						oTelaErrou.setVisible(false);
						TelaPrincipal.main( null );
					}
			
				});
		
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Configurar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				TelaDiretorio.run();
				oTelaErrou.setVisible( false );
			}
		});

		btnNewButton_1.setBounds(10, 85, 100, 20);
		panel.add(btnNewButton_1);
		
		JTextPane txtpnVocErrouA = new JTextPane();
		txtpnVocErrouA.setForeground(new Color(0, 0, 0));
		txtpnVocErrouA.setFont(new Font("Arial", Font.BOLD, 16));
		txtpnVocErrouA.setEditable(false);
		txtpnVocErrouA.setBackground(new Color(255, 0, 0));
		txtpnVocErrouA.setText("Voc\u00EA errou, a resposta certa era: \n\n" + pRespostaCerta.toUpperCase() );
		txtpnVocErrouA.setBounds(0, 0, 174, 106);
		panel.add(txtpnVocErrouA);
		
		oTelaErrou.setSize( 200,200);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - oTelaErrou.getWidth();
        int y = screenSize.height - oTelaErrou.getHeight();
        oTelaErrou.setLocation( x, y-50 );
	}

}