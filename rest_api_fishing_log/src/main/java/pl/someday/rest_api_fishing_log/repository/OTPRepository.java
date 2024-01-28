package pl.someday.rest_api_fishing_log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.someday.rest_api_fishing_log.model.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long>{

    Optional<OTP> findByOneTimePassword(int oneTimePassword);
    
}
