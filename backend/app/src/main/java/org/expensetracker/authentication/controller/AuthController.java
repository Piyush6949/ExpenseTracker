package org.expensetracker.authentication.controller;


import lombok.AllArgsConstructor;
import org.expensetracker.authentication.dto.LoginRequestDto;
import org.expensetracker.authentication.dto.LoginResponseDto;
import org.expensetracker.authentication.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginService loginService;
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return loginService.login(loginRequestDto);
    }

    
}
