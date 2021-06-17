package ma.oncf.digital.repo.foo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.oncf.digital.entity.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

	// Fournisseur findByIdentifiantFiscal(String identifiantFiscal);
	// Fournisseur findByIdFournisseur(Long idFournisseur);
	@Override
	Page<Fournisseur> findAll(Pageable pageable);
	// Fournisseur findByIdentifiantFiscal(int idf);

	// Fournisseur findByIdentifiantFiscal(String idf);
	Fournisseur findByEmail(String email);

	@Query(value = "SELECT count(*) from fournisseur", nativeQuery = true)
	Integer getNbAllFournisseur();

}