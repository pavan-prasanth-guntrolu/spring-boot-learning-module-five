package com.learning.module_5.services;


import com.learning.module_5.dto.LoginDTO;
import com.learning.module_5.dto.SignupDTO;
import com.learning.module_5.dto.UserDTO;
import com.learning.module_5.entities.UserEntity;
import com.learning.module_5.exceptions.ResourceNotFoundException;
import com.learning.module_5.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException("User with email"+username+"Not found"));
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with email"+ userId +"Not found"));
    }

    public UserDTO signup(SignupDTO signupDTO) {
        Optional<UserEntity> userEntity=userRepository.findByEmail(signupDTO.getEmail());
        if(userEntity.isPresent()){
            throw new BadCredentialsException("User with email already exists"+signupDTO.getEmail());
        }

        UserEntity toBeCreatedUser= modelMapper.map(signupDTO,UserEntity.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        UserEntity savedUser=userRepository.save(toBeCreatedUser);

        return modelMapper.map(savedUser,UserDTO.class);
    }


}

