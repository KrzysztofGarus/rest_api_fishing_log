package pl.someday.rest_api_fishing_log.dto.FishingSessionDTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record PatchFishingSessionRequest(
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date,
    
    Long fishingSpotId
) {
    
}
