package com.welovebags.blank.model.mappers;

import com.welovebags.blank.model.dto.request.UserRegisterRequestDto;
import com.welovebags.blank.model.dto.response.UserRegisterResponseDto;
import com.welovebags.blank.model.entity.User;
import com.welovebags.blank.model.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserMapper {
  public static User toEntity(UserRegisterRequestDto userRegisterDto, PasswordEncoder encoder) {
    return User.builder()
        .name(userRegisterDto.name())
        .username(userRegisterDto.username())
        .password(encoder.encode(userRegisterDto.password()))
        .role(UserRole.USER)
        .build();
  }

  public static UserRegisterResponseDto fromEntity(User user) {
    return new UserRegisterResponseDto(
        user.getId(),
        user.getName(),
        user.getUsername(),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }
}
