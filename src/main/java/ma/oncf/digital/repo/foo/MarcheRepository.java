package ma.oncf.digital.repo.foo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.oncf.digital.entity.Fournisseur;
import ma.oncf.digital.entity.Marche;

@Repository
public interface MarcheRepository extends JpaRepository<Marche, Integer> {

	List<Marche> findByFournisseur(Fournisseur fournisseur);

	Marche findByIdMarche(int idMarche);

	@Query(value = "select distinct mar.* FROM marche mar \n"
			+ "left join fournisseur fou on mar.fournisseur_id_fournisseur= fou.id_fournisseur "
			+ "left join facture fact on fact.marche_id_marche = mar.id_marche WHERE fou.code_tier = :codeTierce  "
			+ "AND EXTRACT(YEAR FROM mar.date_signature_marche) = :dateMarche AND mar.statut_marche<>0"
			+ "order by mar.date_signature_marche desc limit 100 ", nativeQuery = true)
	List<Marche> getByFournisseur(@Param("codeTierce") String codeTierce, @Param("dateMarche") int dateMarche);

	@Query(value = "select Distinct EXTRACT(YEAR FROM mar.date_signature_marche) FROM marche mar \n"
			+ "WHERE   mar.fournisseur_id_fournisseur=?1 ", nativeQuery = true)
	List<Date> getDateMarche(int idFournisseur);

	Marche getMarcheByIdMarche(int idMarche);

	@Query(value = "SELECT count(*) from facture fact where  fact.marche_id_marche=:idMarche AND statut_facture<>0", nativeQuery = true)
	Integer getNbFactureInMarche(int idMarche);

	// back-office
	@Query(value = "select  mar.* FROM marche mar \n"
			+ "left join fournisseur fou on mar.fournisseur_id_fournisseur= fou.id_fournisseur "
			+ "left join facture fact on fact.marche_id_marche = mar.id_marche WHERE fou.code_tier = :codeTierce  "
			+ "AND EXTRACT(YEAR FROM mar.date_signature_marche) = :dateMarche "
			+ "order by ?#{#pageable},mar.id_marche   ", nativeQuery = true)
	Page<Marche> getPageByFournisseur(@Param("codeTierce") String codeTierce, @Param("dateMarche") int dateMarche,
			Pageable pageable);

	@Query(value = "SELECT count(*) from marche", nativeQuery = true)
	Integer getNbAllMarche();
	/*
	 * @Query(value =
	 * "SELECT count(*) from marche where fournisseur_id_fournisseure=:idFournisseur"
	 * , nativeQuery = true) Integer getNbAllMarcheById(int idFournisseur);
	 */
}