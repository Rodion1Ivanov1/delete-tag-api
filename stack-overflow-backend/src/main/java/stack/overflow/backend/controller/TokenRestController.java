package stack.overflow.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stack.overflow.backend.api.Data;
import stack.overflow.backend.model.dto.jwt.JwtRequestDto;
import stack.overflow.backend.util.TokenUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/token")
public class TokenRestController {

    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<Data<String>> getToken(@RequestBody @Valid JwtRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        String token = tokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(Data.of(token));
    }

    @GetMapping("/is-expired")
    public ResponseEntity<Data<Boolean>> isTokenExpired(@RequestParam @NotBlank String token) {
        return ResponseEntity.ok(Data.of(tokenUtil.isTokenExpired(token)));
    }
}
