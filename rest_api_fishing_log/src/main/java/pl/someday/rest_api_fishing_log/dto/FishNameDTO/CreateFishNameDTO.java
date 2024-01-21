package pl.someday.rest_api_fishing_log.dto.FishNameDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateFishNameDTO(

@Valid

@NotBlank(message = "Fish name cannot be blank")
String fishName
) {
    
}
