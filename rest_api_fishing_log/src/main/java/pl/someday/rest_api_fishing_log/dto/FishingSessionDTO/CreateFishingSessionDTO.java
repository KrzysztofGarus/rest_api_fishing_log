package pl.someday.rest_api_fishing_log.dto.FishingSessionDTO;

import java.time.LocalDate;

public record CreateFishingSessionDTO(
    LocalDate date,
    Long userId,
    Long fishingSpotId
) {
    
}
