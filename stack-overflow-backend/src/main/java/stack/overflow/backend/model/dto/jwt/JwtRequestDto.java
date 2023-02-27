package stack.overflow.backend.model.dto.jwt;

import javax.validation.constraints.NotBlank;

public record JwtRequestDto(
        @NotBlank String username,
        @NotBlank String password) {
}
