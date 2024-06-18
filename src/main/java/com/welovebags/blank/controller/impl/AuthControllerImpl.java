package com.welovebags.blank.controller.impl;

import com.welovebags.blank.controller.AuthController;
import com.welovebags.blank.model.dto.request.AuthRequestDto;
import com.welovebags.blank.model.dto.response.AuthResponseDto;
import com.welovebags.blank.security.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "auth")
@Tag(name = "Auth", description = "Rotas para autenticação")
public class AuthControllerImpl implements AuthController {
  private final AuthService authService;

  @PostMapping("token")
  public ResponseEntity<AuthResponseDto> getToken(
      @RequestBody @Valid AuthRequestDto dto,
      HttpServletRequest request) {
    return ResponseEntity.ok(authService.authenticate(dto, request));
  }
}
