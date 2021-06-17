package ma.oncf.digital.entity.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;


public class FournisseurJson implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idFournisseur;
	private String codeTierce;
	private String raisonSociale;
	private String identifiantFiscal;
	private String ice;
	private String adressePostale;
	private String telephone;
	private String email;
	private int statusFournisseur;

	
	
	
	public FournisseurJson() {
		// TODO Auto-generated constructor stub
	}



	


	
	
	public Long getIdFournisseur() {
		return idFournisseur;
	}




	public void setIdFournisseur(Long idFournisseur) {
		this.idFournisseur = idFournisseur;
	}


   
	public String getCodeTierce() {
		return codeTierce;
	}



	public void setCodeTierce(String codeTierce) {
		this.codeTierce = codeTierce;
	}


	
	public String getRaisonSociale() {
		return raisonSociale;
	}



	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}


	
	public String getIdentifiantFiscal() {
		return identifiantFiscal;
	}



	public void setIdentifiantFiscal(String identifiantFiscal) {
		this.identifiantFiscal = identifiantFiscal;
	}


	
	public String getIce() {
		return ice;
	}



	public void setIce(String ice) {
		this.ice = ice;
	}


	
	public String getAdressePostale() {
		return adressePostale;
	}



	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	
	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



				
	
	public int getStatusFournisseur() {
		return statusFournisseur;
	}



	public void setStatusFournisseur(int statusFournisseur) {
		this.statusFournisseur = statusFournisseur;
	}


}
