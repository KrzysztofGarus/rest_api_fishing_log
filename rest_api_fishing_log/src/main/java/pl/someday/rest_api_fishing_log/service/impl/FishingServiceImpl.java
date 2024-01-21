package pl.someday.rest_api_fishing_log.service.impl;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pl.someday.rest_api_fishing_log.dto.FishDTO.CreateFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishDTO.PatchFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.CreateFishingSessionRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.PatchFishingSessionRequest;
import pl.someday.rest_api_fishing_log.model.Fish;
import pl.someday.rest_api_fishing_log.model.FishingSession;
import pl.someday.rest_api_fishing_log.repository.FishNameRepository;
import pl.someday.rest_api_fishing_log.repository.FishRepository;
import pl.someday.rest_api_fishing_log.repository.FishingSessionRepository;
import pl.someday.rest_api_fishing_log.repository.FishingSpotRepository;
import pl.someday.rest_api_fishing_log.repository.UserRepository;
import pl.someday.rest_api_fishing_log.service.FishingService;

@Service
public class FishingServiceImpl implements FishingService {

    private final FishRepository fishRepository;
    private final FishNameRepository fishNameRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishingSessionRepository fishingSessionRepository;
    private final UserRepository userRepository;

    public FishingServiceImpl(FishRepository fishRepository, FishNameRepository fishNameRepository, FishingSpotRepository fishingSpotRepository, FishingSessionRepository fishingSessionRepository,
     UserRepository userRepository) {
        this.fishRepository = fishRepository;
        this.fishNameRepository = fishNameRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.fishingSessionRepository = fishingSessionRepository;
        this.userRepository = userRepository;
    }

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

    public void addFishToSession(Long id, @Valid CreateFishDTO createFishDTO) {
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
    public void updateFish(Long id, @Valid PatchFishDTO patchFishDTO) {
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
}
