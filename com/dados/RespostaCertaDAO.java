package com.dados;

public class RespostaCertaDAO 
{
	private static RegistroDAO dsRespostaCerta;
	private static Integer opcao;
		
	public static Integer getOpcao() {
		return opcao;
	}
	public static void setOpcao(Integer opcao) {
		RespostaCertaDAO.opcao = opcao;
	}
	public static RegistroDAO getDsRespostaCerta() 
	{
		return dsRespostaCerta;
	}
	public static void setDsRespostaCerta(RegistroDAO dsRespostaCerta) 
	{
		RespostaCertaDAO.dsRespostaCerta = dsRespostaCerta;
	}
}
