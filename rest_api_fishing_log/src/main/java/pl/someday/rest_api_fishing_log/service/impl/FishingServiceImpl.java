package pl.someday.rest_api_fishing_log.service.impl;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pl.someday.rest_api_fishing_log.dto.FishDTO.CreateFishRequest;
import pl.someday.rest_api_fishing_log.dto.FishDTO.FishResponse;
import pl.someday.rest_api_fishing_log.dto.FishDTO.PatchFishRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.CreateFishingSessionRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.FishingSessionResponse;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.PatchFishingSessionRequest;
import pl.someday.rest_api_fishing_log.model.Fish;
import pl.someday.rest_api_fishing_log.model.FishName;
import pl.someday.rest_api_fishing_log.model.FishingSession;
import pl.someday.rest_api_fishing_log.repository.FishNameRepository;
import pl.someday.rest_api_fishing_log.repository.FishRepository;
import pl.someday.rest_api_fishing_log.repository.FishingSessionRepository;
import pl.someday.rest_api_fishing_log.repository.FishingSpotRepository;
import pl.someday.rest_api_fishing_log.repository.UserRepository;
import pl.someday.rest_api_fishing_log.service.FishingService;

@Service
@RequiredArgsConstructor
public class FishingServiceImpl implements FishingService {

    private final FishRepository fishRepository;
    private final FishNameRepository fishNameRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishingSessionRepository fishingSessionRepository;
    private final UserRepository userRepository;
    private final ModelConverterImpl modelConverter;

    @Override
    public void createFishingSession(CreateFishingSessionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FishingSession fishingSession = new FishingSession();
        fishingSession.setDate(request.date());
        fishingSession.setUser(userRepository.findByUsername(authentication.getName()).orElseThrow());
        fishingSession.setFishingSpot(fishingSpotRepository.findById(request.fishingSpotId()).orElseThrow());
        fishingSessionRepository.save(fishingSession);
    }

    @Override
    public void deleteFishingSession(Long id) {
        fishingSessionRepository.deleteById(id);
    }

    @Override
    public void updateFishingSession(Long id, PatchFishingSessionRequest request) {
        FishingSession fishingSession = fishingSessionRepository.findById(id).orElseThrow();
        if(request.date() != null){
            fishingSession.setDate(request.date());
        }
        if(request.fishingSpotId() != null){
            fishingSession.setFishingSpot(fishingSpotRepository.findById(request.fishingSpotId()).orElseThrow());
        }
        fishingSessionRepository.save(fishingSession);
    }

    public void addFishToSession(Long id, @Valid CreateFishRequest createFishDTO) {
        Fish fish = new Fish();
        fish.setFishName(fishNameRepository.findById(createFishDTO.fishNameId()).orElseThrow());
        fish.setFishingSession(fishingSessionRepository.findById(id).orElseThrow());
        if(createFishDTO.weight() != null){
            fish.setWeight(createFishDTO.weight());
        }
        else{
            fish.setWeight(BigDecimal.ZERO);
        }
        if(createFishDTO.length() != null){
            fish.setLength(createFishDTO.length());
        }
        else{
            fish.setLength(BigDecimal.ZERO);
        }
        fishRepository.save(fish);
    }

    @Override
    public void deleteFish(Long id) {
        Fish fish = fishRepository.findById(id).orElseThrow();
        fishRepository.delete(fish);
    }

    @Override
    public void updateFish(Long id, @Valid PatchFishRequest patchFishDTO) {
        Fish fish = fishRepository.findById(id).orElseThrow();
        if(patchFishDTO.fishNameId() != null){
            fish.setFishName(fishNameRepository.findById(patchFishDTO.fishNameId()).orElseThrow());
        }
        if(patchFishDTO.weight() != null){
            fish.setWeight(patchFishDTO.weight());
        }   
        if(patchFishDTO.length() != null){
            fish.setLength(patchFishDTO.length());
        }
        fishRepository.save(fish);
    }

    @Override
    public FishName getFishName(Long id) {
        return fishNameRepository.findById(id).orElseThrow();
    }

    @Override
    public Fish getFish(Long id) {
        return fishRepository.findById(id).orElseThrow();
        
    }

    @Override
    public FishResponse getFishResponse(Long FishId) {
        return modelConverter.convertFishToFishResponse(fishRepository.findById(FishId).orElseThrow());
    }

    @Override
    public FishingSession getFishingSession(Long id) {
        return fishingSessionRepository.findById(id).orElseThrow();
    }

    @Override
    public FishingSessionResponse getFishingSessionResponse(Long SessionId) {
        
        return modelConverter.convertFishingSessionToFishingSessionResponse(fishingSessionRepository.findById(SessionId).orElseThrow());
    }

    

    

}
