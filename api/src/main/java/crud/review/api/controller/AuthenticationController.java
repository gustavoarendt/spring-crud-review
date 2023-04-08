package crud.review.api.controller;

import crud.review.api.controller.dto.user.JwtTokenViewModel;
import crud.review.api.controller.dto.user.UserLogin;
import crud.review.api.model.User;
import crud.review.api.infrastructure.configuration.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid UserLogin loginDto) {
        var token = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
        var authentication = manager.authenticate(token);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenViewModel(tokenJwt));
    }
}
