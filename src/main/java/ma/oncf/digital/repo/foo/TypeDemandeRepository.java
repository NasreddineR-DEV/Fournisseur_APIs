package ma.oncf.digital.repo.foo;

import ma.oncf.digital.entity.TypeDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Integer> {
    List<TypeDemande> findByFlag(Integer flag);


  
}