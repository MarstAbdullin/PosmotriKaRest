package posmotriKa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import posmotriKa.dto.RegisterDto;
import posmotriKa.services.RegisterService;


@RestController
public class RegisterRestController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> deleteUser(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.toString());
        registerService.register(registerDto);
        return ResponseEntity.accepted().build();
    }
}
