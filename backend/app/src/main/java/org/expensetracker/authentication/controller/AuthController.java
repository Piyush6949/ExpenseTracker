package org.expensetracker.authentication.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.dto.LoginRequestDto;
import org.expensetracker.authentication.dto.LoginResponseDto;
import org.expensetracker.authentication.dto.SignupRequestDto;
import org.expensetracker.authentication.service.LoginService;
import org.expensetracker.authentication.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return loginService.login(loginRequestDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        LoginResponseDto response = signupService.signup(signupRequestDto);
        return ResponseEntity.ok(response);
    }

}
