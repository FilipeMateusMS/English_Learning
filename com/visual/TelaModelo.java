package com.visual;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.controle.Log;
import com.dados.SistemaDAO;

public class TelaModelo
{
	private TelaModelo()
	{
	}
	
	private static JFrame telaModelo;
	
	public static JFrame criar( String nmJFrame )
	{
		try
		{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch( UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e )
		{
			Log.Warning(e, null);
		}
		
		telaModelo = new JFrame( nmJFrame );
		telaModelo.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		telaModelo.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - telaModelo.getWidth();
        int y = screenSize.height - telaModelo.getHeight();
        
        telaModelo.setBounds( x  - 205, y - 245 , 200, 200 );
                
       
        InputStream input = TelaModelo.class.getResourceAsStream(SistemaDAO.getDsCamihoIcone());
        try {
        	BufferedImage bufferedImage = ImageIO.read(input);
        	Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
        	telaModelo.setIconImage(image);
        } catch (IOException e)
        {
        	Log.Warning(e, null);
        }
	         
        return telaModelo;
	}
}
