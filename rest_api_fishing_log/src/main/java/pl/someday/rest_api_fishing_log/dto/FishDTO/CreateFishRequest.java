package pl.someday.rest_api_fishing_log.dto.FishDTO;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CreateFishRequest(

@Valid

@NotNull
@Min(0)
Long fishNameId,

@Min(0)
@Max(999)
@NumberFormat(pattern = "0.#")
BigDecimal length,

@Min(0)
@Max(999)
@NumberFormat(pattern = "0.#")
BigDecimal weight
) {}
