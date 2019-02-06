package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Part;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findById(Long id);

    @Query("SELECT p FROM parts p WHERE p.manufacturer = ?1 AND p.model = ?2 AND p.quantity > 0")
    List<Part> findPartsByManufacturerAndModel(String manufacturer, String model);


}
