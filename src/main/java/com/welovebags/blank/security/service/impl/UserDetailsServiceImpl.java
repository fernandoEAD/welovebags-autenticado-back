package com.welovebags.blank.security.service.impl;

import com.welovebags.blank.model.entity.User;
import com.welovebags.blank.repository.UserRepository;
import com.welovebags.blank.security.config.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User usuario = this.userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));
    return new AuthenticatedUser(usuario);
  }
}
