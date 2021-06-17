package ma.oncf.digital.entity.json;


import java.util.Date;


public class MarcheJson  {
	
	private int idMarche;
    private String codeMarche;
    private Date dateSignatureMarche;
    private Date dateCreation;
    private Date dateModification;
    private int statusMarche;

	
	
	
	public int getIdMarche() {
		return idMarche;
	}




	public void setIdMarche(int idMarche) {
		this.idMarche = idMarche;
	}



	public String getCodeMarche() {
		return codeMarche;
	}




	public void setCodeMarche(String codeMarche) {
		this.codeMarche = codeMarche;
	}




	public Date getDateSignatureMarche() {
		return dateSignatureMarche;
	}




	public void setDateSignatureMarche(Date dateSignatureMarche) {
		this.dateSignatureMarche = dateSignatureMarche;
	}




	public Date getDateCreation() {
		return dateCreation;
	}




	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}




	public Date getDateModification() {
		return dateModification;
	}




	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}



	public int getStatusMarche() {
		return statusMarche;
	}




	public void setStatusMarche(int statusMarche) {
		this.statusMarche = statusMarche;
	}




	public MarcheJson() {
		// TODO Auto-generated constructor stub
	}

	

}
