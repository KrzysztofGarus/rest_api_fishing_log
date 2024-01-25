package pl.someday.rest_api_fishing_log.service;

import jakarta.validation.Valid;
import pl.someday.rest_api_fishing_log.dto.FishDTO.CreateFishRequest;
import pl.someday.rest_api_fishing_log.dto.FishDTO.FishResponse;
import pl.someday.rest_api_fishing_log.dto.FishDTO.PatchFishRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.CreateFishingSessionRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.FishingSessionResponse;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.PatchFishingSessionRequest;
import pl.someday.rest_api_fishing_log.model.Fish;
import pl.someday.rest_api_fishing_log.model.FishName;
import pl.someday.rest_api_fishing_log.model.FishingSession;

public interface FishingService {

void createFishingSession(CreateFishingSessionRequest request);

void deleteFishingSession(Long id);

void updateFishingSession(Long id, PatchFishingSessionRequest request);

FishingSession getFishingSession(Long id);

FishingSessionResponse getFishingSessionResponse(Long SessionId);

void addFishToSession(Long id, CreateFishRequest createFishDTO);

void deleteFish(Long id);

void updateFish(Long id, @Valid PatchFishRequest patchFishDTO);

FishName getFishName(Long id);

Fish getFish(Long id);

FishResponse getFishResponse(Long FishId);

}
