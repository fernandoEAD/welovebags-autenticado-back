package com.welovebags.blank.service.impl;

import com.welovebags.blank.model.dto.request.UserRegisterRequestDto;
import com.welovebags.blank.model.dto.response.UserRegisterResponseDto;
import com.welovebags.blank.model.mappers.UserMapper;
import com.welovebags.blank.repository.UserRepository;
import com.welovebags.blank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  @Override
  public UserRegisterResponseDto save(UserRegisterRequestDto dto) {
    userRepository.findByUsername(dto.username()).ifPresent((result) -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!");
    });

    var userEntity = UserMapper.toEntity(dto, encoder);
    return UserMapper.fromEntity(userRepository.save(userEntity));
  }

  @Override
  public UserRegisterResponseDto findById(UUID id) {
    var user = userRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    return UserMapper.fromEntity(user);
  }

  @Override
  public UserRegisterResponseDto findByUsername(String username) {
    var user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    return UserMapper.fromEntity(user);
  }

  @Override
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }
}
