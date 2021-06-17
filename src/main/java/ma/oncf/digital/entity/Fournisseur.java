package ma.oncf.digital.entity;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="fournisseur"
    ,schema="public"
)
public class Fournisseur  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -8056440663001254869L;
	private int idFournisseur;
     private String codeTier;
     private String raisonSociale;
     private String identifiantFiscal;
     private String ice;
     private String adressePostale;
     private String telephone;
     private String email;
     private Date dateCreation;
     private Date dateModification;
     private int statusFournisseur;

    public Fournisseur() {
    }

	
    public Fournisseur(int idFournisseur, String codeTier, String raisonSociale) {
        this.idFournisseur = idFournisseur;
        this.codeTier = codeTier;
        this.raisonSociale = raisonSociale;
    }

    public Fournisseur(int idFournisseur, String codeTier, String raisonSociale, String identifiantFiscal, String ice, String adressePostale, String telephone, String email, Date dateCreation, Date dateModification, int statusFournisseur) {
        this.idFournisseur = idFournisseur;
        this.codeTier = codeTier;
        this.raisonSociale = raisonSociale;
        this.identifiantFiscal = identifiantFiscal;
        this.ice = ice;
        this.adressePostale = adressePostale;
        this.telephone = telephone;
        this.email = email;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.statusFournisseur = statusFournisseur;
    }

    @Id

    
    @Column(name="id_fournisseur", unique=true, nullable=false)
    public int getIdFournisseur() {
        return this.idFournisseur;
    }
    
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    
    @Column(name="code_tier", nullable=false, length=12)
    public String getCodeTier() {
        return this.codeTier;
    }
    
    public void setCodeTier(String codeTier) {
        this.codeTier = codeTier;
    }

    
    @Column(name="raison_sociale", nullable=false)
    public String getRaisonSociale() {
        return this.raisonSociale;
    }
    
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    
    @Column(name="identifiant_fiscal", length=12)
    public String getIdentifiantFiscal() {
        return this.identifiantFiscal;
    }
    
    public void setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
    }

    
    @Column(name="ice", length=15)
    public String getIce() {
        return this.ice;
    }
    
    public void setIce(String ice) {
        this.ice = ice;
    }

    
    @Column(name="adresse_postale")
    public String getAdressePostale() {
        return this.adressePostale;
    }
    
    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    
    @Column(name="telephone", length=15)
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    
    @Column(name="email", length=80)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_creation", length=29)
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_modification", length=29)
    public Date getDateModification() {
        return this.dateModification;
    }
    
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @Column(name="statut_fournisseur")
	public int getStatusFournisseur() {
		return statusFournisseur;
	}


	public void setStatusFournisseur(int statusFournisseur) {
		this.statusFournisseur = statusFournisseur;
	}



}


