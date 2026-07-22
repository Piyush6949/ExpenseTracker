package org.expensetracker.authentication.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.dto.LoginRequestDto;
import org.expensetracker.authentication.dto.LoginResponseDto;
import org.expensetracker.authentication.dto.SignupRequestDto;
import org.expensetracker.authentication.service.LoginService;
import org.expensetracker.authentication.service.SignupService;
import org.expensetracker.authentication.util.CookieUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);
        CookieUtil.addAccessTokenCookie(httpServletResponse,loginResponseDto.getJwt());
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto, HttpServletResponse httpServletResponse) {
        LoginResponseDto signupResponseDto = signupService.signup(signupRequestDto);
        CookieUtil.addAccessTokenCookie(httpServletResponse,signupResponseDto.getJwt());
        return ResponseEntity.ok(signupResponseDto);
    }

}
