package ma.oncf.digital.repo.foo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.oncf.digital.entity.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {

	// facture by fournisseur
	@Query(value = "select fact.* FROM facture fact \n"
			+ "left join fournisseur fou on fact.fournisseur_id_fournisseur= fou.id_fournisseur "
			+ "left join marche mar on fact.marche_id_marche = mar.id_marche " + "WHERE fou.code_tier = :codeTierce  "
			+ "AND EXTRACT(YEAR FROM fact.date_facture) = :dateFacture order by fact.date_facture desc limit :limit ", nativeQuery = true)
	List<Facture> getByCriteria(@Param("codeTierce") String codeTierce, @Param("dateFacture") int dateFacture,
			@Param("limit") int limit);

	Facture findByIdFacture(int idFacture);

	// get facture by marche
	@Query(value = "select fact.* FROM facture fact \n"
			+ "left join fournisseur fou on fact.fournisseur_id_fournisseur= fou.id_fournisseur "
			+ "left join marche mar on fact.marche_id_marche = mar.id_marche "
			+ "WHERE mar.code_marche = :codeMarche limit :limit", nativeQuery = true)
	List<Facture> getByCriterial(@Param("codeMarche") String codeMarche,

			@Param("limit") int limit);

	@Query(value = "select Distinct EXTRACT(YEAR FROM fact.date_facture) FROM Facture as fact \n"
			+ "WHERE   fact.fournisseur_id_fournisseur=?1 ", nativeQuery = true)
	List<Date> getByCriterial(int idFournisseur);

	Facture getFactureByIdFacture(int idFacture);

	@Query(value = "SELECT count(*) from facture", nativeQuery = true)
	Integer getNbAllFacture();

	@Query(value = "SELECT count(*) from facture fact where fact.marche_id_marche=?1", nativeQuery = true)
	Integer getNbFactureByMarche(int idMarche);

}