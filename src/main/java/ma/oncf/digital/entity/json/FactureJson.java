package ma.oncf.digital.entity.json;


import java.math.BigDecimal;
import java.util.Date;

import ma.oncf.digital.entity.StatutFacture;


public class FactureJson  {
	
	private int idFacture;
    private StatutFacture statutFacture;
    private String numeroFacture;
    private Date dateFacture;
    private Date dateSituation;
    private String codeDevise;
    private BigDecimal montantRegle;
    private BigDecimal montantGlobal;
    private int statusFacture;

	
	
	
	public FactureJson() {
		// TODO Auto-generated constructor stub
	}




	public StatutFacture getStatutFacture() {
		return statutFacture;
	}




	public void setStatutFacture(StatutFacture statutFacture) {
		this.statutFacture = statutFacture;
	}




	public String getNumeroFacture() {
		return numeroFacture;
	}




	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}




	public Date getDateFacture() {
		return dateFacture;
	}




	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}




	public String getCodeDevise() {
		return codeDevise;
	}




	public void setCodeDevise(String codeDevise) {
		this.codeDevise = codeDevise;
	}




	public BigDecimal getMontantRegle() {
		return montantRegle;
	}




	public void setMontantRegle(BigDecimal montantRegle) {
		this.montantRegle = montantRegle;
	}




	public BigDecimal getMontantGlobal() {
		return montantGlobal;
	}




	public void setMontantGlobal(BigDecimal montantGlobal) {
		this.montantGlobal = montantGlobal;
	}




	public Date getDateSituation() {
		return dateSituation;
	}




	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}





	public int getStatusFacture() {
		return statusFacture;
	}




	public void setStatusFacture(int statusFacture) {
		this.statusFacture = statusFacture;
	}




	public int getIdFacture() {
		return idFacture;
	}




	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}



	

}
