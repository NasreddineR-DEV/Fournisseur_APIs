package ma.oncf.digital.entity;
// Generated Jun 14, 2019 9:44:28 PM by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * StatutFacture generated by hbm2java
 */
@Entity
@Table(name="statut_facture"
    ,schema="public"
)
public class StatutFacture  implements java.io.Serializable {


     private int idStatut;
     private String statutLibelleCourt;
     private String statutLibelleLong;
   //  private Set<Facture> factures = new HashSet(0);

    public StatutFacture() {
    }

	
    public StatutFacture(int idStatut) {
        this.idStatut = idStatut;
    }
    public StatutFacture(int idStatut, String statutLibelleCourt, String statutLibelleLong) {
       this.idStatut = idStatut;
       this.statutLibelleCourt = statutLibelleCourt;
       this.statutLibelleLong = statutLibelleLong;
   //    this.factures = factures;
    }
   
    @Id
    @Column(name="id_statut", unique=true, nullable=false)
    public int getIdStatut() {
        return this.idStatut;
    }
    
    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    
    @Column(name="statut_libelle_court", length=50)
    public String getStatutLibelleCourt() {
        return this.statutLibelleCourt;
    }
    
    public void setStatutLibelleCourt(String statutLibelleCourt) {
        this.statutLibelleCourt = statutLibelleCourt;
    }

    
    @Column(name="statut_libelle_long")
    public String getStatutLibelleLong() {
        return this.statutLibelleLong;
    }
    
    public void setStatutLibelleLong(String statutLibelleLong) {
        this.statutLibelleLong = statutLibelleLong;
    }

//@OneToMany(fetch=FetchType.LAZY, mappedBy="statutFacture")
//@JsonManagedReference
//
//public Set<Facture> getFactures() {
//        return this.factures;
//    }
//
//    public void setFactures(Set<Facture> factures) {
//        this.factures = factures;
//    }




}


