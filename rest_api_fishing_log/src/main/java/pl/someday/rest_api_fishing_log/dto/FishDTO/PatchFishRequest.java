package pl.someday.rest_api_fishing_log.dto.FishDTO;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PatchFishRequest(

@Valid

@NotNull
@Min(0)
Long fishId,

@Min(0)
Long fishNameId,

@Min(0)
@Max(999)
@NumberFormat(pattern = "0.#")
BigDecimal length,

@Min(0)
@Max(999)
@NumberFormat(pattern = "0.#")
BigDecimal weight,

@NotNull
@Min(0)
Long sessionId

) {}