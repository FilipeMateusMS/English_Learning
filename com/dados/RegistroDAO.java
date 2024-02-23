package com.dados;

public class RegistroDAO
{
	private Integer qtAcertos;
	private String dsPalavraEn;
	private String dsPalavraPor;
	
	public RegistroDAO( String pNmEN, String pNmPor, String dsAcertos ) 
	{
		qtAcertos = Integer.valueOf( dsAcertos );
		dsPalavraEn = pNmEN;
		dsPalavraPor = pNmPor;
	}
	public RegistroDAO() 
	{
	}
	public Integer getQtAcertos() 
	{
		return qtAcertos;
	}
	public void setQtAcertos(Integer qtAcertos) {
		this.qtAcertos = qtAcertos;
	}
	public String getDsPalavraEn() {
		return dsPalavraEn;
	}
	public void setDsPalavraEn(String dsPalavraEn) {
		this.dsPalavraEn = dsPalavraEn;
	}
	public String getDsPalavraPor() {
		return dsPalavraPor;
	}
	public void setDsPalavraPor(String dsPalavraPor) {
		this.dsPalavraPor = dsPalavraPor;
	}	
}
