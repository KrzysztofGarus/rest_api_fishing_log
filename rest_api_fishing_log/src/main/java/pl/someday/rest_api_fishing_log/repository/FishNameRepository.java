package pl.someday.rest_api_fishing_log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.someday.rest_api_fishing_log.model.FishName;

public interface FishNameRepository extends JpaRepository<FishName, Long> {

    Optional<FishName> findById(Long id);
    
}
