package ma.oncf.digital.repo.foo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.oncf.digital.entity.Reclamation;

@Repository
public interface ReclemationRepository extends JpaRepository<Reclamation, Integer> {

}