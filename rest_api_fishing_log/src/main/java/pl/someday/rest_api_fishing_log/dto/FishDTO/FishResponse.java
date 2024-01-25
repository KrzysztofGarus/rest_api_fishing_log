package pl.someday.rest_api_fishing_log.dto.FishDTO;

import java.math.BigDecimal;

public record FishResponse(

        Long id,
        String name,
        BigDecimal length,
        BigDecimal weight
) {
    
}
