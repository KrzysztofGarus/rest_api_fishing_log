package pl.someday.rest_api_fishing_log.service;

import jakarta.validation.Valid;
import pl.someday.rest_api_fishing_log.dto.FishDTO.CreateFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishDTO.PatchFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.CreateFishingSessionRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.PatchFishingSessionRequest;

public interface FishingService {

void createFishingSession(CreateFishingSessionRequest request);

void deleteFishingSession(Long id);

void updateFishingSession(Long id, PatchFishingSessionRequest request);

void addFishToSession(Long id, CreateFishDTO createFishDTO);

void deleteFish(Long id);

void updateFish(Long id, @Valid PatchFishDTO patchFishDTO);

}
