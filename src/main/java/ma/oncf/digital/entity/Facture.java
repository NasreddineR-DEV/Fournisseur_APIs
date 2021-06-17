package ma.oncf.digital.entity;
// Generated Jun 14, 2019 9:44:28 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Facture generated by hbm2java
 */
@Entity
@Table(name = "facture", schema = "public")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Facture implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private int idFacture;
	private Fournisseur fournisseur;
	private Marche marche;
	private StatutFacture statutFacture;
	private String numeroFacture;
	private Date dateFacture;
	private String codeDevise;
	private BigDecimal montantRegle;
	private BigDecimal montantGlobal;
	private Date dateSituation;
	private Date dateCreation;
	private Date dateModification;
	private int statusFacture;

	public Facture() {
	}

	public Facture(int idFacture, Fournisseur fournisseur, Marche marche, StatutFacture statutFacture,
			String numeroFacture) {
		this.idFacture = idFacture;
		this.fournisseur = fournisseur;
		this.marche = marche;
		this.statutFacture = statutFacture;
		this.numeroFacture = numeroFacture;
	}

	public Facture(int idFacture, Fournisseur fournisseur, Marche marche, StatutFacture statutFacture,
			String numeroFacture, Date dateFacture, String codeDevise, BigDecimal montantRegle,
			BigDecimal montantGlobal, Date dateSituation, Date dateCreation, Date dateModification, int statusFacture) {
		this.idFacture = idFacture;
		this.fournisseur = fournisseur;
		this.marche = marche;
		this.statutFacture = statutFacture;
		this.numeroFacture = numeroFacture;
		this.dateFacture = dateFacture;
		this.codeDevise = codeDevise;
		this.montantRegle = montantRegle;
		this.montantGlobal = montantGlobal;
		this.dateSituation = dateSituation;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.statusFacture = statusFacture;
	}

	@Id

	@Column(name = "id_facture", unique = true, nullable = false)
	public int getIdFacture() {
		return this.idFacture;
	}

	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "fournisseur_id_fournisseur", nullable = false)
	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marche_id_marche", nullable = false)
	@JsonBackReference
	public Marche getMarche() {
		return this.marche;
	}

	public void setMarche(Marche marche) {
		this.marche = marche;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "situation", nullable = false)

	public StatutFacture getStatutFacture() {
		return this.statutFacture;
	}

	public void setStatutFacture(StatutFacture statutFacture) {
		this.statutFacture = statutFacture;
	}

	@Column(name = "numero_facture", nullable = false, length = 100)
	public String getNumeroFacture() {
		return this.numeroFacture;
	}

	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_facture", length = 13)
	public Date getDateFacture() {
		return this.dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	@Column(name = "code_devise", length = 3)
	public String getCodeDevise() {
		return this.codeDevise;
	}

	public void setCodeDevise(String codeDevise) {
		this.codeDevise = codeDevise;
	}

	@Column(name = "montant_regle", precision = 131089, scale = 0)
	public BigDecimal getMontantRegle() {
		return this.montantRegle;
	}

	public void setMontantRegle(BigDecimal montantRegle) {
		this.montantRegle = montantRegle;
	}

	@Column(name = "montant_global", precision = 131089, scale = 0)
	public BigDecimal getMontantGlobal() {
		return this.montantGlobal;
	}

	public void setMontantGlobal(BigDecimal montantGlobal) {
		this.montantGlobal = montantGlobal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_situation", length = 13)
	public Date getDateSituation() {
		return this.dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation", length = 13)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_modification", length = 13)
	public Date getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	@Column(name = "statut_facture")
	public int getStatusFacture() {
		return statusFacture;
	}

	public void setStatusFacture(int statusFacture) {
		this.statusFacture = statusFacture;
	}

}
