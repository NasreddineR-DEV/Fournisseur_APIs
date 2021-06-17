package ma.oncf.digital.entity.json;

import java.io.Serializable;

public class Identifiant implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mail;
	private String codetierce;
	
	
	public Identifiant() {
		// TODO Auto-generated constructor stub
	}


	public String getCodetierce() {
		return codetierce;
	}


	public void setCodetierce(String codetierce) {
		this.codetierce = codetierce;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	

}
