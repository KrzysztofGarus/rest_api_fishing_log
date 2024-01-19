package pl.someday.rest_api_fishing_log.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JWTAuthenticationResponse {

    private String token;
}
