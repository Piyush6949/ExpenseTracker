package org.expensetracker.authentication.service;

import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.dto.LoginResponseDto;
import org.expensetracker.authentication.dto.SignupRequestDto;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.authentication.repository.UserRepository;
import org.expensetracker.authentication.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto signup(SignupRequestDto signupRequestDto) {

        Optional<User> user = userRepository.findByUsername(signupRequestDto.getUsername());

        if(user.isPresent()){
            throw new RuntimeException("Username already exists");
        }

        User user1 = modelMapper.map(signupRequestDto,User.class);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        userRepository.save(user1);

        // set username
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUsername(signupRequestDto.getUsername());

        // add jwt
        
        loginResponseDto.setJwt(jwtUtil.jwts(loginResponseDto.getUsername(), User.Role.USER));

        return loginResponseDto;
    }

}
