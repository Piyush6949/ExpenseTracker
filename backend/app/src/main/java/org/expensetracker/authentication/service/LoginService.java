package org.expensetracker.authentication.service;

import lombok.AllArgsConstructor;
import org.expensetracker.authentication.dto.LoginRequestDto;
import org.expensetracker.authentication.dto.LoginResponseDto;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.authentication.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        LoginResponseDto loginResponseDto = modelMapper.map(user,LoginResponseDto.class);
        
        
        // add jwt
        String jwt = jwtUtil.jwts(loginResponseDto.getUsername(), User.Role.USER);
        loginResponseDto.setJwt(jwt);

        return loginResponseDto;
    }
}
