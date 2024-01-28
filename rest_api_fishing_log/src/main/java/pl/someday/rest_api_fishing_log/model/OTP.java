package pl.someday.rest_api_fishing_log.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oneTimePassword;

    private LocalDateTime expirationDate;

}
