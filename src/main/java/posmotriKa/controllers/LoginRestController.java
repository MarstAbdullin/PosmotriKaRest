package posmotriKa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import posmotriKa.dto.LoginDto;
import posmotriKa.dto.TokenDto;
import posmotriKa.services.LoginService;


@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> signIn(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(loginService.signIn(loginDto));
    }
}
