package pl.someday.rest_api_fishing_log.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import pl.someday.rest_api_fishing_log.dto.FishDTO.FishResponse;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.FishingSessionResponse;
import pl.someday.rest_api_fishing_log.model.Fish;
import pl.someday.rest_api_fishing_log.model.FishingSession;
import pl.someday.rest_api_fishing_log.service.ModelConverter;

@Service
public class ModelConverterImpl implements ModelConverter {

    @Override
    public FishResponse convertFishToFishResponse(Fish fish) {
        FishResponse fishResponse = new FishResponse(
            fish.getId(),
            fish.getFishName().getName(),
            fish.getLength() != null ? fish.getLength() : BigDecimal.ZERO,
            fish.getWeight() != null ? fish.getWeight() : BigDecimal.ZERO
    );
    return fishResponse; 
    }

    @Override
    public FishingSessionResponse convertFishingSessionToFishingSessionResponse(FishingSession fishingSession) {
        
        FishingSessionResponse fishingSessionResponse = new FishingSessionResponse(
                fishingSession.getId(),
                fishingSession.getDate(),
                fishingSession.getFishingSpot().getName(),
                fishingSession.getFishList().stream().map(this::convertFishToFishResponse).toList()
        );
        return fishingSessionResponse;
    }

    
}
