package pl.someday.rest_api_fishing_log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.rest_api_fishing_log.model.FishingSpot;

@Repository
public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long> {
    Optional<FishingSpot> findById(Long id);
}
