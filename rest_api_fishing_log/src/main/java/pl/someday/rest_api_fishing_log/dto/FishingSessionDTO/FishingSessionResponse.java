package pl.someday.rest_api_fishing_log.dto.FishingSessionDTO;

import java.time.LocalDate;
import java.util.List;

import pl.someday.rest_api_fishing_log.dto.FishDTO.FishResponse;

public record FishingSessionResponse(
        
        Long id,
        
        LocalDate date,
        
        String spotName,

        List<FishResponse> fishList
) {
    
}
