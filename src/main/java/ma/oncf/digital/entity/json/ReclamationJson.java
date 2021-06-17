package ma.oncf.digital.entity.json;

import java.io.Serializable;

public class ReclamationJson implements Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private int idReclamation;
	private String mail;
	private String tel;
	private int idtype;
	private String contenu;
	private String objet;


	public ReclamationJson() {
		// TODO Auto-generated constructor stub
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public int getIdtype() {
		return idtype;
	}


	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public String getObjet() {
		return objet;
	}


	public void setObjet(String objet) {
		this.objet = objet;
	}

/*
	public int getIdReclamation() {
		return idReclamation;
	}


	public void setIdReclamation(int idReclamation) {
		this.idReclamation = idReclamation;
	}
	
	*/
	

}
