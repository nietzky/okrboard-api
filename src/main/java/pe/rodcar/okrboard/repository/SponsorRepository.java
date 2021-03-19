package pe.rodcar.okrboard.repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.rodcar.okrboard.entities.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long>{

    Optional<Sponsor> findById(Long id);

    boolean existsById(Long id);

}
