package ma.oncf.digital.repo.foo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.oncf.digital.entity.Connexion;
import ma.oncf.digital.entity.Fournisseur;

@Repository
public interface ConnexionRepository extends JpaRepository<Connexion, String> {

	Connexion findFirstByLoginAndPassword(String mail, String password);

	Connexion findByFournisseur(Fournisseur fournisseur);

	Connexion findByLogin(String username);

	@Query(value = "select count(fact.*) FROM facture fact \n"
			+ "WHERE fact.fournisseur_id_fournisseur = :idFournisseur and statut_facture<>0 ", nativeQuery = true)
	Integer getCountFact(@Param("idFournisseur") Integer idFournisseur);
	
	@Query(value = "select count(fact.*) FROM facture fact \n"
			+ "WHERE fact.fournisseur_id_fournisseur = :idFournisseur and statut_facture<>0  and situation=1", nativeQuery = true)
	Integer getCountFactEncourTrait(@Param("idFournisseur") Integer idFournisseur);
	
	@Query(value = "select count(fact.*) FROM facture fact \n"
			+ "WHERE fact.fournisseur_id_fournisseur = :idFournisseur and statut_facture<>0  and situation=2", nativeQuery = true)
	Integer getCountFactEnCourRegle(@Param("idFournisseur") Integer idFournisseur);
	
	@Query(value = "select count(fact.*) FROM facture fact \n"
			+ "WHERE fact.fournisseur_id_fournisseur = :idFournisseur and statut_facture<>0  and situation=3", nativeQuery = true)
	Integer getCountFactEnr(@Param("idFournisseur") Integer idFournisseur);
	
	@Query(value = "select count(fact.*) FROM facture fact \n"
			+ "WHERE fact.fournisseur_id_fournisseur = :idFournisseur and statut_facture<>0  and situation=4", nativeQuery = true)
	Integer getCountFactRegle(@Param("idFournisseur") Integer idFournisseur);
	
	@Query(value = "select count(mar.*) FROM marche mar \n"
			+ "WHERE mar.fournisseur_id_fournisseur = :idFournisseur and statut_marche<>0", nativeQuery = true)
	Integer getCountMarche(@Param("idFournisseur") Integer idFournisseur);

}