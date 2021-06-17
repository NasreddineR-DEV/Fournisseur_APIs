package ma.oncf.digital.repo.foo;

import ma.oncf.digital.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByFlag(Integer flag);



  
}