package pl.someday.rest_api_fishing_log.controller;

import lombok.RequiredArgsConstructor;
import pl.someday.rest_api_fishing_log.dto.FishDTO.CreateFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishDTO.PatchFishDTO;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.CreateFishingSessionRequest;
import pl.someday.rest_api_fishing_log.dto.FishingSessionDTO.PatchFishingSessionRequest;
import pl.someday.rest_api_fishing_log.service.impl.FishingServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final FishingServiceImpl fishingService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello user!");
    }

    @PostMapping("/session/create")
    public ResponseEntity<String> createFishingSession(@RequestBody @Valid CreateFishingSessionRequest request) throws Exception{
        try{
            fishingService.createFishingSession(request);
            return ResponseEntity.created(null).body("Session created successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Session creation failed");
        }
    }

    @PatchMapping("/session/update/{id}")
    public ResponseEntity<String> updateFishingSession(@PathVariable Long id, @RequestBody @Valid PatchFishingSessionRequest request) throws Exception{
        try{
            fishingService.updateFishingSession(id, request);
            return ResponseEntity.ok("Session updated successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Session update failed");
        }
    }

    @DeleteMapping("/session/delete/{id}")
    public ResponseEntity<String> deleteFishingSession(@PathVariable Long id) throws Exception{
        try{
            fishingService.deleteFishingSession(id);
            return ResponseEntity.ok("Session deleted successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Session deletion failed");
        }
    }

    @PostMapping("/fish/add/{sessionId}")
    public ResponseEntity<String> addFishToSession(@PathVariable Long sessionId, @RequestBody @Valid CreateFishDTO createFishDTO) throws Exception{
        try{
            fishingService.addFishToSession(sessionId, createFishDTO);
            return ResponseEntity.ok("Fish added successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Fish addition failed");
        }
    }

    @DeleteMapping("/fish/delete/{fishId}")
    public ResponseEntity<String> deleteFish(@PathVariable Long fishId) throws Exception{
        try{
            fishingService.deleteFish(fishId);
            return ResponseEntity.ok("Fish deleted successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Fish deletion failed");
        }
    }

    @PatchMapping("/fish/update/{fishId}")
    public ResponseEntity<String> updateFishFromSession(@PathVariable Long fishId, @RequestBody @Valid PatchFishDTO patchFishDTO) throws Exception{
        try{
            fishingService.updateFish(fishId, patchFishDTO);
            return ResponseEntity.ok("Fish updated successfully");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Fish update failed");
        }
    }
}
