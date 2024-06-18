package com.welovebags.blank.security.service;

import com.welovebags.blank.model.dto.request.AuthRequestDto;
import com.welovebags.blank.model.dto.response.AuthResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface AuthService {
  AuthResponseDto authenticate(@Valid AuthRequestDto dto, HttpServletRequest request);
}
