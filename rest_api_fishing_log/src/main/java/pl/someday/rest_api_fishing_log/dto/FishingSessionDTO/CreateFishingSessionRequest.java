package pl.someday.rest_api_fishing_log.dto.FishingSessionDTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public record CreateFishingSessionRequest(
    @NotNull(message = "Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date,
    @NotNull(message = "Fishing spot id cannot be null")
    Long fishingSpotId
) {
    
}
