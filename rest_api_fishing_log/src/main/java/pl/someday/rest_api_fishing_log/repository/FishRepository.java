package pl.someday.rest_api_fishing_log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.someday.rest_api_fishing_log.model.Fish;

public interface FishRepository extends JpaRepository<Fish, Long>{

    Optional<Fish> findById(Long id); 
    
}
