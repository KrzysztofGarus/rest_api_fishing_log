package pl.someday.rest_api_fishing_log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.someday.rest_api_fishing_log.model.FishingSession;

public interface FishingSessionRepository extends JpaRepository<FishingSession, Long>{

}
