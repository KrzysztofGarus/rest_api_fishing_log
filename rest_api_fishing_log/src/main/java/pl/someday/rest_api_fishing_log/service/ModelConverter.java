package pl.someday.rest_api_fishing_log.service;

import pl.someday.rest_api_fishing_log.dto.FishDTO.FishResponse;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.FishingSessionResponse;
import pl.someday.rest_api_fishing_log.model.Fish;
import pl.someday.rest_api_fishing_log.model.FishingSession;

public interface ModelConverter {

    FishResponse convertFishToFishResponse(Fish fish);
    FishingSessionResponse convertFishingSessionToFishingSessionResponse(FishingSession fishingSession);

}
